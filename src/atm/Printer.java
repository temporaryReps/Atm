package atm;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

class Printer {
    private static volatile boolean printerIsBroken;

    static void print(String report, String path) throws IOException {

        Thread printThread = new Thread(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM yyyy HH:mm");
                String currentDate = dateFormat.format(new Date());

                String result = currentDate + "\n\n" + report;

                try(BufferedWriter writer = new BufferedWriter(new FileWriter(path, false))) {
                    writer.write(result);
                    writer.flush();
                } catch (IOException e) {
                    printerIsBroken = true;
                }
            }
        });

        printThread.start();
        try {
            printThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (printerIsBroken) {
            throw new  IOException("Printer is broken");
        }
    }
}
