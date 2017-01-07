# deploy.sh
#! /bin/bash

SHA1=$1

# Deploy image to Docker Hub
docker push larse514/tcfjshifts:$SHA1

# Create new Elastic Beanstalk version
EB_BUCKET=elasticbeanstalk-us-west-2-417615409974
DOCKERRUN_FILE=Dockerrun.aws.json
sed "s/<TAG>/$SHA1/" < Dockerrun.aws.json.template > $DOCKERRUN_FILE
echo $DOCKERRUN_FILE
echo $EB_BUCKET
aws s3 cp $DOCKERRUN_FILE s3://$EB_BUCKET/$DOCKERRUN_FILE
aws elasticbeanstalk create-application-version --application-name ShiftService \
  --version-label $SHA1 --source-bundle S3Bucket=$EB_BUCKET,S3Key=$DOCKERRUN_FILE

# Update Elastic Beanstalk environment to new version
aws elasticbeanstalk update-environment --environment-name ShiftService-env \
    --version-label $SHA1