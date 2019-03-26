package actions;

import enums.NavigationTab;
import helper.Common;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.MainPage;
import pages.PLRequestPage;

import static helper.Common.waitForVisible;

public class MainActions extends BaseActions {

    private MainPage mainPage;

    public MainActions(WebDriver driver){
        this.driver = driver;
        this.mainPage = PageFactory.initElements(driver, MainPage.class);
    }

    public MainActions openNavigationTab(NavigationTab navTab) throws InterruptedException {
        Common.hoverElement(driver, mainPage.getNavigationTab(navTab));
        mainPage.getNavigationTab(navTab).click();
        waitForVisible(driver, mainPage.getNavigationTabSelected(navTab), 5);
        return this;
    }

    public MainActions findMyRate(int amount){
        mainPage.getLoanAmountFld().click();
        mainPage.getLoanAmountFld().sendKeys(Integer.toString(amount));
        mainPage.getFindMyRateBtn().click();
        Common.waitForLoad(driver);
        PLRequestPage personLoanRequestPage = PageFactory.initElements(driver, PLRequestPage.class);
        Common.waitForVisible(driver, personLoanRequestPage.personalFormArea, 5);
        return this;
    }
}
