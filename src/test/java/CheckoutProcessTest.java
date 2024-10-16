
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.toolshop.CheckoutProcessor;
import com.toolshop.data.ToolRepository;
import com.toolshop.data.ToolTypeRepository;
import com.toolshop.model.RentalAgreement;

public class CheckoutProcessTest {

    
    ToolTypeRepository toolTypeRepository = new ToolTypeRepository();
    ToolRepository toolRepository = new ToolRepository(toolTypeRepository);
    CheckoutProcessor checkoutProcessor = new CheckoutProcessor(toolRepository);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy", Locale.ENGLISH);

    //Labor Day - Monday Sept 7th   2020
    //Labor Day - Monday Sept 7th   2015
    //Labor Day - Monday Sept 1st   2014   
    //Labor Day - Monday Sept 4th   2023
    
    //4th of July - Saturday        2020
    //4th of July - Saturday        2015
    //4th of July - Sunday          2021
    //4th of July - Monday          2022
    //4th of July - Friday          2014

    @Test
    public void test1(){
        try{
            RentalAgreement ra = checkoutProcessor.checkout("JAKR", 5, 101, LocalDate.parse("09/03/15", formatter));
        }
        catch(Exception ex){
            assertEquals("Discount percentage should be between 0 and 100", ex.getMessage());
        }
    }

    @Test
    public void test2() throws Exception {
        //include the 4th (on saturday, observed on friday)
        RentalAgreement ra = checkoutProcessor.checkout("LADW", 3, 10, LocalDate.parse("07/02/20", formatter));
        assertEquals("LADW", ra.getToolCode());
        assertEquals("Ladder", ra.getToolType());
        assertEquals("Werner", ra.getBrand());
        assertEquals(3, ra.getRentalDays());
        assertEquals(LocalDate.parse("07/02/20", formatter), ra.getCheckoutDate());
        assertEquals(LocalDate.parse("07/05/20", formatter), ra.getDueDate());
        assertEquals(1.99, ra.getDailyRentalCharge(), 0);
        assertEquals(2, ra.getChargeDays());
        assertEquals(3.98, ra.getPreDiscountCharge(), 0);
        assertEquals(10, ra.getDiscountPercent());
        assertEquals(0.40, ra.getDiscountAmount(), 0);
        assertEquals(3.58, ra.getFinalCharge(), 0);
        ra.printRentalAgreementToConsole();
    }

    @Test
    public void test3()  throws Exception{
        //include the 4th (on saturday, observed on friday)
        RentalAgreement ra = checkoutProcessor.checkout("CHNS", 5, 25, LocalDate.parse("07/02/15", formatter));
        assertEquals("CHNS", ra.getToolCode());
        assertEquals("Chainsaw", ra.getToolType());
        assertEquals("Stihl", ra.getBrand());
        assertEquals(5, ra.getRentalDays());
        assertEquals(LocalDate.parse("07/02/15", formatter), ra.getCheckoutDate());
        assertEquals(LocalDate.parse("07/07/15", formatter), ra.getDueDate());
        assertEquals(1.49, ra.getDailyRentalCharge(), 0);
        assertEquals(3, ra.getChargeDays());
        assertEquals(4.47, ra.getPreDiscountCharge(), 0);
        assertEquals(25, ra.getDiscountPercent());
        assertEquals(1.12, ra.getDiscountAmount(), 0);
        assertEquals(3.35, ra.getFinalCharge(), 0);
        ra.printRentalAgreementToConsole();
    }
    
    @Test
    public void test4()  throws Exception{
        //include labor day
        RentalAgreement ra = checkoutProcessor.checkout("JAKD", 6, 0, LocalDate.parse("09/03/15", formatter));
        assertEquals("JAKD", ra.getToolCode());
        assertEquals("Jackhammer", ra.getToolType());
        assertEquals("DeWalt", ra.getBrand());
        assertEquals(6, ra.getRentalDays());
        assertEquals(LocalDate.parse("09/03/15", formatter), ra.getCheckoutDate());
        assertEquals(LocalDate.parse("09/09/15", formatter), ra.getDueDate());
        assertEquals(2.99, ra.getDailyRentalCharge(), 0);
        assertEquals(3, ra.getChargeDays());
        assertEquals(8.97, ra.getPreDiscountCharge(), 0);
        assertEquals(0, ra.getDiscountPercent());
        assertEquals(0, ra.getDiscountAmount(), 0);
        assertEquals(8.97, ra.getFinalCharge(), 0);
        ra.printRentalAgreementToConsole();
    }
    
