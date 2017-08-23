package appdm;
import java.io.IOException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: santhoshp
 * Date: 6/28/16
 * Time: 3:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class DeviceProperties {
    private static DeviceProperties instance = null;
    private final Properties properties;

    private DeviceProperties() throws IOException {
        properties = new Properties();
        properties.load(getClass().getResourceAsStream("/device.properties"));
    }


    public static DeviceProperties getInstance() {
        if (instance == null) {
            try {
                instance = new DeviceProperties();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return instance;
    }

    public String getValue(String key) {
        return properties.getProperty(key);
    }

}
