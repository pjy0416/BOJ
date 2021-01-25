package data_reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
    File inputFile, outputFile;

    public DataReader(String item) {
        final String INPUT_BASE_URL = "D://ITStudy/BOJ/input_data/", OUTPUT_BASE_URL="D://ITStudy/BOJ/output_data/";

        this.inputFile = new File(getPath(item, INPUT_BASE_URL));
        this.outputFile = new File(getPath(item, OUTPUT_BASE_URL));
    }

    private List<String> getData(File file) {
        List<String> dataList = new ArrayList<>();
        String line;
        FileReader fr;
        try {
            fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while((line = br.readLine()) != null) {
                dataList.add(line);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public List<String> getInputData() {
        return getData(this.inputFile);
    }

    public List<String> getOutputData() {
        return getData(this.outputFile);
    }

    private String getPath(String item, String url) {
        final String EXTENSION = ".txt";
        StringBuilder sb = new StringBuilder();
        sb.append(url).append(item).append(EXTENSION);

        return sb.toString();
    }
}