    @Test
    public void test5()  throws Exception{
        //include the 4th (on saturday, observed on friday)
        RentalAgreement ra = checkoutProcessor.checkout("JAKR", 9, 0, LocalDate.parse("07/02/15", formatter));
        assertEquals("JAKR", ra.getToolCode());
        assertEquals("Jackhammer", ra.getToolType());
        assertEquals("Ridgid", ra.getBrand());
        assertEquals(9, ra.getRentalDays());
        assertEquals(LocalDate.parse("07/02/15", formatter), ra.getCheckoutDate());
        assertEquals(LocalDate.parse("07/11/15", formatter), ra.getDueDate());
        assertEquals(2.99, ra.getDailyRentalCharge(), 0);
        assertEquals(6, ra.getChargeDays());
        assertEquals(17.94, ra.getPreDiscountCharge(), 01);
        assertEquals(0, ra.getDiscountPercent());
        assertEquals(0, ra.getDiscountAmount(), 0);
        assertEquals(17.94, ra.getFinalCharge(), 0);
        ra.printRentalAgreementToConsole();
    }
    
    @Test
    public void test6()  throws Exception{
        //include the 4th (on saturday, observed on friday)
        RentalAgreement ra =  checkoutProcessor.checkout("JAKR", 4, 50, LocalDate.parse("07/02/20", formatter));
        assertEquals("JAKR", ra.getToolCode());
        assertEquals("Jackhammer", ra.getToolType());
        assertEquals("Ridgid", ra.getBrand());
        assertEquals(4, ra.getRentalDays());
        assertEquals(LocalDate.parse("07/02/20", formatter), ra.getCheckoutDate());
        assertEquals(LocalDate.parse("07/06/20", formatter), ra.getDueDate());
        assertEquals(2.99, ra.getDailyRentalCharge(), 0);
        assertEquals(1, ra.getChargeDays());
        assertEquals(2.99, ra.getPreDiscountCharge(), 0);
        assertEquals(50, ra.getDiscountPercent());
        assertEquals(1.50, ra.getDiscountAmount(), 0);
        assertEquals(1.49, ra.getFinalCharge(), 0);
        ra.printRentalAgreementToConsole();
    }

    @Test
    public void testNullToolCode(){
        try {
            checkoutProcessor.checkout(null, 4, 50, LocalDate.parse("07/02/20", formatter));
        } catch (Exception ex) {
            assertEquals("Could not find tool, please input a valid toolCode", ex.getMessage());
        }
    }

    @Test
    public void testToolNotFound(){
        try {
            RentalAgreement ra =  checkoutProcessor.checkout("DNE", 4, 50, LocalDate.parse("07/02/20", formatter));
        } catch (Exception ex) {
            assertEquals("Could not find tool, please input a valid toolCode", ex.getMessage());
        }
    }

    @Test
    public void testNullToolType(){
        try {
            RentalAgreement ra =  checkoutProcessor.checkout("CHNW", 4, 50, LocalDate.parse("07/02/20", formatter));
        } catch (Exception ex) {
            assertEquals("Could not find tool type, please input a valid toolCode", ex.getMessage());
        }
    }

    @Test
    public void testNegativeRentalDays(){
        try {
            RentalAgreement ra =  checkoutProcessor.checkout("JAKR", -4, 50, LocalDate.parse("07/02/20", formatter));
        } catch (Exception ex) {
            assertEquals("Rental Day Count should not be less than 1", ex.getMessage());
        }

    }
    
    @Test
    public void testNegativeDiscount(){
        try {
            RentalAgreement ra =  checkoutProcessor.checkout("JAKR", 4, -50, LocalDate.parse("07/02/20", formatter));
        } catch (Exception ex) {
            assertEquals("Discount percentage should be between 0 and 100", ex.getMessage());
        }
    }

