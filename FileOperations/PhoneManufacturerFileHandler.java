package FileOperations;

import Model.MobilePhone.PhoneManufacturer;
import java.io.*;
import java.util.HashMap;

public class PhoneManufacturerFileHandler implements PhoneManufacturerFileHandlerService{
    File PhoneManufacturer=new File("/Users/sangavi-pt5468/Desktop/Shopping-1/PhoneManufacturer.txt");
    public  void writePhoneManufacturerDetails(int productId, String manufacturerName){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(PhoneManufacturer,true));
            bw.write(productId+"|"+manufacturerName+ System.getProperty("line.separator"));
            bw.flush();
            bw.close();
        }
        catch(IOException e) {
            System.out.println();
        }
    }
    public HashMap<Integer, PhoneManufacturer> readPhoneManufacturerDetails(){
        HashMap<Integer, PhoneManufacturer>phoneManufacturers=new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(PhoneManufacturer));
            String sentence;
            while ((sentence = br.readLine()) != null) {
                String[] words = sentence.split("\\|");
                PhoneManufacturer phoneManufacturer=new PhoneManufacturer(words[1]);
                phoneManufacturers.put(Integer.parseInt(words[0]),phoneManufacturer);
            }
            br.close();

        }
        catch(IOException e) {
            System.out.println();
        }
        finally {
            return phoneManufacturers;
        }
    }
    public void removePhoneManufacturer(int productId){
        try {
            File PhoneManufacturerTemp=new File("/Users/sangavi-pt5468/Desktop/Shopping-1/PhoneManufacturerTemp.txt");
            BufferedReader reader = new BufferedReader(new FileReader(PhoneManufacturer));
            BufferedWriter writer = new BufferedWriter(new FileWriter(PhoneManufacturerTemp,true));
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
            PhoneManufacturer.delete();
            PhoneManufacturerTemp.renameTo(PhoneManufacturer);
        }
        catch(IOException e) {
            System.out.println();
        }
    }
}
