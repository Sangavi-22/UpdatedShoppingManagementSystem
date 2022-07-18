package FileOperations;

import Model.ShoppingCart.ShoppingCart;
import java.io.*;
import java.util.HashMap;

public class ShoppingCartFileHandler implements ShoppingCartFileHandlerService{
    File ShoppingCart= new File("/Users/sangavi-pt5468/Desktop/Shopping-1/ShoppingCart.txt");
    public void writeToCart(String userName, HashMap<Integer,Integer> shoppingCart){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(ShoppingCart,true));
            for(int productId: shoppingCart.keySet()) {
                bw.write(userName+"|"+productId+"|"+shoppingCart.get(productId)+System.getProperty("line.separator"));
            }
            bw.flush();
            bw.close();
        }
        catch(IOException e) {
            System.out.println();
        }
    }
    public boolean containsShoppingCart(String userName){
        boolean found=false;
        String sentence;
        try{
            String[] words;
            BufferedReader br=new BufferedReader(new FileReader(ShoppingCart));
            while(br.ready()) {
                sentence=br.readLine();
                words=sentence.split("\\|");
                if(words[0].equals(userName)) {
                   found=true;
                    break;
                }
            }
            br.close();
        }
        catch(IOException e) {
            System.out.println();
        }
        finally {
            return found;
        }
    }
    public Model.ShoppingCart.ShoppingCart getShoppingCart(String userName){
        Model.ShoppingCart.ShoppingCart shoppingCart =new ShoppingCart();
        String sentence;
        try{
            String[] words;
            BufferedReader br=new BufferedReader(new FileReader(ShoppingCart));
            while(br.ready()) {
                sentence=br.readLine();
                words=sentence.split("\\|");
                if(words[0].equals(userName)) {
                    shoppingCart.addProductsToCart(Integer.parseInt(words[1]),Integer.parseInt(words[2]));
                }
            }
            br.close();
        }
        catch(IOException e) {
            System.out.println();
        }
        finally {
            return shoppingCart;
        }
    }
    public void removeFromCart(String userName){
        try {
            File ShoppingCartTemp= new File("/Users/sangavi-pt5468/Desktop/Shopping-1/ShoppingCartTemp.txt");
            BufferedReader reader = new BufferedReader(new FileReader(ShoppingCart));
            BufferedWriter writer = new BufferedWriter(new FileWriter(ShoppingCartTemp, true));
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
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
            ShoppingCart.delete();
            ShoppingCartTemp.renameTo(ShoppingCart);
        }
        catch(IOException e) {
            System.out.println();
        }
    }
    public  void updateCartOwnerName(String userName){
        try {
            File ShoppingCartTemp= new File("/Users/sangavi-pt5468/Desktop/Shopping-1/ShoppingCartTemp.txt");
            BufferedReader reader = new BufferedReader(new FileReader(ShoppingCart));
            BufferedWriter writer = new BufferedWriter(new FileWriter(ShoppingCartTemp, true));
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] words=currentLine.split("\\|");
                int productId=Integer.parseInt(words[1]);
                int quantity=Integer.parseInt(words[2]);
                String newLine=userName+"|"+productId+"|"+quantity;
                if (words[0].equals("guest")) {
                    writer.write(newLine+System.getProperty("line.separator"));
                }
                else {
                    writer.write(currentLine + System.getProperty("line.separator"));
                }

            }
            writer.flush();
            writer.close();
            reader.close();
            ShoppingCart.delete();
            ShoppingCartTemp.renameTo(ShoppingCart);
        }
        catch(IOException e) {
            System.out.println();
        }

    }
}

