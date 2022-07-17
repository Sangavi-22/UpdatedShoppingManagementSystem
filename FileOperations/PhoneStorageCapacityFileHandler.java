package FileOperations;

import Model.MobilePhone.PhoneStorageCapacity;
import java.io.*;
import java.util.HashMap;

public class PhoneStorageCapacityFileHandler implements PhoneStorageCapacityFileHandlerService {
    File PhoneStorageCapacity= new File("/Users/sangavi-pt5468/Desktop/Shopping-1/PhoneStorageCapacity.txt");
    public void writePhoneProcessorDetails(int productId, String storageCapacity){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(PhoneStorageCapacity,true));
            bw.write(productId+"|"+storageCapacity+System.getProperty("line.separator"));
            bw.flush();
            bw.close();
        }
        catch(IOException e) {
            System.out.println();
        }
    }
    public HashMap<Integer, PhoneStorageCapacity> readPhoneStorageCapacityDetails(){
        HashMap<Integer, PhoneStorageCapacity>phoneStorageCapacityDetails=new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(PhoneStorageCapacity));
            String sentence;
            while ((sentence = br.readLine()) != null) {
                String[] words = sentence.split("\\|");
                PhoneStorageCapacity phoneStorageCapacity=new PhoneStorageCapacity(words[1]);
                phoneStorageCapacityDetails.put(Integer.parseInt(words[0]),phoneStorageCapacity);
            }
            br.close();
        }
        catch(IOException e) {
            System.out.println();
        }
        finally {
            return phoneStorageCapacityDetails;
        }
    }
    public void removePhoneStorageCapacity(int productId){
        try {
            File PhoneProcessorTemp= new File("/Users/sangavi-pt5468/Desktop/Shopping-1/PhoneStorageCapacityTemp.txt");
            BufferedReader reader = new BufferedReader(new FileReader(PhoneStorageCapacity));
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
            PhoneStorageCapacity.delete();
            PhoneProcessorTemp.renameTo(PhoneStorageCapacity);
        }
        catch(IOException e) {
            System.out.println();
        }
    }
}
