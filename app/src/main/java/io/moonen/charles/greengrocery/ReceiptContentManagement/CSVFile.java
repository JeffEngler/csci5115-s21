package io.moonen.charles.greengrocery.ReceiptContentManagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//Class to get data from csv file
public class CSVFile {
    InputStream inputStream;

    public CSVFile(InputStream inputStream){
        this.inputStream = inputStream;
    }

    public List readFile(){
        List fileContents = new ArrayList();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String currLine;
            while((currLine = reader.readLine()) != null) {
                String[] currRow = currLine.split(",");
                fileContents.add(currRow);
            }
        }
        catch (IOException except){
            throw new RuntimeException("Read Error: " + except);
        }
        finally{
            try{
                inputStream.close();
            }
            catch (IOException except){
                throw new RuntimeException("Close Error: " + except);
            }
        }
        return fileContents;
    }
}
