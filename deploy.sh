# deploy.sh
#! /bin/bash

SHA1=$1
env=$2

# Deploy image to Docker Hub
docker push larse514/tcfjshifts:$SHA1

# Create new Elastic Beanstalk version
EB_BUCKET=elasticbeanstalk-us-west-2-417615409974
DOCKERRUN_FILE=$SHA1-Dockerrun.aws.json

sed "s/<TAG>/$SHA1/" < Dockerrun.aws.json.template > $DOCKERRUN_FILE
echo $DOCKERRUN_FILE
echo $EB_BUCKET
ls

#todo-update this to create environment in DEV and tear down after tests
aws s3 cp $DOCKERRUN_FILE s3://$EB_BUCKET/$DOCKERRUN_FILE

aws elasticbeanstalk create-application-version --application-name ShiftsService \
  --version-label $SHA1 --source-bundle S3Bucket=$EB_BUCKET,S3Key=$DOCKERRUN_FILE

# Update Elastic Beanstalk environment to new version
updateResponse=`aws elasticbeanstalk update-environment --environment-name ShiftService-"${env}" \
    --version-label "${SHA1}"`
echo "update response is $updateResponse"
updateStatus=`echo $updateResponse|python -c "import sys, json; print(json.load(sys.stdin)['Status'])"`
#check update status
echo "status is $updateStatus"
if [ $updateStatus = Updating ]; then
     echo "Update started"
 else
     echo "Deployment failed with status $updateStatus"
     exit 1
 fi

 while [ $updateStatus != 'Ready' ] && [ $updateStatus != 'Terminated' ]; do
     echo "deploy status is $updateStatus"
     updateResponse=`aws elasticbeanstalk describe-environment-health --environment-name ShiftService-$env \
        --attribute-names All`
     updateStatus=`echo $updateResponse|python -c "import sys, json; print(json.load(sys.stdin)['Status'])"`
     sleep 4
 done
 if [ $updateStatus = Ready ]; then
     #a hacky solution to deployment lag
     sleep 15
     echo "Deployment succeeded!"
 else
     echo "Deployment failed with status $updateStatus"
     exit 1
 fi
