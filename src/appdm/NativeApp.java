package appdm;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: santhoshp
 * Date: 6/28/16
 * Time: 2:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class NativeApp extends App {
    private final DeviceProperties devProp;
    private String appPackage = null;
    private String appActivity = null;
    private String fileToPush = null;

    public NativeApp() {
        super();
        devProp = DeviceProperties.getInstance();
        this.appPackage = devProp.getValue("appPackage");
        this.appActivity = devProp.getValue("appActivity");
        this.fileToPush = devProp.getValue("fileToPush");
    }


    @Override
    public DesiredCapabilities getCapabilities() {
        DesiredCapabilities caps = new DesiredCapabilities();
        File app = new File(this.getFileToPush());    //Location for the apk file
        caps.setCapability(MobileCapabilityType.APP, app);
        caps.setCapability("deviceName", getDeviceName());
        caps.setCapability("platformName", getPlatformName());
        caps.setCapability("platformVersion", getPlatformVersion());
        caps.setCapability("avd", getAvd());
        caps.setCapability("appPackage", getAppPackage());
        caps.setCapability("appActivity", getAppActivity());
        return caps;
    }

    @Override
    public AndroidDriver getDriver() throws MalformedURLException {
        return new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), this.getCapabilities());
    }


    String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    String getAppActivity() {
        return appActivity;
    }

    public void setAppActivity(String appActivity) {
        this.appActivity = appActivity;
    }

    String getFileToPush() {
        return fileToPush;
    }

    public void setFileToPush(String fileToPush) {
        this.fileToPush = fileToPush;
    }
}
