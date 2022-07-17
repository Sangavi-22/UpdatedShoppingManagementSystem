package FileOperations;

import java.io.*;
import java.util.ArrayList;
public class StockNotAvailableFileHandler implements StockNotAvailableFileHandlerService{
    File StockNotAvailableFile=new File("/Users/sangavi-pt5468/Desktop/Shopping-1/StockNotAvailable.txt");
    public void writeToFile(int id){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(StockNotAvailableFile,true));
            bw.write(id+System.getProperty("line.separator"));
            bw.flush();
            bw.close();
        }
        catch(IOException e) {
            System.out.println();
        }
    }
    public ArrayList<Integer> readStockNotAvailableProducts(){
        ArrayList<Integer>products=new ArrayList<>();
        String sentence;
        try{
            BufferedReader br=new BufferedReader(new FileReader(StockNotAvailableFile));
            while(br.ready()) {
                sentence=br.readLine().trim();
                if(!products.contains(Integer.parseInt(sentence))){
                    if(Integer.parseInt(sentence)!=0) {
                        products.add(Integer.parseInt(sentence));
                    }
                }
            }
            br.close();
        }
        catch(IOException e) {
            System.out.println();
        }
        finally {
            return products;
        }
    }
    public void removePhoneId(int phoneId){
        try {
            File StockNotAvailableTempFile=new File("/Users/sangavi-pt5468/Desktop/Shopping-1/StockNotAvailableTemp.txt");
            BufferedReader reader = new BufferedReader(new FileReader(StockNotAvailableFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(StockNotAvailableTempFile, true));
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                if(trimmedLine.equals(Integer.toString(phoneId))) {
                    continue;
                }
                else {
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
            }
            writer.flush();
            writer.close();
            reader.close();
            StockNotAvailableFile.delete();
            StockNotAvailableTempFile.renameTo(StockNotAvailableFile);
        }
        catch(IOException e) {
            System.out.println();
        }
    }
}