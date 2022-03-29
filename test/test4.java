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


public class test4 {
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

    @Test (enabled=true) public void testCase4() throws InterruptedException {
        boolean isSubmitVisible;

        MobileElement name = driver.findElement(By.id("name"));
        MobileElement day = driver.findElement(By.id("dd"));
        MobileElement month = driver.findElement(By.id("mm"));
        MobileElement year = driver.findElement(By.id("yyyy"));
        MobileElement city = driver.findElement(By.id("city"));
        MobileElement gender = driver.findElement(By.id("groupGender"));
        MobileElement vacType = driver.findElement(By.id("groupVaccination"));
        MobileElement checkASE = driver.findElement(By.id("checkase"));

        name.sendKeys("Stephan Will");
        day.sendKeys("23");
        month.sendKeys("08");
        year.sendKeys("1985");
        city.sendKeys("Erzurum");
        driver.findElementById("female").click();

        boolean isOtherVacDisp;
        driver.findElementById("other_vac").click();
        isOtherVacDisp = driver.findElementById("otherVaccine").isDisplayed();
        Assert.assertTrue(isOtherVacDisp);

        driver.findElementById("biontech").click();
        isOtherVacDisp = driver.findElements(By.id("otherVaccine")).size() != 0;
        System.out.println(isOtherVacDisp);
        Assert.assertFalse(isOtherVacDisp);

        driver.findElement(By.id("checkase")).click();
        isOtherVacDisp = driver.findElement(By.id("ase")).isDisplayed();
        Assert.assertTrue(isOtherVacDisp);

        driver.findElement(By.id("checkase")).click();
        isOtherVacDisp = driver.findElements(By.id("ase")).size() != 0;
        Assert.assertFalse(isOtherVacDisp);
    }
}