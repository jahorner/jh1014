package com.toolshop;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.toolshop.data.ToolTypeRepository;
import com.toolshop.data.ToolRepository;
import com.toolshop.model.ToolType;
import com.toolshop.model.RentalAgreement;
import com.toolshop.model.Tool;

public class CheckoutProcessor {
    final ToolRepository toolRepository;
    final ToolTypeRepository toolTypeRepository;
    public CheckoutProcessor(ToolRepository toolRepository, ToolTypeRepository toolTypeRepository){
        this.toolRepository = toolRepository;
        this.toolTypeRepository = toolTypeRepository;
    }

    public RentalAgreement checkout(String toolCode, int rentalDayCount, int discoutPercentage, LocalDate checkoutDate) throws Exception{
        if(rentalDayCount < 1){
            throw new Exception("Rental Day Count should not be less than 1");
        }
        if(discoutPercentage < 0 || discoutPercentage > 100){
            throw new Exception("Discount percentage should be between 0 and 100");
        }
        Tool tool = toolRepository.getTool(toolCode);
        ToolType toolType = toolTypeRepository.getItemList(tool.getType());

        double total = toolType.getPrice() * rentalDayCount;
        double discount = total * (discoutPercentage/100.0);//TODO check floating point math;
        double toCharge = total - discount;

        LocalDate dueDate = checkoutDate.plus(rentalDayCount, ChronoUnit.DAYS);

        //TODO add holiday/weekend calcs
        int chargeDays = rentalDayCount;

        return new RentalAgreement(tool.getCode(), tool.getType(), tool.getBrand(), rentalDayCount, checkoutDate, dueDate, 
            toolType.getPrice(), chargeDays,total, discoutPercentage, discount, toCharge);
    }
}
