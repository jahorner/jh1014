package com.toolshop.model;

public class ToolType {

    final String toolType;
    final double price;
    final boolean weekdayCharge;
    final boolean weekendCharge;
    final boolean holidayCharge;

    public ToolType(String toolType, double price, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge){
        this.toolType = toolType;
        this.price = price;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
    }

    public String getToolType() {
        return toolType;
    }

    public double getPrice() {
        return price;
    }

    public boolean isWeekdayCharge() {
        return weekdayCharge;
    }

    public boolean isWeekendCharge() {
        return weekendCharge;
    }

    public boolean isHolidayCharge() {
        return holidayCharge;
    }
}
