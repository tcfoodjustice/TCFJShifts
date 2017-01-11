package com.tcfj.webservice.model;

/**
 * Created by andrew.larsen on 10/25/2016.
 */
public class Shift {

    private int shiftId;
    private String donarName;
    private String recipientName;
    private String rescueDate;
    private String volunteer1;
    private String volunteer2;
    private String volunteer3;
    private String pickUpTime;
    private String modeOfTransit;
    private int foodDonatedWeight;
    private int foodCompostedWeight;
    private int shiftLength;
    private String foodTypeSummary;
    private String comments;
    private boolean suppliesStocked;
    private String submitTime;

    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    public String getDonarName() {
        return donarName;
    }

    public void setDonarName(String donarName) {
        this.donarName = donarName;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRescueDate() {
        return rescueDate;
    }

    public void setRescueDate(String rescueDate) {
        this.rescueDate = rescueDate;
    }

    public String getVolunteer1() {
        return volunteer1;
    }

    public void setVolunteer1(String volunteer1) {
        this.volunteer1 = volunteer1;
    }

    public String getVolunteer2() {
        return volunteer2;
    }

    public void setVolunteer2(String volunteer2) {
        this.volunteer2 = volunteer2;
    }

    public String getVolunteer3() {
        return volunteer3;
    }

    public void setVolunteer3(String volunteer3) {
        this.volunteer3 = volunteer3;
    }

    public String getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(String pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public String getModeOfTransit() {
        return modeOfTransit;
    }

    public void setModeOfTransit(String modeOfTransit) {
        this.modeOfTransit = modeOfTransit;
    }

    public int getFoodDonatedWeight() {
        return foodDonatedWeight;
    }

    public void setFoodDonatedWeight(int foodDonatedWeight) {
        this.foodDonatedWeight = foodDonatedWeight;
    }

    public int getFoodCompostedWeight() {
        return foodCompostedWeight;
    }

    public void setFoodCompostedWeight(int foodCompostedWeight) {
        this.foodCompostedWeight = foodCompostedWeight;
    }

    public int getShiftLength() {
        return shiftLength;
    }

    public void setShiftLength(int shiftLength) {
        this.shiftLength = shiftLength;
    }

    public String getFoodTypeSummary() {
        return foodTypeSummary;
    }

    public void setFoodTypeSummary(String foodTypeSummary) {
        this.foodTypeSummary = foodTypeSummary;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public boolean isSuppliesStocked() {
        return suppliesStocked;
    }

    public void setSuppliesStocked(boolean suppliesStocked) {
        this.suppliesStocked = suppliesStocked;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }
}
