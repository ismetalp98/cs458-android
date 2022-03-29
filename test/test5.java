





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


public class test5 {
    public static URL url;
    public static DesiredCapabilities capabilities;
    public static AndroidDriver<MobileElement> driver;

    @BeforeSuite
    public void setupAppium() throws MalformedURLException {

        String path = "C:\\Users\\user\\AndroidStudioProjects\\cs458-android\\app\\build\\intermediates\\apk\\debug\\app-debug.apk";
        final String URL_STRING = "http://127.0.0.1:4723/wd/hub";
        url = new URL(URL_STRING);
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

        //see if the edit button works

        //start filling the survey
        driver.findElement(By.id("name")).sendKeys("TestName");
        driver.findElement(By.id("dd")).sendKeys("01");
        driver.findElement(By.id("mm")).sendKeys("01");
        driver.findElement(By.id("yyyy")).sendKeys("1991");
        driver.findElement(By.id("city")).sendKeys("ankara");
        driver.findElementById("female").click();
        driver.findElementById("biontech").click();
        driver.findElement(By.id("checkase")).click();
        driver.findElement(By.id("ase")).sendKeys("TestAse");
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
        Assert.assertEquals(ase, "Side Effects are: TestAse");
        String after = driver.findElement(By.id("after")).getText();
        Assert.assertEquals(after, "After 3th: Yes");

        //edit the data
        driver.findElement(By.id("edit")).click();
        driver.findElementById("male").click();
        driver.findElement(By.id("name")).sendKeys("TestNameTwo");
        driver.findElement(By.id("dd")).sendKeys("02");
        driver.findElement(By.id("mm")).sendKeys("02");
        driver.findElement(By.id("yyyy")).sendKeys("1992");
        driver.findElement(By.id("city")).sendKeys("istanbul");
        driver.findElementById("turcovac").click();
        driver.findElement(By.id("checkase")).click();
        driver.findElementById("no_pos").click();
        driver.findElement(By.id("submit")).click();

        //next page data check
        vaccination = driver.findElement(By.id("vaccination")).getText();
        Assert.assertEquals(vaccination, "Vaccination is: Turcovac");
        name = driver.findElement(By.id("name")).getText();
        Assert.assertEquals(name, "Name is: TestNameTwo");
        bdate = driver.findElement(By.id("bdate")).getText();
        Assert.assertEquals(bdate, "Birth date is: 02-02-1992");
        city = driver.findElement(By.id("city")).getText();
        Assert.assertEquals(city, "City is: istanbul");
        gender = driver.findElement(By.id("gender")).getText();
        Assert.assertEquals(gender, "Gender is: Male");
        ase = driver.findElement(By.id("ase")).getText();
        Assert.assertEquals(ase, "Side Effects are: No side effect");
        after = driver.findElement(By.id("after")).getText();
        Assert.assertEquals(after, "After 3th: No");

    }

    @Test (enabled=true) public void testCase2() throws InterruptedException {

        //see if going back and forward works

        //go back to the survey
        driver.navigate().back();
        driver.findElement(By.id("name")).sendKeys("TestName");
        driver.findElement(By.id("dd")).sendKeys("01");
        driver.findElement(By.id("mm")).sendKeys("01");
        driver.findElement(By.id("yyyy")).sendKeys("1991");
        driver.findElement(By.id("city")).sendKeys("ankara");
        driver.findElementById("female").click();
        driver.findElementById("biontech").click();
        driver.findElement(By.id("checkase")).click();
        driver.findElement(By.id("ase")).sendKeys("TestAse");
        driver.findElementById("yes_pos").click();
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
        Assert.assertEquals(ase, "Side Effects are: TestAse");
        String after = driver.findElement(By.id("after")).getText();
        Assert.assertEquals(after, "After 3th: Yes");

    }

    @Test (enabled=true) public void testCase3() throws InterruptedException {

        //see if going back and forward without changing anything works

        //go back to the survey
        driver.navigate().back();
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
        Assert.assertEquals(ase, "Side Effects are: TestAse");
        String after = driver.findElement(By.id("after")).getText();
        Assert.assertEquals(after, "After 3th: Yes");

    }
    @Test (enabled=true) public void testCase4() throws InterruptedException {

        //see if editing without changing anything works

        //edit the data
        driver.findElement(By.id("edit")).click();
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
        Assert.assertEquals(ase, "Side Effects are: TestAse");
        String after = driver.findElement(By.id("after")).getText();
        Assert.assertEquals(after, "After 3th: Yes");

    }
}
