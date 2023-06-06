package ru.praktikum.sprint8;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumGenericTest {
    private WebDriver driver;
    private MetroHomePage metroPage;
    @Before
    public void setUp(){
        driver = new ChromeDriver();
        driver.get("https://qa-metro.stand-2.praktikum-services.ru/");
        metroPage = new MetroHomePage(driver);
        metroPage.waitForLoadHomePage();
    }

    @Test
    public void mainTestCheckFromStation(){
        driver.findElement(By.className("select_metro__button")).click();
        driver.findElement(By.xpath(".//*[text()='Санкт-Петербург']")).click();

        driver.findElement(By.xpath(".//input[@placeholder='Откуда']")).sendKeys("Проспект Ветеранов", Keys.DOWN, Keys.ENTER);
        driver.findElement(By.xpath(".//input[@placeholder='Куда']")).sendKeys("Достоевская", Keys.DOWN, Keys.ENTER);

        int len = driver.findElements(By.className("route-details-block__station-list-item")).size();
        Assert.assertEquals("Проспект Ветеранов",driver.findElements(By.className("route-details-block__station-list-item")).get(0).getText());

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[text()='Получить ссылку на маршрут']")));
    }

    @Test
    public void mainTestCheckToStation(){
        driver.findElement(By.className("select_metro__button")).click();
        driver.findElement(By.xpath(".//*[text()='Санкт-Петербург']")).click();

        driver.findElement(By.xpath(".//input[@placeholder='Откуда']")).sendKeys("Проспект Ветеранов", Keys.DOWN, Keys.ENTER);
        driver.findElement(By.xpath(".//input[@placeholder='Куда']")).sendKeys("Достоевская", Keys.DOWN, Keys.ENTER);

        int len = driver.findElements(By.className("route-details-block__station-list-item")).size();
        Assert.assertEquals("Достоевская",driver.findElements(By.className("route-details-block__station-list-item")).get(len - 1).getText());

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[text()='Получить ссылку на маршрут']")));
    }
    @After
    public void teardown(){
        driver.quit();
    }
}
