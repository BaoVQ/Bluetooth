package managerobject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessBuilder {
    public static void main(String[] args) throws IOException {
//        System.out.println("absS");
//        ManageOj manageOj = new ManageOj();
//        manageOj.GetManagedObjects();

        String[] cmd = new String[]{"ipconfig"};

        java.lang.ProcessBuilder processBuilder = new java.lang.ProcessBuilder(cmd);
        Process process = processBuilder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String str = "";

        while (true) {

            str = reader.readLine();
            if (str == null) break;
            System.out.println(str);
        }
    }
}
