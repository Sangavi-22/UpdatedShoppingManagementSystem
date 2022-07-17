package FileOperations;

import Model.MobilePhone.PhoneSpecifications;
import java.io.*;
import java.util.HashMap;

public class PhoneSpecificationsFileHandler implements PhoneSpecificationsFileHandlerService{
    File PhoneSpecifications=new File("/Users/sangavi-pt5468/Desktop/Shopping-1/PhoneSpecifications.txt");
    public void writePhoneSpecifications(int productId, String modelName, String batteryCapacity, String displaySize,int price){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(PhoneSpecifications,true));
            bw.write(productId+"|"+modelName+"|"+batteryCapacity+"|"+displaySize+"|"+price+ System.getProperty("line.separator"));
            bw.flush();
            bw.close();
        }
        catch(IOException e) {
            System.out.println();
        }
    }
    public int readLastPhoneId(){
        int id=0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(PhoneSpecifications));
            String sentence;
            while ((sentence =br.readLine()) != null) {
                String[] words=sentence.split("\\|");
                id=Integer.parseInt(words[0]);
            }
            br.close();
        }
        catch(IOException e) {
            System.out.println();
        }
        finally {
            return id;
        }
    }
    public HashMap<Integer, PhoneSpecifications> readPhoneSpecifications(){
        HashMap<Integer, PhoneSpecifications>phoneSpecificationsMap=new HashMap<>();
        String[] words;
        try{
            BufferedReader br=new BufferedReader(new FileReader(PhoneSpecifications));
            while(br.ready()){
                words=br.readLine().split("\\|");
                String modelName=words[1];
                String batteryCapacity=words[2];
                String displaySize=words[3];
                int price=Integer.parseInt(words[4]);
                PhoneSpecifications phoneSpecifications=new PhoneSpecifications(modelName,batteryCapacity,displaySize,price);
                phoneSpecificationsMap.put(Integer.parseInt(words[0]),phoneSpecifications);
            }
            br.close();
        }
        catch(IOException e) {
            System.out.println();
        }
        finally {
            return phoneSpecificationsMap;
        }

    }
    public void removePhoneSpecifications(int productId){
        try {
            File PhoneSpecificationsTemp=new File("/Users/sangavi-pt5468/Desktop/Shopping-1/PhoneSpecificationsTemp.txt");
            BufferedReader reader = new BufferedReader(new FileReader(PhoneSpecifications));
            BufferedWriter writer = new BufferedWriter(new FileWriter(PhoneSpecificationsTemp,true));
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
            PhoneSpecifications.delete();
            PhoneSpecificationsTemp.renameTo(PhoneSpecifications);
        }
        catch(IOException e) {
            System.out.println();
        }
    }

}
