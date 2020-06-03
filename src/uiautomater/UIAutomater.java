package uiautomater;

import java.io.IOException;

public class UIAutomater {
    ProcessBuilder processBuilder = new ProcessBuilder();
    String[] terminal = new String[] {"cd C:\\Users\\SVMC\\AppData\\Local\\Android\\Sdk\\platform-tools"};


    public void turnOnBluetooth() throws IOException, InterruptedException {
        //String[] run = new String[] {"C:\\Users\\SVMC\\AppData\\Local\\Android\\Sdk\\platform-tools\\adb shell am instrument -w -r   -e debug false -e class 'com.example.myapplication.ExampleInstrumentedTest#turnOnBluetooth' com.example.myapplication.test/androidx.test.runner.AndroidJUnitRunner"};
        String run = new String();
        //run = "adb shell am instrument -w -r -e debug false -e class 'com.example.myapplication.ExampleInstrumentedTest#turnOnBluetooth' com.example.myapplication.test/androidx.test.runner.AndroidJUnitRunner";
        run = "C:\\Users\\SVMC\\AppData\\Local\\Android\\Sdk\\platform-tools\\adb shell am instrument -w -r   -e debug false -e class 'com.example.myapplication.ExampleInstrumentedTest#turnOnBluetooth' com.example.myapplication.test/androidx.test.runner.AndroidJUnitRunner";
        //String[] run = new String[] {"ipconfig"};
        //String[] run = new String[] {"AppData\\Local\\Android\\Sdk\\platform-tools\\adb shell am instrument -w -r   -e debug false -e class 'com.example.myapplication.ExampleInstrumentedTest#turnOnBluetooth' com.example.myapplication.test/androidx.test.runner.AndroidJUnitRunner"};
        processBuilder.processBuider(run);
    }

    public void turnOffBluetooth() throws IOException, InterruptedException {
        String run = new String();
        run = "C:\\Users\\SVMC\\AppData\\Local\\Android\\Sdk\\platform-tools\\adb shell am instrument -w -r   -e debug false -e class 'com.example.myapplication.ExampleInstrumentedTest#turnOffBluetooth' com.example.myapplication.test/androidx.test.runner.AndroidJUnitRunner";
        processBuilder.processBuider(run);
    }

    public void runOpenAppMucis() throws IOException {
        String run = new String();
        run = "C:\\Users\\SVMC\\AppData\\Local\\Android\\Sdk\\platform-tools\\adb shell am instrument -w -r   -e debug false -e class 'com.example.myapplication.ExampleInstrumentedTest#openAppMusic' com.example.myapplication.test/androidx.test.runner.AndroidJUnitRunner";
        processBuilder.processBuider(run);

    }

    public void confirmPair() throws IOException, InterruptedException {
        String run = new String();
        run = "C:\\Users\\SVMC\\AppData\\Local\\Android\\Sdk\\platform-tools\\adb shell am instrument -w -r   -e debug false -e class 'com.example.myapplication.ExampleInstrumentedTest#confirmPair' com.example.myapplication.test/androidx.test.runner.AndroidJUnitRunner";
        processBuilder.processBuider(run);
    }

    public String getMACBT() throws IOException {
        String run = new String();
        run = "C:\\Users\\SVMC\\AppData\\Local\\Android\\Sdk\\platform-tools\\adb shell settings get secure bluetooth_address";
        String rs = processBuilder.processBuiderPrint(run);
        return rs;
    }




    //    adb shell am instrument -w -r   -e debug false -e class 'com.example.myapplication.ExampleInstrumentedTest#turnOffBluetooth' com.example.myapplication.test/androidx.test.runner.AndroidJUnitRunner
    //C:\Users\SVMC\AppData\Local\Android\Sdk\platform-tools\adb shell am instrument -w -r   -e debug false -e class 'com.example.myapplication.ExampleInstrumentedTest#turnOnBluetooth' com.example.myapplication.test/androidx.test.runner.AndroidJUnitRunner

    //adb shell am instrument -w -r   -e debug false -e class 'com.example.myapplication.ExampleInstrumentedTest#confirmPair' com.example.myapplication.test/androidx.test.runner.AndroidJUnitRunner


    public static void main(String[] args) throws IOException, InterruptedException {
        UIAutomater uiAutomater = new UIAutomater();
        uiAutomater.turnOffBluetooth();
    }
}
