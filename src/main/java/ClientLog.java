import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientLog {

    protected String[] values = {"productNum", "amount"};
    protected List<String[]> log = new ArrayList<>();

    public void log(int productNum, int amount) {
        if (log.isEmpty()) {
            log.add(values);
        }
        log.add(new String[]{String.valueOf(productNum + 1), String.valueOf(amount)});
    }

    public void exportAsCSV() throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter("csv_dir/log.csv", true),
                CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.RFC4180_LINE_END)) {
            writer.writeAll(log);
        }
    }
}
