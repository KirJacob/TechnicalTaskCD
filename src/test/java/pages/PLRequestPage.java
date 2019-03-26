package pages;

import enums.LoanStep;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PLRequestPage extends BasePage{

    public PLRequestPage(WebDriver driver){
        this.driver = driver;
    }

    //Common and Personal

    @FindBy(xpath = "//h2[text()='Personal Loan Request']")
    public WebElement personalLoanRequestHeader;

    @FindBy(xpath = "//*[@class='base-form' and contains(@style,'block')]")
    public WebElement personalFormArea;

    @FindBy(xpath = "(//li[contains(@class,'results-dept')])[1]")
    public WebElement optionsPopup;

    public WebElement getStepTitleByName(LoanStep step, boolean isActive){
        String activePart = "";
        if (isActive) activePart = " active";
        String xpathVal = "//*[@class='steps-wrapper']" +
                "//*[contains(@class,'step " + step.getClassName() + activePart
                + "')]//div[@class='title' and text()='" + step.getVisibleName() + "']";
        return driver.findElement(By.xpath(xpathVal));
    }

    public WebElement getOptionByName(String optionName){
        String xpathVal = "//*[@role='option' and text()='" + optionName + "']";
        return driver.findElement(By.xpath(xpathVal));
    }

    private String getFieldXpathByName(String fieldName){
        String xpathVal = "//*[@class='base-form' and contains(@style,'block')]//*[contains(@class,'form-group')]" +
                "/label[contains(text(),'" + fieldName + "')]";
        return xpathVal;
    }

    public WebElement getFieldDdByName(String fieldName){
        String xpathVal = getFieldXpathByName(fieldName) + "/following-sibling::span//span[@role='presentation']";
        return driver.findElement(By.xpath(xpathVal));
    }

    public WebElement getFieldInputByName(String fieldName){
        String xpathVal = getFieldXpathByName(fieldName) + "/following-sibling::span/input";
        return driver.findElement(By.xpath(xpathVal));
    }

    public WebElement getFieldTooltipByName(String fieldName){
        String xpathVal = getFieldXpathByName(fieldName) + "/following-sibling::span";
        return driver.findElement(By.xpath(xpathVal));
    }

    public WebElement getContinueBtn(boolean isEnabled){
        String isEnabledPart = "";
        if (isEnabled) isEnabledPart = "not(@disabled='disabled')";
        else  isEnabledPart = "@disabled='disabled'";
        String xpathVal = "//*[@class='base-form' and contains(@style,'block')]" +
                "//button[@data-ga-action='Continue' and " + isEnabledPart + "]";
        return driver.findElement(By.xpath(xpathVal));
    }

    public WebElement getStepAreaWithDisplayOpt(LoanStep step, boolean isDisplayed){
        String displayPart = "";
        if (isDisplayed) displayPart = "block";
        else displayPart = "none";
        String xpathVal = "//fieldset[@data-step='" + step.getClassName() + "']" +
                "/ancestor::div[@class='base-form' and contains(@style,'" + displayPart + "')]";
        return driver.findElement(By.xpath(xpathVal));
    }

    //Profile

    @FindBy(xpath = "(//*[contains(@class,'pac-container')]//*[@class='pac-item'])[1]")
    public WebElement getExactAddress;

    public WebElement getFieldAddressByName(String fieldName){
        String xpathVal = "//*[@class='base-form' and contains(@style,'block')]//*[@class='form-group']" +
                "//*[@class='bootstrap-address']//label[contains(text(),'" + fieldName + "')]/following-sibling::span/input";
        return driver.findElement(By.xpath(xpathVal));
    }

    private String getSignupBlockXpath(){
        String xpathVal = "//*[@class='prequalSignup']";
        return xpathVal;
    }

    public By getSignupFld(String fieldName){ //By instead of WebElement since we need to use it in wait
        String xpathVal = getSignupBlockXpath() + "//label[contains(text(),'" + fieldName + "')]"
                + "/following-sibling::*//input[1]";
        return By.xpath(xpathVal);
    }

    public WebElement getFindMyRatesBtn(boolean isEnabled){
        String isEnabledPart = "";
        if (isEnabled) isEnabledPart = "and not(@disabled='disabled')";
        else  isEnabledPart = "@disabled='disabled'";

        String xpathVal = "//*[@class='next-step finish']" +
                "//button[contains(text(),'Agree and Find My Rates')" + isEnabledPart + "]";
        return driver.findElement(By.xpath(xpathVal));
    }

}