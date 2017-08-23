package appdm;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: santhoshp
 * Date: 6/28/16
 * Time: 11:40 AM
 *
 */
public class WebApp extends App {
    private final String browserName;
    private DesiredCapabilities caps;

    public WebApp() {
        this.browserName = DeviceProperties.getInstance().getValue("browserName");
    }


    @Override
    public DesiredCapabilities getCapabilities() {
        caps = new DesiredCapabilities();
        caps.setCapability("deviceName", getDeviceName());
        caps.setCapability("browserName", getBrowserName());
        caps.setCapability("platformVersion", getPlatformVersion());
        caps.setCapability("platformName", getPlatformName());
        caps.setCapability("avd", getAvd());

        return caps;
    }

    @Override
    public AndroidDriver getDriver() throws MalformedURLException {
        return new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), this.getCapabilities());


    }



    String getBrowserName() {
        return browserName;
    }

}
