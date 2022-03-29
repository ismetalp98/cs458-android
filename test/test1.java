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


public class test1 {
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

        boolean noError = false;
        MobileElement name = driver.findElement(By.id("name"));
        MobileElement day = driver.findElement(By.id("dd"));
        MobileElement month = driver.findElement(By.id("mm"));
        MobileElement year = driver.findElement(By.id("yyyy"));
        MobileElement city = driver.findElement(By.id("city"));
        MobileElement errCheck = driver.findElement(By.id("errorCheck"));
        MobileElement checkASE = driver.findElement(By.id("checkase"));

        // Valid input test
        name.sendKeys("Maya Cat");
        day.sendKeys("10");
        month.sendKeys("03");
        year.sendKeys("1990");
        city.sendKeys("Manisa");
        driver.findElementById("female").click();
        driver.findElementById("biontech").click();
        driver.findElementById("yes_pos").click();
        noError = errCheck.getText().equals("noError");
        Assert.assertTrue(noError);

        // Wrong name test
        // See if numbers are accepted as name
        name.sendKeys("123 345");
        day.sendKeys("13");
        month.sendKeys("04");
        year.sendKeys("1993");
        city.sendKeys("konya");
        driver.findElementById("other_gen").click();
        driver.findElementById("biontech").click();
        driver.findElementById("no_pos").click();
        if(errCheck.getText().equals("errorName ")){
            noError = false;
        }
        Assert.assertTrue(!noError);

        // Wrong 29 February
        // See if leap years are detected true
        name.sendKeys("Pufi Dog");
        day.sendKeys("29");
        month.sendKeys("02");
        year.sendKeys("1999");
        city.sendKeys("Eskişehir");
        driver.findElementById("male").click();
        driver.findElementById("turcovac").click();
        driver.findElementById("yes_pos").click();
        if(errCheck.getText().equals("errorDay")){
            noError = false;
        }
        Assert.assertTrue(!noError);

        // Right 29 February
        // See if leap years are detected true
        name.sendKeys("Stewart bill");
        day.sendKeys("29");
        month.sendKeys("02");
        year.sendKeys("2000");
        city.sendKeys("Eskişehir");
        driver.findElementById("male").click();
        driver.findElementById("turcovac").click();
        driver.findElementById("no_pos").click();
        noError = errCheck.getText().equals("noError");
        Assert.assertTrue(noError);

        // 31 November False
        // See if month days are correct
        name.sendKeys("carlos white");
        day.sendKeys("31");
        month.sendKeys("11");
        year.sendKeys("1999");
        city.sendKeys("Trabzon");
        driver.findElementById("female").click();
        driver.findElementById("turcovac").click();
        driver.findElementById("yes_pos").click();
        if(errCheck.getText().equals("errorDay ")){
            noError = false;
        }
        Assert.assertTrue(!noError);

        // 31 December True
        // See if month days are correct
        name.sendKeys("maya cat");
        day.sendKeys("31");
        month.sendKeys("12");
        year.sendKeys("2012");
        driver.findElementById("other_gen").click();
        driver.findElementById("biontech").click();
        driver.findElementById("no_pos").click();
        noError = errCheck.getText().equals("noError");
        Assert.assertTrue(noError);

        // Year 2024
        // See if future years accepted
        name.sendKeys("denis hood");
        day.sendKeys("01");
        month.sendKeys("01");
        year.sendKeys("2024");
        driver.findElementById("female").click();
        driver.findElementById("other_vac").click();
        driver.findElementById("otherVaccine").sendKeys("Moderna");
        driver.findElementById("yes_pos").click();
        if(errCheck.getText().equals("errorYear ")){
            noError = false;
        }
        Assert.assertTrue(!noError);

        // See if future days accepted
        name.sendKeys("apple fruit");
        day.sendKeys("30");
        month.sendKeys("03");
        year.sendKeys("2022");
        city.sendKeys("erzincan");
        driver.findElementById("male").click();
        driver.findElementById("turcovac").click();
        driver.findElementById("no_pos").click();
        if(errCheck.getText().equals("errorMonth errorDay errorYear ")){
            noError = false;
        }
        Assert.assertTrue(!noError);