    @Test
    public void testSkippingLaborDay0DiscountJackhammer() throws Exception{
        //don't include labor day 
        RentalAgreement ra = checkoutProcessor.checkout("JAKD", 3, 0, LocalDate.parse("09/02/14", formatter));
        assertEquals("JAKD", ra.getToolCode());
        assertEquals("Jackhammer", ra.getToolType());
        assertEquals("DeWalt", ra.getBrand());
        assertEquals(3, ra.getRentalDays());
        assertEquals(LocalDate.parse("09/02/14", formatter), ra.getCheckoutDate());
        assertEquals(LocalDate.parse("09/05/14", formatter), ra.getDueDate());
        assertEquals(2.99, ra.getDailyRentalCharge(), 0);
        assertEquals(3, ra.getChargeDays());
        assertEquals(8.97, ra.getPreDiscountCharge(), 0);
        assertEquals(0, ra.getDiscountPercent());
        assertEquals(0, ra.getDiscountAmount(), 0);
        assertEquals(8.97, ra.getFinalCharge(), 0);
        ra.printRentalAgreementToConsole();
    }

    @Test
    public void testMonthBoundrayWithLaborDay15DiscountJackhammer() throws Exception {
        RentalAgreement ra = checkoutProcessor.checkout("JAKR", 5, 15, LocalDate.parse("08/30/14", formatter));
        assertEquals("JAKR", ra.getToolCode());
        assertEquals("Jackhammer", ra.getToolType());
        assertEquals("Ridgid", ra.getBrand());
        assertEquals(5, ra.getRentalDays());
        assertEquals(LocalDate.parse("08/30/14", formatter), ra.getCheckoutDate());
        assertEquals(LocalDate.parse("09/04/14", formatter), ra.getDueDate());
        assertEquals(2.99, ra.getDailyRentalCharge(), 0);
        assertEquals(3, ra.getChargeDays());
        assertEquals(8.97, ra.getPreDiscountCharge(), 0);
        assertEquals(15, ra.getDiscountPercent());
        assertEquals(1.35, ra.getDiscountAmount(), 0);
        assertEquals(7.62, ra.getFinalCharge(), 0);
        ra.printRentalAgreementToConsole();
    }

    @Test
    public void testFeburaryBoundaryNoDiscountChainsaw() throws Exception {
        RentalAgreement ra = checkoutProcessor.checkout("CHNS", 5, 0, LocalDate.parse("02/26/14", formatter));
        assertEquals("CHNS", ra.getToolCode());
        assertEquals("Chainsaw", ra.getToolType());
        assertEquals("Stihl", ra.getBrand());
        assertEquals(5, ra.getRentalDays());
        assertEquals(LocalDate.parse("02/26/14", formatter), ra.getCheckoutDate());
        assertEquals(LocalDate.parse("03/03/14", formatter), ra.getDueDate());
        assertEquals(1.49, ra.getDailyRentalCharge(), 0);
        assertEquals(3, ra.getChargeDays());
        assertEquals(4.47, ra.getPreDiscountCharge(), 0);
        assertEquals(0, ra.getDiscountPercent());
        assertEquals(0, ra.getDiscountAmount(), 0);
        assertEquals(4.47, ra.getFinalCharge(), 0);
        ra.printRentalAgreementToConsole();
    }

    @Test
    public void testFeburaryBoundaryNoDiscountLadderWeekendCharge() throws Exception {
        RentalAgreement ra = checkoutProcessor.checkout("LADW", 5, 0, LocalDate.parse("02/26/14", formatter));
        assertEquals("LADW", ra.getToolCode());
        assertEquals("Ladder", ra.getToolType());
        assertEquals("Werner", ra.getBrand());
        assertEquals(5, ra.getRentalDays());
        assertEquals(LocalDate.parse("02/26/14", formatter), ra.getCheckoutDate());
        assertEquals(LocalDate.parse("03/03/14", formatter), ra.getDueDate());
        assertEquals(1.99, ra.getDailyRentalCharge(), 0);
        assertEquals(5, ra.getChargeDays());
        assertEquals(9.95, ra.getPreDiscountCharge(), 0);
        assertEquals(0, ra.getDiscountPercent());
        assertEquals(0, ra.getDiscountAmount(), 0);
        assertEquals(9.95, ra.getFinalCharge(), 0);
        ra.printRentalAgreementToConsole();
    }

