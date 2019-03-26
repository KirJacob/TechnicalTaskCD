package scenarios;

import actions.MainActions;
import actions.PLRequestActions;
import category.SmokeTestCategory;
import enums.LoanStep;
import helper.Common;
import lombok.extern.slf4j.Slf4j;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.support.PageFactory;
import pages.MainPage;
import pages.PLRequestPage;

import static helper.Common.*;
import static enums.NavigationTab.*;

@Category({SmokeTestCategory.class})
@Slf4j
public class MainTest extends BaseTest{

    private MainPage mainPage;
    private PLRequestPage plRequestPage;

    private static MainActions mainActions;
    private static PLRequestActions plRActions;

    //FIXME You can store the test data in an external file (properties file, txt file, Excel file, etc.)

    @BeforeClass
    public static void beforeAll() throws InterruptedException {
        driver.get(mainURL);
    }

    @Before
    public void preparation(){
        //nothing here for now
    }

    @Test
    public void t01FirstTest() throws InterruptedException {
        log.info("Click on the Personal Loans tab and enter a loan amount ($15k should be good)");
        mainPage = PageFactory.initElements(driver, MainPage.class);
        mainActions = new MainActions(driver);
        mainActions
                .openNavigationTab(PERSONAL_LOAN)
                .findMyRate(15000);

        log.info("Personal Step");
        log.info("- Verify Personal step header text");
        plRequestPage = PageFactory.initElements(driver, PLRequestPage.class);
        plRActions = new PLRequestActions(driver);
        Assert.assertTrue(Common.isElementPresent(driver, plRequestPage.getStepTitleByName(LoanStep.PERSONAL, true)));

        //todo - when there will be more tests we can create Form Model Object with field Objects etc
        log.info("- Complete input fields as well as verify the “Annual income” tool-tip’s text (Use fake test data not your own)");

        plRActions
                .setDdField("Loan purpose", "Major purchase")
                .setDdField("Highest level of completed education", "High school")
                .setDdField("Current employment status", "Self Employed")
                .setInputField("Date of birth", "03131945")
                .setInputField("Annual income (before tax)", Integer.toString(12000))
                .verifyTooltipText("Annual income (before tax)", "What should I include?")
                .setDdField("Estimate your credit score", "Fair (640-699)")
                .clickContinue() //fixme remove sleep here

                .logger("Profile Step")
                .logger("- Verify Profile step header text")
                .logger("- Complete input fields as well as verify the three tool-tip’s text (Use fake test data here as well)")
                .setInputField("First name", "John")
                .setInputField("Last name", "Doe")
                .setInputField("Primary phone number", "0961234567")
                .setDdField("Housing status", "Rent")
                .setAddressField("Permanent address", "Maidan Hawallai, Ada Street, Cincinnati, OH, USA") //.setInputField("Permanent address", "Maidan Nezalezhnosti 1")
                .setDdField("Citizenship status", "H1 Visa Holder")
                .setInputField("Social security number", "123456789")
                .logger("- For Email use the following format yourEmail+Jan01+1125@domain.com (your email + current date + current time @ domain name)")

                .setSignUpBlockField("Email Address", Common.getEmailWithTimeStamp("autoqa.yakov@gmail.com"))
                .waitForSignUpPwd("Choose Password")
                .setSignUpBlockField("Choose Password", "Qwertyui9")
                .logger("- Click on the ‘Find My Rates’ button to submit")
                .clickAgreeAndFind()
                .sleep(10000);//to see in UI that it searches for offers
    }

    @AfterClass
    public static void afterAll(){
        driver.quit();
    }

}