        // Dates are all wrong
        // See if invalid numbers for days are correctly detected
        name.sendKeys("Bella Steel");
        day.sendKeys("32");
        month.sendKeys("19");
        year.sendKeys("1000");
        driver.findElementById("other_gen").click();
        driver.findElementById("biontech").click();
        driver.findElementById("no_pos").click();
        if(errCheck.getText().equals("errorMonth errorDay errorYear ")){
            noError = false;
        }
        Assert.assertTrue(!noError);

        // See the if day month and year fields accept strings
        name.sendKeys("Monica Pi");
        day.sendKeys("twelve");
        month.sendKeys("november");
        year.sendKeys("nineteenninetynine");
        city.sendKeys("gaziantep");
        driver.findElementById("female").click();
        driver.findElementById("other_vac").click();
        driver.findElementById("otherVaccine").sendKeys("AstraZeneca");
        driver.findElementById("yes_pos").click();
        if(errCheck.getText().equals("errorMonth errorDay errorYear ")){
            noError = false;
        }
        Assert.assertTrue(!noError);

        // See if date and month is accepted without 0
        name.sendKeys("Appium Test");
        day.sendKeys("4");
        month.sendKeys("5");
        year.sendKeys("1972");
        city.sendKeys("yozgat");
        driver.findElementById("other_gen").click();
        driver.findElementById("turcovac").click();
        driver.findElementById("no_pos").click();
        noError = errCheck.getText().equals("noError");
        Assert.assertTrue(noError);


        // See if city names are accepted with lower case letters
        name.sendKeys("Deniz Alp");
        day.sendKeys("30");
        month.sendKeys("10");
        year.sendKeys("1992");
        city.sendKeys("giresun");
        driver.findElementById("male").click();
        driver.findElementById("biontech").click();
        driver.findElementById("no_pos").click();
        noError = errCheck.getText().equals("noError");
        Assert.assertTrue(noError);

        // See if city names are accepted with different case letters
        name.sendKeys("Işık Eylul");
        day.sendKeys("04");
        month.sendKeys("05");
        year.sendKeys("1992");
        city.sendKeys("ANKara");
        driver.findElementById("female").click();
        driver.findElementById("turcovac").click();
        driver.findElementById("yes_pos").click();
        noError = errCheck.getText().equals("noError");
        Assert.assertTrue(noError);


        // See if cities other than Turkey are accepted
        name.sendKeys("ismet han");
        day.sendKeys("07");
        month.sendKeys("05");
        year.sendKeys("1975");
        city.sendKeys("New York");
        driver.findElementById("male").click();
        driver.findElementById("turcovac").click();
        driver.findElementById("no_pos").click();
        if(errCheck.getText().equals("errorCity ")){
            noError = false;
        }
        Assert.assertTrue(!noError);

        // Invalid city, 30 February and name with number are tested together
        name.sendKeys("123 Maya");
        day.sendKeys("30");
        month.sendKeys("02");
        year.sendKeys("1990");
        city.sendKeys("wrong city");
        driver.findElementById("male").click();
        driver.findElementById("turcovac").click();
        driver.findElementById("no_pos").click();
        if(errCheck.getText().equals("errorName errorCity errorDay ")){
            noError = false;
        }
        Assert.assertTrue(!noError);

        // See the if text fields of other vaccination and side effect are gives error
        name.sendKeys("Monica Pi");
        day.sendKeys("12");
        month.sendKeys("10");
        year.sendKeys("1995");
        city.sendKeys("gaziantep");
        driver.findElementById("female").click();
        driver.findElementById("other_vac").click();
        driver.findElementById("otherVaccine").sendKeys("Moderna");
        checkASE.click();
        driver.findElement(By.id("ase")).sendKeys("Lassitude");
        driver.findElementById("yes_pos").click();
        noError = errCheck.getText().equals("noError");
        Assert.assertTrue(noError);

    }
}