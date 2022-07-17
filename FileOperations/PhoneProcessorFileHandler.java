package FileOperations;

import Model.MobilePhone.PhoneProcessor;
import java.io.*;
import java.util.HashMap;

public class PhoneProcessorFileHandler implements  PhoneProcessorFileHandlerService{
    File PhoneProcessor= new File("/Users/sangavi-pt5468/Desktop/Shopping-1/PhoneProcessor.txt");
    public  void writePhoneProcessorDetails(int productId, String processorType, String operatingSystem){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(PhoneProcessor,true));
            bw.write(productId+"|"+processorType+"|"+operatingSystem+ System.getProperty("line.separator"));
            bw.flush();
            bw.close();
        }
        catch(IOException e) {
            System.out.println();
        }
    }
    public HashMap<Integer, PhoneProcessor> readPhoneProcessorDetails(){
        HashMap<Integer, PhoneProcessor>phoneProcessorDetails=new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(PhoneProcessor));
            String sentence;
            while ((sentence = br.readLine()) != null) {
                String[] words = sentence.split("\\|");
                PhoneProcessor phoneProcessor=new PhoneProcessor(words[1],words[2]);
                phoneProcessorDetails.put(Integer.parseInt(words[0]),phoneProcessor);
            }
            br.close();
        }
        catch(IOException e) {
            System.out.println();
        }
        finally {
            return phoneProcessorDetails;
        }
    }
    public void removePhoneProcessor(int productId){
        try {
            File PhoneProcessorTemp= new File("/Users/sangavi-pt5468/Desktop/Shopping-1/PhoneProcessorTemp.txt");
            BufferedReader reader = new BufferedReader(new FileReader(PhoneProcessor));
            BufferedWriter writer = new BufferedWriter(new FileWriter(PhoneProcessorTemp,true));
            String currentLine;
            while((currentLine = reader.readLine()) != null) {
                String[] words=currentLine.split("\\|");
                if(words[0].equals(Integer.toString(productId))) {
                    continue;
                }
                else {
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
            }
            writer.flush();
            writer.close();
            reader.close();
            PhoneProcessor.delete();
            PhoneProcessorTemp.renameTo(PhoneProcessor);
        }
        catch(IOException e) {
            System.out.println();
        }
    }
}
