package com.tcfj.webservice.model;

/**
 * Created by andrew.larsen on 10/25/2016.
 */
public class Shift {

    private Integer shiftId;
    private Integer donarId;
    private Integer recipientId;
    private String rescueDate;
    private String volunteer1;
    private String volunteer2;
    private String volunteer3;
    private String pickUpTime;
    private String modeOfTransit;
    private Integer foodDonatedWeight;
    private Integer foodCompostedWeight;
    private Integer shiftLength;
    private String foodTypeSummary;
    private String comments;
    private Boolean suppliesStocked;
    private String submitTime;
    private Integer organizationId;

    public Integer getShiftId() {
        return shiftId;
    }

    public void setShiftId(Integer shiftId) {
        this.shiftId = shiftId;
    }

    public Integer getDonarId() {
        return donarId;
    }

    public void setDonarId(Integer donarId) {
        this.donarId = donarId;
    }

    public Integer getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Integer recipientId) {
        this.recipientId = recipientId;
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

    public Integer getFoodDonatedWeight() {
        return foodDonatedWeight;
    }

    public void setFoodDonatedWeight(Integer foodDonatedWeight) {
        this.foodDonatedWeight = foodDonatedWeight;
    }

    public Integer getFoodCompostedWeight() {
        return foodCompostedWeight;
    }

    public void setFoodCompostedWeight(Integer foodCompostedWeight) {
        this.foodCompostedWeight = foodCompostedWeight;
    }

    public Integer getShiftLength() {
        return shiftLength;
    }

    public void setShiftLength(Integer shiftLength) {
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

    public Boolean getSuppliesStocked() {
        return suppliesStocked;
    }

    public void setSuppliesStocked(Boolean suppliesStocked) {
        this.suppliesStocked = suppliesStocked;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }
}
