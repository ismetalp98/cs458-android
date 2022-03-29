import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;


public class test3 {
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

        //see if the data of survey is passed to the next page

        //start filling the survey
        driver.findElement(By.id("name")).sendKeys("TestName");
        driver.findElement(By.id("dd")).sendKeys("01");
        driver.findElement(By.id("mm")).sendKeys("01");
        driver.findElement(By.id("yyyy")).sendKeys("1991");
        driver.findElement(By.id("city")).sendKeys("ankara");
        driver.findElementById("female").click();
        driver.findElementById("biontech").click();
        driver.findElementById("yes_pos").click();
        //submit the survey
        driver.findElement(By.id("submit")).click();

        //next page data check
        String vaccination = driver.findElement(By.id("vaccination")).getText();
        Assert.assertEquals(vaccination, "Vaccination is: Biontech");
        String name = driver.findElement(By.id("name")).getText();
        Assert.assertEquals(name, "Name is: TestName");
        String bdate = driver.findElement(By.id("bdate")).getText();
        Assert.assertEquals(bdate, "Birth date is: 01-01-1991");
        String city = driver.findElement(By.id("city")).getText();
        Assert.assertEquals(city, "City is: ankara");
        String gender = driver.findElement(By.id("gender")).getText();
        Assert.assertEquals(gender, "Gender is: Female");
        String ase = driver.findElement(By.id("ase")).getText();
        Assert.assertEquals(ase, "Side Effects are: No side effect");
        String after = driver.findElement(By.id("after")).getText();
        Assert.assertEquals(after, "After 3th: Yes");
    }

    @Test (enabled=true) public void testCase2() throws InterruptedException, MalformedURLException {

        //see if the other options pass to the next page

        //rerun the app
        uninstallApp();
        setupAppium();

        //start filling the survey
        driver.findElement(By.id("name")).sendKeys("TestName");
        driver.findElement(By.id("dd")).sendKeys("01");
        driver.findElement(By.id("mm")).sendKeys("01");
        driver.findElement(By.id("yyyy")).sendKeys("1991");
        driver.findElement(By.id("city")).sendKeys("ankara");
        driver.findElementById("other_gen").click();
        driver.findElementById("other_vac").click();
        driver.findElementById("otherVaccine").sendKeys("TestVac");
        driver.findElement(By.id("checkase")).click();
        driver.findElement(By.id("ase")).sendKeys("TestAse");
        driver.findElementById("no_pos").click();
        driver.findElement(By.id("submit")).click();

        //next page data check
        String vaccination = driver.findElement(By.id("vaccination")).getText();
        Assert.assertEquals(vaccination, "Vaccination is: TestVac");
        String name = driver.findElement(By.id("name")).getText();
        Assert.assertEquals(name, "Name is: TestName");
        String bdate = driver.findElement(By.id("bdate")).getText();
        Assert.assertEquals(bdate, "Birth date is: 01-01-1991");
        String city = driver.findElement(By.id("city")).getText();
        Assert.assertEquals(city, "City is: ankara");
        String gender = driver.findElement(By.id("gender")).getText();
        Assert.assertEquals(gender, "Gender is: Other");
        String ase = driver.findElement(By.id("ase")).getText();
        Assert.assertEquals(ase, "Side Effects are: TestAse");
        String after = driver.findElement(By.id("after")).getText();
        Assert.assertEquals(after, "After 3th: No");
    }
}
