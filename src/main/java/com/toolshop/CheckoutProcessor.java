package com.toolshop;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

import com.toolshop.data.ToolRepository;
import com.toolshop.model.RentalAgreement;
import com.toolshop.model.Tool;
import com.toolshop.model.ToolType;

public class CheckoutProcessor {
    final ToolRepository toolRepository;
    public CheckoutProcessor(ToolRepository toolRepository){
        this.toolRepository = toolRepository;
    }

    public RentalAgreement checkout(String toolCode, int rentalDayCount, int discoutPercentage, LocalDate checkoutDate) throws Exception{
        if(rentalDayCount < 1){
            throw new Exception("Rental Day Count should not be less than 1");
        }
        if(discoutPercentage < 0 || discoutPercentage > 100){
            throw new Exception("Discount percentage should be between 0 and 100");
        }
        Tool tool = toolRepository.getTool(toolCode);
        if(tool == null){
            throw new Exception("Could not find tool");
        }
        ToolType toolType = tool.getType();
        if(toolType == null){
            throw new Exception("Could not find tool type");
        }

        int chargeDays = rentalDayCount - findNonChargeDayCount(checkoutDate, rentalDayCount, toolType);

        double total = toolType.getPrice() * chargeDays;
        //TODO check floating point math, possibly change from using decimal dollars to integer cents;
        double discount = total * (discoutPercentage/100.0);
        double toCharge = total - discount;

        LocalDate dueDate = checkoutDate.plus(rentalDayCount-1, ChronoUnit.DAYS);

        return new RentalAgreement(tool.getCode(), tool.getType().getToolType(), tool.getBrand(), rentalDayCount, checkoutDate, dueDate, 
            toolType.getPrice(), chargeDays, total, discoutPercentage, discount, toCharge);
    }

    private int findNonChargeDayCount(LocalDate checkoutDate, int rentalDayCount,ToolType toolType){
        int nonChargeDays = 0;
        for(int i = 0; i < rentalDayCount; i++){
            LocalDate testDate = checkoutDate.plus(i, ChronoUnit.DAYS);
            DayOfWeek dow = testDate.getDayOfWeek();
            boolean isWeekend = false;
            boolean isHoliday = false;
            if(dow == DayOfWeek.SATURDAY || dow == DayOfWeek.SUNDAY){
                isWeekend = true;
            }
            if(testDate.getDayOfMonth() == 4 && testDate.getMonth() == Month.JULY){
                if(dow == DayOfWeek.SATURDAY && i>=1 && !toolType.isHolidayCharge()){
                    nonChargeDays++;//we're counting the friday as the 4th of july
                }
                if(dow == DayOfWeek.SUNDAY && i < rentalDayCount && !toolType.isHolidayCharge()){
                    nonChargeDays++;//we're counting the monday as the 4th of july
                }
                else if(dow !=DayOfWeek.SATURDAY && dow != DayOfWeek.SUNDAY){
                    isHoliday = true;
                }
            }
            if(testDate.getMonth() == Month.SEPTEMBER && testDate.getDayOfWeek() == DayOfWeek.MONDAY && testDate.getDayOfMonth() <=7){
                isHoliday = true; //Labor day - 1st monday of september
            }

            if(!toolType.isHolidayCharge() && isHoliday){
                nonChargeDays++;
            }
            else if(!toolType.isWeekendCharge() && isWeekend){
                nonChargeDays++;
            }
        }
        return nonChargeDays;
    }
}
