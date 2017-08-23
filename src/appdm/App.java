package appdm;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;

/**
 * Created with IntelliJ IDEA.
 * User: santhoshp
 * Date: 6/28/16
 * Time: 11:22 AM
 * To change this template use File | Settings | File Templates.
 */
abstract class App {

    private final DeviceProperties devProp;
    private String deviceName;
    private String platformName;
    private String platformVersion;
    private String avd;


    App() {
        devProp = DeviceProperties.getInstance();
        this.deviceName = devProp.getValue("deviceName");
        this.platformName = devProp.getValue("platformName");
        this.platformVersion = devProp.getValue("platformVersion");
        this.avd = devProp.getValue("avd");
    }


    public abstract DesiredCapabilities getCapabilities();

    public abstract AndroidDriver getDriver() throws MalformedURLException;


    String getDeviceName() {
        return deviceName;
    }

    String getPlatformName() {
        return platformName;
    }

    String getPlatformVersion() {
        return platformVersion;
    }

    String getAvd() {
        return avd;
    }

}
