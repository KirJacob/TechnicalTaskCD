package pages;

import enums.NavigationTab;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage extends BasePage{

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    public WebElement getNavigationTab(NavigationTab navTab){
        String xpathVal = "//*[@class='" + navTab.getNavigationTabClassName() + "']";
        return driver.findElement(By.xpath(xpathVal));
    }

    public WebElement getNavigationTabSelected(NavigationTab navTab){
        String xpathVal = "//*[@class='" + navTab.getNavigationTabClassName() + " selected']";
        return driver.findElement(By.xpath(xpathVal));
    }

    public String getLoanAmountAreaXpath(){
        return "//*[contains(@class,'extended-find-my-rate')]";
    }

    public WebElement getLoanAmountFld(){
        String xpathVal = getLoanAmountAreaXpath() + "//input";
        return driver.findElement(By.xpath(xpathVal));
    };

    public WebElement getFindMyRateBtn(){
        String xpathVal = getLoanAmountAreaXpath() + "//button[contains(@class,'hidden')]";
        return driver.findElement(By.xpath(xpathVal));
    };

}
