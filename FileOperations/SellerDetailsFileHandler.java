package FileOperations;

import Model.Users.Seller;
import java.io.*;
import java.util.HashMap;

public class SellerDetailsFileHandler implements SellerDetailsFileHandlerService {
    File SellerDetails=new File("/Users/sangavi-pt5468/Desktop/Shopping-1/SellerDetails.txt");
    public void writeSellerDetails(String userName,String password,String name,String email,long mobileNum){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(SellerDetails,true));
            bw.write(userName+"|"+password+"|"+name+"|"+email+"|"+mobileNum+System.getProperty("line.separator"));
            bw.flush();
            bw.close();
        }
        catch(IOException e) {
            System.out.println();
        }
    }
    public void updateSellerDetails(String userName, String password, String name, String email, long mobileNum){
        try {
            File SellerDetailsTemp = new File("/Users/sangavi-pt5468/Desktop/Shopping-1/SellerDetailsTemp.txt");
            BufferedReader reader = new BufferedReader(new FileReader(SellerDetails));
            BufferedWriter writer = new BufferedWriter(new FileWriter(SellerDetailsTemp,true));
            String newLine=userName+"|"+password+"|"+name+"|"+email+"|"+mobileNum;
            String currentLine;
            while((currentLine = reader.readLine()) != null) {
                String[] words=currentLine.split("\\|");
                if(words[0].equals(userName)) {
                    writer.write(newLine + System.getProperty("line.separator"));
                }
                else {
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
            }
            writer.flush();
            writer.close();
            reader.close();
            SellerDetails.delete();
            SellerDetailsTemp.renameTo(SellerDetails);
        }
        catch(IOException e) {
            System.out.println();
        }
    }
    public HashMap<String,Seller>readSellers(){
        HashMap<String, Seller>sellers= new HashMap<>();
        String userName,password,name,email;
        long mobileNum;
        try{
            String[] words;
            BufferedReader br=new BufferedReader(new FileReader(SellerDetails));
            while(br.ready()){
                words=br.readLine().split("\\|");
                userName=words[0];
                password=words[1];
                name=words[2];
                email=words[3];
                mobileNum=Long.parseLong(words[4]);
                Seller seller=new Seller(userName,password,name,email,mobileNum);
                sellers.put(seller.getUserName(),seller);
            }
            br.close();
        }
        catch(IOException e) {
            System.out.println();
        }
        finally {
            return sellers;
        }
    }
    public void removeSellerDetailsFromFile(String userName){
        try {
            File SellerDetailsTemp = new File("/Users/sangavi-pt5468/Desktop/Shopping-1/SellerDetailsTemp.txt");
            BufferedReader reader = new BufferedReader(new FileReader(SellerDetails));
            BufferedWriter writer = new BufferedWriter(new FileWriter(SellerDetailsTemp,true));
            String currentLine;
            while((currentLine = reader.readLine()) != null) {
                String[] words=currentLine.split("\\|");
                if(words[0].equals(userName)) {
                    continue;
                }
                else {
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
            }
            writer.flush();
            writer.close();
            reader.close();
            SellerDetails.delete();
            SellerDetailsTemp.renameTo(SellerDetails);
        }
        catch(IOException e) {
            System.out.println();
        }
    }
}
