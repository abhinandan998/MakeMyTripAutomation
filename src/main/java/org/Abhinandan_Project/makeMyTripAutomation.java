package org.Abhinandan_Project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

public class makeMyTripAutomation {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.makemytrip.com/");


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        By closeModalLocator = By.xpath("//span[@data-cy='closeModal']");
        WebElement closeModal = wait.until(ExpectedConditions.elementToBeClickable(closeModalLocator));
        closeModal.click();

        By closeModal2Locator = By.xpath("//img[@alt='minimize']");
        WebElement closeModal2 = wait.until(ExpectedConditions.elementToBeClickable(closeModal2Locator));
        closeModal2.click();

        By sourceCityLocator = By.xpath("//label[@for='fromCity']");
        WebElement sourceCity = wait.until(ExpectedConditions.visibilityOfElementLocated(sourceCityLocator));
        sourceCity.click();

        By sourceCityTExtBoxLocator = By.xpath("//input[@placeholder='From']");
        WebElement sourceCityTextBox = wait.until(ExpectedConditions.visibilityOfElementLocated(sourceCityTExtBoxLocator));
        sourceCityTextBox.sendKeys("Pune");

        By fromCitySuggestionListLocator = By.xpath("//p[contains(text(),'SUGGESTIONS')]/../../ul/li");
        boolean state = wait.until(ExpectedConditions.and(ExpectedConditions.visibilityOfElementLocated(fromCitySuggestionListLocator), ExpectedConditions.numberOfElementsToBeLessThan(fromCitySuggestionListLocator,12)));
        List<WebElement> forCitySuggestionList = null;

        if(state)
        {
            forCitySuggestionList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(fromCitySuggestionListLocator));

        }
        for(WebElement suggewstion: forCitySuggestionList)
        {
            System.out.println(suggewstion.getText());
        }

        forCitySuggestionList.get(0).click();


        By destCityLocator = By.xpath("//label[@for='toCity']");
        WebElement destCity = wait.until(ExpectedConditions.elementToBeClickable(destCityLocator));
        destCity.click();

        By destinationCityTExtBoxLocator = By.xpath("//input[@placeholder='To']");
        WebElement destinationCityTextBox = wait.until(ExpectedConditions.visibilityOfElementLocated(destinationCityTExtBoxLocator));
        destinationCityTextBox.sendKeys("Kolkata");

        By toSuggestionListLocator = By.xpath("//font[contains(text(),'*This visa information applies to Indian passport holders')]/ancestor::div[contains(@class,'react-autosuggest')]/ul/li");
        state =   wait.until(ExpectedConditions.and(ExpectedConditions.visibilityOfElementLocated(toSuggestionListLocator), ExpectedConditions.numberOfElementsToBeMoreThan(toSuggestionListLocator, 10)));

        List<WebElement> toSuggestionList = null;
        if(state)
        {
            toSuggestionList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(toSuggestionListLocator));

        }
        toSuggestionList.get(0).click();


        LocalDate targetdate = LocalDate.now();
        targetdate = targetdate.plusDays(5);
        String targetMonth = targetdate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        int targetYear = targetdate.getYear();
        int targetDay = targetdate.getDayOfMonth();

        By calenderMonthLocator= By.xpath("//div[contains(text(),'"+targetMonth+ " "+targetYear+ "')]/ancestor::div[@class='DayPicker-Month']");

        WebElement calenderMonth = wait.until(ExpectedConditions.visibilityOfElementLocated(calenderMonthLocator));
        By dateLocator = By.xpath(".//p[text()='"+targetDay+"']/ancestor::div[contains(@class,'DayPicker-Day')]");
        WebElement date = calenderMonth.findElement(dateLocator);
        date.click();

    }
}
