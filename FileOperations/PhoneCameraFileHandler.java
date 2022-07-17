package FileOperations;

import Model.MobilePhone.PhoneCamera;
import java.io.*;
import java.util.HashMap;

public class PhoneCameraFileHandler implements PhoneCameraFileHandlerService{
    File PhoneCameraDetails=new File("/Users/sangavi-pt5468/Desktop/Shopping-1/PhoneCameraDetails.txt");
    public void writePhoneCameraDetails(int productId, String primaryCamera, String secondaryCamera){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(PhoneCameraDetails,true));
            bw.write(productId+"|"+primaryCamera+"|"+secondaryCamera+System.getProperty("line.separator"));
            bw.flush();
            bw.close();
        }
        catch(IOException e) {
            System.out.println();
        }
    }
    public HashMap<Integer, PhoneCamera> readPhoneCameraDetails() {
        HashMap<Integer, PhoneCamera>phoneCameraDetails=new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(PhoneCameraDetails));
            String sentence;
            while ((sentence = br.readLine()) != null) {
                String[] words = sentence.split("\\|");
                PhoneCamera phoneCamera=new PhoneCamera(words[1],words[2]);
                phoneCameraDetails.put(Integer.parseInt(words[0]),phoneCamera);
            }
            br.close();

        }
        catch(IOException e) {
            System.out.println();
        }
        finally {
            return phoneCameraDetails;
        }

    }
    public void removePhoneCamera(int productId){
        try {
            File PhoneCameraDetailsTemp=new File("/Users/sangavi-pt5468/Desktop/Shopping-1/PhoneCameraDetailsTemp.txt");
            BufferedReader reader = new BufferedReader(new FileReader(PhoneCameraDetails));
            BufferedWriter writer = new BufferedWriter(new FileWriter(PhoneCameraDetailsTemp,true));
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
            PhoneCameraDetails.delete();
            PhoneCameraDetailsTemp.renameTo(PhoneCameraDetails);
        }
        catch(IOException e) {
            System.out.println();
        }
    }
}
