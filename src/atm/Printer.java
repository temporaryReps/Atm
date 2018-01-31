package atm;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

class Printer {
    static void print(String report, String path) throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM yyyy HH:mm");
        String currentDate = dateFormat.format(new Date());

        String result = currentDate + "\n\n" + report;

        BufferedWriter writer = new BufferedWriter(new FileWriter(path, false));
        writer.write(result);
        writer.flush();
        writer.close();
    }
}
