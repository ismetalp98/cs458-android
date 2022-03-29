import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;


public class test2 {
    public static URL url;
    public static DesiredCapabilities capabilities;
    public static AndroidDriver<MobileElement> driver;

    @BeforeSuite
    public void setupAppium() throws MalformedURLException {
        final String URL_STRING = "http://127.0.0.1:4723/wd/hub";
        url = new URL(URL_STRING);
        String path = "C:\\Users\\user\\AndroidStudioProjects\\cs458-android\\app\\build\\intermediates\\apk\\debug\\app-debug.apk";
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", path);
        capabilities.setCapability("automationName", "UiAutomator2");

        //4
        driver = new AndroidDriver<MobileElement>(url, capabilities);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.resetApp();
    }

    @AfterSuite
    public void uninstallApp() throws InterruptedException {
        driver.quit();
    }

    @Test (enabled=true) public void testCase1() throws InterruptedException {
        boolean isSubmitVisible;

        MobileElement name = driver.findElement(By.id("name"));
        MobileElement day = driver.findElement(By.id("dd"));
        MobileElement month = driver.findElement(By.id("mm"));
        MobileElement year = driver.findElement(By.id("yyyy"));
        MobileElement city = driver.findElement(By.id("city"));
        MobileElement gender = driver.findElement(By.id("groupGender"));
        MobileElement vacType = driver.findElement(By.id("groupVaccination"));
        MobileElement checkASE = driver.findElement(By.id("checkase"));
        // MobileElement sideEff = driver.findElement(By.id("ase"));

        isSubmitVisible = driver.findElements(By.id("submit")).isEmpty();
        Assert.assertFalse(!isSubmitVisible);

        name.sendKeys("Monica June");
        day.sendKeys("12");
        month.sendKeys("01");
        year.sendKeys("1995");
        city.sendKeys("Ankara");
        driver.findElementById("female").click();
        driver.findElementById("biontech").click();
        driver.findElementById("yes_pos").click();

        isSubmitVisible = driver.findElements(By.id("submit")).isEmpty();
        Assert.assertFalse(isSubmitVisible);

        name.sendKeys("");
        isSubmitVisible = driver.findElements(By.id("submit")).isEmpty();
        Assert.assertFalse(!isSubmitVisible);

        name.sendKeys("Marco Pi");
        isSubmitVisible = driver.findElements(By.id("submit")).isEmpty();
        Assert.assertFalse(isSubmitVisible);

        day.sendKeys("");
        isSubmitVisible = driver.findElements(By.id("submit")).isEmpty();
        Assert.assertFalse(!isSubmitVisible);

        day.sendKeys("32");
        isSubmitVisible = driver.findElements(By.id("submit")).isEmpty();
        Assert.assertFalse(!isSubmitVisible);

        day.sendKeys("23");
        isSubmitVisible = driver.findElements(By.id("submit")).isEmpty();
        Assert.assertFalse(isSubmitVisible);

        month.sendKeys("13");
        isSubmitVisible = driver.findElements(By.id("submit")).isEmpty();
        Assert.assertFalse(!isSubmitVisible);

        month.sendKeys("11");
        isSubmitVisible = driver.findElements(By.id("submit")).isEmpty();
        Assert.assertFalse(isSubmitVisible);

        year.sendKeys("");
        isSubmitVisible = driver.findElements(By.id("submit")).isEmpty();
        Assert.assertFalse(!isSubmitVisible);

        year.sendKeys("20");
        isSubmitVisible = driver.findElements(By.id("submit")).isEmpty();
        Assert.assertFalse(!isSubmitVisible);

        year.sendKeys("1996");
        isSubmitVisible = driver.findElements(By.id("submit")).isEmpty();
        Assert.assertFalse(isSubmitVisible);

        city.sendKeys("");
        isSubmitVisible = driver.findElements(By.id("submit")).isEmpty();
        Assert.assertFalse(!isSubmitVisible);

        city.sendKeys("123");
        isSubmitVisible = driver.findElements(By.id("submit")).isEmpty();
        Assert.assertFalse(!isSubmitVisible);

        city.sendKeys("Kocaeli");
        isSubmitVisible = driver.findElements(By.id("submit")).isEmpty();
        Assert.assertFalse(isSubmitVisible);

        city.sendKeys("van");
        isSubmitVisible = driver.findElements(By.id("submit")).isEmpty();
        Assert.assertFalse(isSubmitVisible);

        checkASE.click();
        isSubmitVisible = driver.findElements(By.id("submit")).isEmpty();
        Assert.assertFalse(!isSubmitVisible);

        driver.findElement(By.id("ase")).sendKeys("Headache");
        isSubmitVisible = driver.findElements(By.id("submit")).isEmpty();
        Assert.assertFalse(isSubmitVisible);

        checkASE.click();
        isSubmitVisible = driver.findElements(By.id("submit")).isEmpty();
        Assert.assertFalse(isSubmitVisible);

        driver.findElementById("no_pos").click();
        isSubmitVisible = driver.findElements(By.id("submit")).isEmpty();
        Assert.assertFalse(isSubmitVisible);
    }
}