    @Test
    public void test3Month5DiscountChainsaw() throws Exception {
        RentalAgreement ra = checkoutProcessor.checkout("CHNS", 90, 5, LocalDate.parse("10/26/14", formatter));
        assertEquals("CHNS", ra.getToolCode());
        assertEquals("Chainsaw", ra.getToolType());
        assertEquals("Stihl", ra.getBrand());
        assertEquals(90, ra.getRentalDays());
        assertEquals(LocalDate.parse("10/26/14", formatter), ra.getCheckoutDate());
        assertEquals(LocalDate.parse("01/24/15", formatter), ra.getDueDate());
        assertEquals(1.49, ra.getDailyRentalCharge(), 0);
        assertEquals(66, ra.getChargeDays());
        assertEquals(98.34, ra.getPreDiscountCharge(), 0);
        assertEquals(5, ra.getDiscountPercent());
        assertEquals(4.92, ra.getDiscountAmount(), 0);
        assertEquals(93.42, ra.getFinalCharge(), 0);
        ra.printRentalAgreementToConsole();
    }

    @Test
    public void testJuly4OnFridayWithHolidayChargeNoWeekendCharge() throws Exception {
        RentalAgreement ra = checkoutProcessor.checkout("CHNS", 6, 0, LocalDate.parse("07/03/14", formatter));
        assertEquals("CHNS", ra.getToolCode());
        assertEquals("Chainsaw", ra.getToolType());
        assertEquals("Stihl", ra.getBrand());
        assertEquals(6, ra.getRentalDays());
        assertEquals(LocalDate.parse("07/03/14", formatter), ra.getCheckoutDate());
        assertEquals(LocalDate.parse("07/09/14", formatter), ra.getDueDate());
        assertEquals(1.49, ra.getDailyRentalCharge(), 0);
        assertEquals(4, ra.getChargeDays());
        assertEquals(5.96, ra.getPreDiscountCharge(), 0);
        assertEquals(0, ra.getDiscountPercent());
        assertEquals(0, ra.getDiscountAmount(), 0);
        assertEquals(5.96, ra.getFinalCharge(), 0);
        ra.printRentalAgreementToConsole();
    }

    @Test
    public void testJuly4OnFridayWithoutHolidayChargeOrWeekendCharge() throws Exception {
        RentalAgreement ra = checkoutProcessor.checkout("JAKD", 6, 0, LocalDate.parse("07/03/14", formatter));
        assertEquals("JAKD", ra.getToolCode());
        assertEquals("Jackhammer", ra.getToolType());
        assertEquals("DeWalt", ra.getBrand());
        assertEquals(6, ra.getRentalDays());
        assertEquals(LocalDate.parse("07/03/14", formatter), ra.getCheckoutDate());
        assertEquals(LocalDate.parse("07/09/14", formatter), ra.getDueDate());
        assertEquals(2.99, ra.getDailyRentalCharge(), 0);
        assertEquals(3, ra.getChargeDays());
        assertEquals(8.97, ra.getPreDiscountCharge(), 0);
        assertEquals(0, ra.getDiscountPercent());
        assertEquals(0, ra.getDiscountAmount(), 0);
        assertEquals(8.97, ra.getFinalCharge(), 0);
        ra.printRentalAgreementToConsole();
    }

    @Test
    public void testJuly4OnFridayWithWeekendChargeNoHolidayCharge() throws Exception {
        RentalAgreement ra = checkoutProcessor.checkout("LADW", 6, 0, LocalDate.parse("07/03/14", formatter));
        assertEquals("LADW", ra.getToolCode());
        assertEquals("Ladder", ra.getToolType());
        assertEquals("Werner", ra.getBrand());
        assertEquals(6, ra.getRentalDays());
        assertEquals(LocalDate.parse("07/03/14", formatter), ra.getCheckoutDate());
        assertEquals(LocalDate.parse("07/09/14", formatter), ra.getDueDate());
        assertEquals(1.99, ra.getDailyRentalCharge(), 0);
        assertEquals(5, ra.getChargeDays());
        assertEquals(9.95, ra.getPreDiscountCharge(), 0);
        assertEquals(0, ra.getDiscountPercent());
        assertEquals(0, ra.getDiscountAmount(), 0);
        assertEquals(9.95, ra.getFinalCharge(), 0);
        ra.printRentalAgreementToConsole();
    }
}
