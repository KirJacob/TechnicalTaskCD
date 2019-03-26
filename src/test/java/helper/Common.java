package helper;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Common {

    public static void setWebDriverByOS(){
        String osName = System.getProperty("os.name");
        switch (osName){
            case "Mac OS X":
                System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver_mac64");
                break;
            case "Linux":
                System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver_linux64");
                break;
            case "Windows 10":
                System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver_win32.exe");
                break;
            default:
                break;
        }
    }

    //todo not used yet
    public static void hoverElement(WebDriver driver, WebElement element){
        Actions builder = new Actions(driver);
        builder.moveToElement(element).perform();
    }

    public static void sleep(int timeout) throws InterruptedException {
        Thread.sleep(timeout);
    }

    //this wait doesnt wait for element if its not presented use next wait with BY
    public static void waitForVisible(WebDriver driver, WebElement element, int timeoutSec){
        WebDriverWait wait = new WebDriverWait(driver, timeoutSec);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    //this wait wait for element if its not presented, perhaps we can rewrite all elements to BY instead of WEBELEMENT
    public static void waitForVisible(WebDriver driver, By by, int timeoutSec){
        WebDriverWait wait = new WebDriverWait(driver, timeoutSec);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void waitForDisappear(WebDriver driver, WebElement element, int timeoutSec){
        List<WebElement>list = new ArrayList<>();
        list.add(element);
        new WebDriverWait(driver, timeoutSec).until(ExpectedConditions.invisibilityOfAllElements(list));
    }

    public static void waitForLoad(WebDriver driver) {
        new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    public static boolean isElementPresent(WebDriver driver, WebElement element){
        boolean result = false;
        try{
            if (element.isDisplayed()) result = true;
        }catch (NoSuchElementException e){
            result = false;
        }
        return result;
    }

    public static String getEmailWithTimeStamp(String email){
        //TImeStamp Pattern: yourEmail+Jan01+1125@domain.com
        LocalDateTime localTime = LocalDateTime.now();
        String timeData = localTime.getHour() + "" + localTime.getMinute();
        String monthVal = localTime.getMonth().toString().substring(0,3).toLowerCase();
        String monthData = monthVal.replace(monthVal.substring(0, 1),monthVal.substring(0,1).toUpperCase()) +
                "" + String.format("%02d", localTime.getMonthValue());
        return email.replace("@","+" + monthData + "+" + timeData + "@");
    }
}
