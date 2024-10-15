package com.toolshop.model;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.Locale;

public class RentalAgreement {
//     Tool code - Specified at checkout
// ● Tool type - From tool info
// ● Tool brand - From tool info
// ● Rental days - Specified at checkout
// ● Check out date - Specified at checkout
// ● Due date - Calculated from checkout date and rental days.
// ● Daily rental charge - Amount per day, specified by the tool type.
// ● Charge days - Count of chargeable days, from day after checkout through and including due
// date, excluding “no charge” days as specified by the tool type.
// ● Pre-discount charge - Calculated as charge days X daily charge. Resulting total rounded half up
// to cents.
// ● Discount percent - Specified at checkout.
// ● Discount amount - calculated from discount % and pre-discount charge. Resulting amount
// rounded half up to cents.
// ● Final charge - Calculated as pre-discount charge - discount amount.

    final String toolCode;
    final String toolType;
    final String brand;
    final int rentalDays;
    final LocalDate checkoutDate;
    final LocalDate dueDate;
    final double dailyRentalCharge;
    final int chargeDays;
    final double preDiscountCharge;
    final int discountPercent;
    final double discountAmount;
    final double finalCharge;

    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yy", Locale.ENGLISH);
    NumberFormat usdFormatter  = NumberFormat.getCurrencyInstance(Locale.US); 
    

    public RentalAgreement(String toolCode,
                            String toolType,
                            String brand,
                            int rentalDays,
                            LocalDate checkoutDate,
                            LocalDate dueDate,
                            double dailyRentalCharge,
                            int chargeDays,
                            double preDiscountCharge,
                            int discountPercent,
                            double discountAmount,
                            double finalCharge){
            usdFormatter.setCurrency(Currency.getInstance("USD"));
            this.toolCode = toolCode;
            this.toolType = toolType;
            this.brand = brand;
            this.rentalDays = rentalDays;
            this.checkoutDate = checkoutDate;
            this.dueDate = dueDate;
            this.dailyRentalCharge = dailyRentalCharge;
            this.chargeDays = chargeDays;
            this.preDiscountCharge = preDiscountCharge;
            this.discountPercent = discountPercent;
            this.discountAmount = discountAmount;
            this.finalCharge = finalCharge;
        }

    public String getToolCode() {
        return toolCode;
    }

    public String getToolType() {
        return toolType;
    }

    public String getBrand() {
        return brand;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public double getDailyRentalCharge() {
        return dailyRentalCharge;
    }

    public int getChargeDays() {
        return chargeDays;
    }

    public double getPreDiscountCharge() {
        return preDiscountCharge;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public double getFinalCharge() {
        return finalCharge;
    }

    public void printRentalAgreementToConsole(){
        StringBuilder builder = new StringBuilder();
        builder.append("Tool code: ").append(this.getToolCode()).append("\r\n");
        builder.append("Tool type: ").append(this.getToolType()).append("\r\n");
        builder.append("Brand: ").append(this.getBrand()).append("\r\n");
        builder.append("Rental Days: ").append(this.getRentalDays()).append("\r\n");
        builder.append("Checkout Date: ").append(dateFormatter.format(this.getCheckoutDate())).append("\r\n");
        builder.append("Due Date: ").append(dateFormatter.format(this.getDueDate())).append("\r\n");
        builder.append("Daily Rental Charge: ").append(usdFormatter.format(this.getDailyRentalCharge())).append("\r\n");
        builder.append("Charge Days: ").append(this.getChargeDays()).append("\r\n");
        builder.append("Pre Discount Charge: ").append(usdFormatter.format(this.getPreDiscountCharge())).append("\r\n");
        builder.append("Discount Percent: ").append(this.getDiscountPercent()).append("%").append("\r\n");
        builder.append("Discount Amount: ").append(usdFormatter.format(this.getDiscountAmount())).append("\r\n");
        builder.append("Final Charge: ").append(usdFormatter.format(this.getFinalCharge())).append("\r\n");
        System.out.println(builder.toString());
    }

}
