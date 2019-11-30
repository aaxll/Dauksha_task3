import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.OptionalDataException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class MainClass {
    public static void main(String[] args) {
        System.setProperty(
                "webdriver.chrome.driver",
                "C:/testing/chromedriver_win32/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://google.com/?hl=ru-BY");
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("selenium");
        searchBox.submit();
        WebElement imagesUrl = driver.findElement(By.xpath("//a[contains(text(),'Картинки')] "));
        imagesUrl.click();
        WebElement toolsButton = driver.findElement(By.xpath("//a[contains(text(),'Инструменты')]"));
        toolsButton.click();

        List<WebElement> filters = driver.findElements(By.className("hdtb-mn-hd"));
        Optional<WebElement> filterByType = filters.stream().filter(elem -> {
            String titleAttr = elem.getAttribute("aria-label");
            return titleAttr != null && titleAttr.equals("Тип");

        }).findFirst();

        if (!filterByType.isPresent())
            throw new NoSuchElementException("Element is not found!");
        System.out.println(filterByType.get());

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(filterByType.get()));

        filterByType.get().click();

        WebElement filterByTypeXpath = driver.findElement(By.xpath("//*[contains(@class, hdtb-mn-hd) and @aria-label='Тип']"));
        filterByTypeXpath.click();

        WebElement filterByTypeCss = driver.findElement(By.cssSelector(".hdtb-mn-hd[aria-label='Тип']"));
        filterByTypeCss.click();

        driver.get("http://htmlbook.ru/");

        //через CSS

        WebElement findSearchBox01 = driver.findElement(By.cssSelector("input"));
        findSearchBox01.sendKeys("Test01");

        WebElement findSearchBox02 = driver.findElement(By.cssSelector("aside input"));
        findSearchBox02.sendKeys("Test02");

        WebElement allTagsUrl = driver.findElement(By.cssSelector("div> div> ul > li > a"));
        allTagsUrl.click();

        WebElement searchBox03 = driver.findElement(By.cssSelector("#cse-search-box"));
        System.out.println(searchBox03.getAttribute("action"));

        WebElement logoByClass = driver.findElement(By.cssSelector(".logo"));
        logoByClass.click();

        WebElement logoAlt = driver.findElement(By.cssSelector("img[alt]"));
        System.out.println(logoAlt.getAttribute("alt"));

        //через Xpath

        WebElement findSearchBox01Xpath = driver.findElement(By.xpath("//input"));
        findSearchBox01Xpath.sendKeys("Test01");

        WebElement findSearchBox02Xpath = driver.findElement(By.xpath("//aside//input"));
        findSearchBox02Xpath.sendKeys("Test02");

        WebElement allTagsUrlXpath = driver.findElement(By.xpath("//div/div/ul/li/a"));
        allTagsUrlXpath.click();

        WebElement searchTextXpath = driver.findElement(By.xpath(".//*[text()='<blockquote>']/.."));
        System.out.println(searchTextXpath.getAttribute("class"));

        WebElement logoByClassXpath = driver.findElement(By.xpath("//img[@alt='htmlbook.ru']"));
        logoByClassXpath.click();

        WebElement logoAltXpath = driver.findElement(By.xpath("//img[@alt]"));
        System.out.println(logoAltXpath.getAttribute("alt"));

        driver.quit();

    }
}
