package FileOperations;

import Model.MobilePhone.PhoneDimensions;
import java.io.*;
import java.util.HashMap;

public class PhoneDimensionsFileHandler implements PhoneDimensionsFileHandlerService{
    File PhoneDimensions=new File("/Users/sangavi-pt5468/Desktop/Shopping-1/PhoneDimensions.txt");
    public void writePhoneDimensions(int productId, String width, String height,String weight){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(PhoneDimensions,true));
            bw.write(productId+"|"+width+"|"+height+"|"+weight+ System.getProperty("line.separator"));
            bw.flush();
            bw.close();
        }
        catch(IOException e) {
            System.out.println();
        }
    }
    public HashMap<Integer, PhoneDimensions> readPhoneDimensionsDetails() {
        HashMap<Integer,PhoneDimensions>phoneDimensionsDetails=new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(PhoneDimensions));
            String sentence;
            while ((sentence = br.readLine()) != null) {
                String[] words = sentence.split("\\|");
                PhoneDimensions phoneDimensions=new PhoneDimensions(words[1],words[2],words[3]);
                phoneDimensionsDetails.put(Integer.parseInt(words[0]),phoneDimensions);
            }
            br.close();

        }
        catch(IOException e) {
            System.out.println();
        }
        finally {
            return phoneDimensionsDetails;
        }
    }
    public void removePhoneDimensions(int productId) {
        try {
            File PhoneDimensionsTemp=new File("/Users/sangavi-pt5468/Desktop/Shopping-1/PhoneDimensionsTemp.txt");
            BufferedReader reader = new BufferedReader(new FileReader(PhoneDimensions));
            BufferedWriter writer = new BufferedWriter(new FileWriter(PhoneDimensionsTemp,true));
            String currentLine;
            while((currentLine = reader.readLine()) != null) {
                String[] words=currentLine.split("\\|");
                if(words[0].equals(Integer.toString(productId))) {
                    continue;
                }
                else{
                    writer.write(currentLine + System.getProperty("line.separator"));
                }

            }
            writer.flush();
            writer.close();
            reader.close();
            PhoneDimensions.delete();
            PhoneDimensionsTemp.renameTo(PhoneDimensions);
        }
        catch(IOException e) {
            System.out.println();
        }
    }
}
