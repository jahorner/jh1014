
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.toolshop.CheckoutProcessor;
import com.toolshop.data.ToolTypeRepository;
import com.toolshop.data.ToolRepository;
import com.toolshop.model.RentalAgreement;

public class CheckoutProcessTest {

    ToolRepository toolRepository = new ToolRepository();
    ToolTypeRepository toolTypeRepository = new ToolTypeRepository();
    CheckoutProcessor checkoutProcessor = new CheckoutProcessor(toolRepository, toolTypeRepository);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy", Locale.ENGLISH);
    
    @Test
    public void test1(){
        try{
            checkoutProcessor.checkout("JAKR", 5, 101, LocalDate.parse("09/03/15", formatter));
        }
        catch(Exception e){
            assertEquals("Discount percentage should be between 0 and 100", e.getMessage());
        }
    }

    @Test
    public void test2() throws Exception{
        RentalAgreement ra = checkoutProcessor.checkout("LADW", 3, 10, LocalDate.parse("07/02/20", formatter));
        assertEquals(ra.getCheckoutDate(), LocalDate.parse("07/02/20", formatter));
        assertEquals(ra.getDueDate(), LocalDate.parse("07/05/20", formatter));
        assertEquals(ra.getToolCode(), "LADW");
        System.out.println(ra.getPrintableRentalAgreement());
    }
    @Test
    public void test3()  throws Exception{
        RentalAgreement ra = checkoutProcessor.checkout("CHNS", 5, 25, LocalDate.parse("07/02/15", formatter));
        assertEquals(ra.getCheckoutDate(), LocalDate.parse("07/02/20", formatter));
        assertEquals(ra.getDueDate(), LocalDate.parse("07/05/20", formatter));
        assertEquals(ra.getToolCode(), "CHNS");
    }
    @Test
    public void test4()  throws Exception{
        RentalAgreement ra = checkoutProcessor.checkout("JAKD", 6, 0, LocalDate.parse("09/03/15", formatter));
        assertEquals(ra.getCheckoutDate(), LocalDate.parse("07/02/20", formatter));
        assertEquals(ra.getDueDate(), LocalDate.parse("07/05/20", formatter));
        assertEquals(ra.getToolCode(), "JAKD");
    }
    @Test
    public void test5()  throws Exception{
        RentalAgreement ra = checkoutProcessor.checkout("JAKR", 9, 0, LocalDate.parse("07/02/15", formatter));
        assertEquals(ra.getCheckoutDate(), LocalDate.parse("07/02/20", formatter));
        assertEquals(ra.getDueDate(), LocalDate.parse("07/05/20", formatter));
        assertEquals(ra.getToolCode(), "JAKR");
    }
    @Test
    public void test6()  throws Exception{
        RentalAgreement ra =  checkoutProcessor.checkout("JAKR", 4, 50, LocalDate.parse("07/02/20", formatter));
        assertEquals(ra.getCheckoutDate(), LocalDate.parse("07/02/20", formatter));
        assertEquals(ra.getDueDate(), LocalDate.parse("07/05/20", formatter));
        assertEquals(ra.getToolCode(), "JAKR");
    }
}
