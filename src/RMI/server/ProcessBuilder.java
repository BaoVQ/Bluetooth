package RMI.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessBuilder {
    void processBuider1(String[] terminal) throws IOException, InterruptedException {
        final java.lang.Process p = Runtime.getRuntime().exec(terminal);

        new Thread(new Runnable() {
            public void run() {
                BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line = null;

                try {
                    while ((line = input.readLine()) != null)
                        System.out.println(line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        for (int i = 0; i < 2; i++) {
            System.out.println("Sleep: " + i);
            Thread.sleep(1000);
        }
        p.destroy();
        System.out.println("Done");
    }

    void processBuider(String[] terminal) throws IOException {
        java.lang.ProcessBuilder processBuilder = new java.lang.ProcessBuilder(terminal);
        java.lang.Process process = processBuilder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String str = "";

        while (true) {

            str = reader.readLine();
            if (str == null) break;
            System.out.println(str);
        }
    }

}
