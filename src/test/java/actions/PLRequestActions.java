package actions;

import enums.LoanStep;
import helper.Common;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.PLRequestPage;

@Slf4j
public class PLRequestActions extends BaseActions {

    private PLRequestPage plRequestPage;

    public PLRequestActions(WebDriver driver){
        this.driver = driver;
        this.plRequestPage = PageFactory.initElements(driver, PLRequestPage.class);
    }

    public PLRequestActions setDdField(String fieldName, String fieldValue){
        plRequestPage.getFieldDdByName(fieldName).click();
        Common.waitForVisible(driver, plRequestPage.optionsPopup, 5);
        plRequestPage.getOptionByName(fieldValue).click();
        Common.waitForDisappear(driver, plRequestPage.optionsPopup, 5);
        return this;
    }
    public PLRequestActions setInputField(String dateField, String dateValue) {
        plRequestPage.getFieldInputByName(dateField).click();
        plRequestPage.getFieldInputByName(dateField).sendKeys(dateValue);
        return this;
    }

    public PLRequestActions setAddressField(String addressField, String addressText) throws InterruptedException {
        plRequestPage.getFieldAddressByName(addressField).click();
        plRequestPage.getFieldAddressByName(addressField).sendKeys(addressText);
        Common.waitForVisible(driver, plRequestPage.getExactAddress, 5);
        plRequestPage.getExactAddress.click();
        return this;
    }

    public PLRequestActions setSignUpBlockField(String fieldName, String text){
        log.info(">>> enter {}", text);
        driver.findElement(plRequestPage.getSignupFld(fieldName)).click();
        driver.findElement(plRequestPage.getSignupFld(fieldName)).sendKeys(text);
        return this;
    }

    public PLRequestActions waitForSignUpPwd(String pwdFieldName){
        Common.waitForVisible(driver, plRequestPage.getSignupFld(pwdFieldName), 10000);
        return this;
    }

    public PLRequestActions clickContinue() throws InterruptedException {
        Common.waitForVisible(driver, plRequestPage.getContinueBtn(true), 10);
        plRequestPage.getContinueBtn(true).click();
        Common.sleep(3000);//FIXME
        Common.waitForVisible(driver, plRequestPage.getStepAreaWithDisplayOpt(LoanStep.PROFILE, true), 5000);
        return this;
    }

    public PLRequestActions verifyTooltipText(String fieldName, String tooltipText){
        Assert.assertEquals("verify tooltiptext", tooltipText, plRequestPage.getFieldTooltipByName(fieldName).getText());
        return this;
    }

    public PLRequestActions clickAgreeAndFind(){
        Common.waitForVisible(driver, plRequestPage.getFindMyRatesBtn(true), 10);
        plRequestPage.getFindMyRatesBtn(true).click();
        return this;
    }

    public PLRequestActions sleep(int timeout) throws InterruptedException {
        System.out.println("SLEEP FOR " + timeout);
        Common.sleep(timeout);
        return this;
    }

    public PLRequestActions logger(String logtext){
        log.info(logtext);
        return this;
    }
}
