package FileOperations;

import Model.Users.Customer;
import java.io.*;
import java.util.HashMap;

public class CustomerDetailsFileHandler implements CustomerDetailsFileHandlerService {
    File CustomerDetails= new File("/Users/sangavi-pt5468/Desktop/Shopping-1/CustomerDetails.txt");
    public void writeCustomerDetails(String userName, String password, String name, String email, long mobileNum, String address){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(CustomerDetails,true));
            bw.write(userName+"|"+password+"|"+name+"|"+email+"|"+mobileNum+"|" + address+ System.getProperty("line.separator"));
            bw.flush();
            bw.close();
        }
        catch(IOException e) {
            System.out.println();
        }
    }
    public  void updateCustomerDetails(String userName, String password, String name, String email, long mobileNum, String address){
        try {
            File CustomerDetailsTemp = new File("/Users/sangavi-pt5468/Desktop/Shopping-1/CustomerDetailsTemp.txt");
            BufferedReader reader = new BufferedReader(new FileReader(CustomerDetails));
            BufferedWriter writer = new BufferedWriter(new FileWriter(CustomerDetailsTemp,true));
            String newLine=userName+"|"+password+"|"+name+"|"+email+"|"+mobileNum+"|" + address;
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
            CustomerDetails.delete();
            CustomerDetailsTemp.renameTo(CustomerDetails);
        }
        catch(IOException e) {
            System.out.println();
        }
    }
    public HashMap<String, Customer> readCustomers(){
        String[] words;
        HashMap<String, Customer>customers= new HashMap<>();
        String sentence,userName,password,name,email,address;
        long mobileNum;
        try{
            BufferedReader br=new BufferedReader(new FileReader(CustomerDetails));
            while(br.ready()) {
                sentence=br.readLine();
                words=sentence.split("\\|");
                userName=words[0];
                password=words[1];
                name=words[2];
                email=words[3];
                mobileNum=Long.parseLong(words[4]);
                address=words[5];
                Customer customer=new Customer(userName,password,name,email,mobileNum,address);
                customers.put(customer.getUserName(),customer);
            }
            br.close();
        }
        catch(IOException e) {
            System.out.println();
        }
        finally {
            return customers;
        }
    }
    public void removeCustomerDetailsFromFile(String userName){
        try {
            File CustomerDetailsTemp= new File("/Users/sangavi-pt5468/Desktop/Shopping-1/CustomerDetailsTemp.txt");
            BufferedReader reader = new BufferedReader(new FileReader(CustomerDetails));
            BufferedWriter writer = new BufferedWriter(new FileWriter(CustomerDetailsTemp,true));
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
            CustomerDetails.delete();
            CustomerDetailsTemp.renameTo(CustomerDetails);
        }
        catch(IOException e) {
            System.out.println();
        }
    }
}
