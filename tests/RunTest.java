/**
 * Created with IntelliJ IDEA.
 * User: santhoshp
 * Date: 6/15/16
 * Time: 1:46 PM
 *
 */

import DeviceManager.Device;
import appdm.NativeApp;
import appdm.WebApp;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Ignore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RunTest {
    private AndroidDriver driver;
    private Random random;
    private Device device;

    public RunTest() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @BeforeMethod
    public void setUp() throws InterruptedException, IOException {
        random = new Random();
        device = new Device();
        device.addDevice();
        device.startDevice(true);


    }

    @Ignore
    public void testWebApp() throws IOException {
        driver = new WebApp().getDriver();
        driver.manage().timeouts().implicitlyWait(600, TimeUnit.SECONDS);
        driver.get("https://www.google.com");
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("search for appium");
    }

    @Test
    public void testNativeApp() throws MalformedURLException, InterruptedException {
        driver = new NativeApp().getDriver();
        driver.manage().timeouts().implicitlyWait(360, TimeUnit.SECONDS);
        String emailId = "a360wip" + random.nextInt(999) + "@yandex.com";
        Thread.sleep(60*1000);
//        driver.findElement(By.id("com.dropbox.android:id/tour_sign_up")).click();
//        getWebElementByText("First name").sendKeys("John");
//        getWebElementByText("Last name").sendKeys("Miller");
//        getWebElementByText("Email").sendKeys(emailId);
//        driver.findElement(By.xpath("//android.widget.EditText[@NAF='true']")).sendKeys("pramati123");
//        driver.findElement(By.id("com.dropbox.android:id/new_account_submit")).click();
//        driver.findElement(By.id("android:id/button2")).click();
//        driver.findElement(By.id("com.dropbox.android:id/qr_finish_button")).click();

    }

    private WebElement getWebElementByText(String text) {
        try {
            List<WebElement> we = driver.findElementsByClassName("android.widget.EditText");
            for (WebElement aWe : we) {
                if (aWe.getText().startsWith(text)) {
                    return aWe;
                }
            }
        } catch (NoSuchElementException nsee) {
            System.out.println("Elements not found by class EditText" + nsee);
        }
        return null;
    }

    @AfterClass
    public void tearDown() throws IOException {
        driver.closeApp();//CloseApp() function is used to close the mobile native and hybrid apps while quit() and close() is used for web apps
        device.stopDevice();
    }
}



