package FileOperations;

import java.io.*;

public class BillAmountFileHandler implements BillAmountFileHandlerService{
    File BillAmountFile=new File("/Users/sangavi-pt5468/Desktop/Shopping-1/BillAmountFile.txt");
    public void writeBillAmount(int orderId,int billAmount) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(BillAmountFile,true));
            bw.write(orderId+"|"+billAmount+ System.getProperty("line.separator"));
            bw.flush();
            bw.close();

        }
        catch(IOException e) {
            System.out.println();
        }
    }
    public int readBillAmount(int orderId){
        int amount=0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(BillAmountFile));
            String sentence;
            while ((sentence = br.readLine()) != null) {
                String[] words = sentence.split("\\|");
                if(words[0].equals(Integer.toString(orderId))) {
                    amount=Integer.parseInt(words[1]);
                }
            }
            br.close();
        }
        catch(IOException e) {
            System.out.println();
        }
        finally {
            return amount;
        }
    }
    public void removeBillAmount(int orderId){
        try {
            File BillAmountFileTemp=new File("/Users/sangavi-pt5468/Desktop/Shopping-1/BillAmountFileTemp.txt");
            BufferedReader reader = new BufferedReader(new FileReader(BillAmountFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(BillAmountFileTemp,true));
            String currentLine;
            while((currentLine = reader.readLine()) != null) {
                String[] words=currentLine.split("\\|");
                if(words[0].equals(Integer.toString(orderId))) {
                    continue;
                }
                else {
                    writer.write(currentLine + System.getProperty("line.separator"));
                }

            }
            writer.flush();
            writer.close();
            reader.close();
            BillAmountFile.delete();
            BillAmountFileTemp.renameTo(BillAmountFile);
        }
        catch(IOException e) {
            System.out.println();
        }
    }
}
