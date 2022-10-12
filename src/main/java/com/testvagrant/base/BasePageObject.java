package com.testvagrant.base;

import com.testvagrant.config.FrameworkConfig;
import com.testvagrant.config.PropertyFileReader;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;
import static java.lang.String.format;

@Slf4j
public abstract class BasePageObject {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected AjaxElementLocatorFactory ajaxElementLocatorFactory;
    protected Properties test_data;
    private final Properties config;

    public BasePageObject(WebDriver driver) {
        this(driver, FrameworkConfig.getInstance().getConfigProperties());
    }

    public BasePageObject(WebDriver driver, Properties config) {
        this.driver = driver;
        this.config = config;
        wait = new WebDriverWait(driver, parseInt(config.getProperty("WEBDRIVERWAIT_TIMEOUT")),
                parseInt(config.getProperty("WEBDRIVERWAIT_POLL")));
        ajaxElementLocatorFactory = new AjaxElementLocatorFactory(driver, parseInt(config.getProperty("LOCATOR_FACTORY_TIMEOUT")));
        test_data = new PropertyFileReader(new File(String.format("%s/src/test/resources/test_data/data.properties", System.getProperty("user.dir"))))
                            .getPropertyFile();
        setTimeouts();

        isLoaded();
    }

    private void setTimeouts() {
        driver.manage().timeouts().implicitlyWait(parseInt(config.getProperty("IMPLICITWAIT_TIMEOUT")),
                TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(parseInt(config.getProperty("PAGE_LOAD_TIMEOUT")),
                TimeUnit.SECONDS);
    }

}
