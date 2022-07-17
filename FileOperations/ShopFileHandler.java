package FileOperations;

import java.io.*;
import java.util.HashMap;

public class ShopFileHandler implements ShopFileHandlerService{
    File ShopProducts=new File("/Users/sangavi-pt5468/Desktop/Shopping-1/ShopProducts.txt");
    public void writeProductToShop(int productId, int quantity) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(ShopProducts,true));
            bw.write(productId+"|"+quantity+System.getProperty("line.separator"));
            bw.flush();
            bw.close();
        }
        catch(IOException e) {
            System.out.println();
        }
    }
    public HashMap<Integer,Integer> readProducts(){
        HashMap<Integer,Integer>products=new HashMap<>();
        String sentence;
        try{
            String[] words;
            BufferedReader br=new BufferedReader(new FileReader(ShopProducts));
            while(br.ready()) {
                sentence=br.readLine();
                words=sentence.split("\\|");
                products.put(Integer.parseInt(words[0]),Integer.parseInt(words[1]));
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
    public void updateProductInShop(int productId,int quantity){
        try {
            File ShopProductsTemp=new File("/Users/sangavi-pt5468/Desktop/Shopping-1/ShopProductsTemp.txt");
            BufferedReader reader = new BufferedReader(new FileReader(ShopProducts));
            BufferedWriter writer = new BufferedWriter(new FileWriter(ShopProductsTemp,true));
            String currentLine;
            while((currentLine = reader.readLine()) != null) {
                String[] words=currentLine.split("\\|");
                if(words[0].equals(Integer.toString(productId))) {
                    writer.write(productId + "|" + quantity + System.getProperty("line.separator"));
                }
                else {
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
            }
            writer.flush();
            writer.close();
            reader.close();
            ShopProducts.delete();
            ShopProductsTemp.renameTo(ShopProducts);
        }
        catch(IOException e) {
            System.out.println();
        }
    }
    public void removePhoneFromShop(int productId){
        try {
            File ShopProductsTemp=new File("/Users/sangavi-pt5468/Desktop/Shopping-1/ShopProductsTemp.txt");
            BufferedReader reader = new BufferedReader(new FileReader(ShopProducts));
            BufferedWriter writer = new BufferedWriter(new FileWriter(ShopProductsTemp,true));
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
            ShopProducts.delete();
            ShopProductsTemp.renameTo(ShopProducts);
        }
        catch(IOException e) {
            System.out.println();
        }
    }
}
