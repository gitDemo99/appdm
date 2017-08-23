package DeviceManager;

import appdm.DeviceProperties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: santhoshp
 * Date: 7/7/16
 * Time: 11:02 AM
 */
public class Device {
    private final String name;
    private final String abi;

    private String[] processCmd;
    private Process process;


    public Device() {
        DeviceProperties devProp = DeviceProperties.getInstance();
        name = devProp.getValue("avd");
        abi = devProp.getValue("abi");
    }


    void stopAppium() {
        processCmd = new String[]{"cmd", "/c", "taskkill", "/f", "/im", "node.exe"};
        execute(processCmd);
    }

    public void addDevice() {
        try {
            if (!isStarted()) {
                processCmd = new String[]{"cmd", "/c", "echo", "no", "|", "android", "create", "avd", "--force", "-n", name, "-t", "1", "--abi", abi};
                execute(processCmd).waitFor();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void startDevice(Boolean wipeData) {
        try {
            if (!isStarted()) {
                if (wipeData) {
                    processCmd = new String[]{"cmd", "/c", "emulator", "-avd", name, "-wipe-data"};
                } else {
                    processCmd = new String[]{"cmd", "/c", "emulator", "-avd", name};
                }
                execute(processCmd);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void stopDevice() throws IOException {
        if (isStarted()) {
            stopAppium();
            processCmd = new String[]{"cmd", "/c", "adb", "shell", "reboot", "-p"};
            execute(processCmd);
        }
    }

    boolean isStarted() throws IOException {
        boolean started = false;
        String outputLine;
        processCmd = new String[]{"cmd", "/c", "adb", "shell", "getprop", "init.svc.bootanim"};
        Process process = execute(processCmd);
        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(process.getInputStream()));

        while ((outputLine = stdInput.readLine()) != null) {
            if (outputLine.contains("stopped")) {
                started = true;
                break;
            }
        }
        return started;
    }


    Process execute(String[] processCmd) {
        if (System.getProperty("os.name").contains("Windows")) {
            ProcessBuilder processBuilder = new ProcessBuilder(processCmd);
            processBuilder.redirectErrorStream(true);
            try {
                process = processBuilder.start();
                return process;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
