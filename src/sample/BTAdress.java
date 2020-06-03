package sample;

import uiautomater.UIAutomater;

import java.io.IOException;

public class BTAdress {
    public static void main(String[] args) throws IOException {
        UIAutomater uiAutomater = new UIAutomater();
        System.out.print(uiAutomater.getMACBT());
    }
}
