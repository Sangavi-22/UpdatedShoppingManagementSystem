package FileOperations;

import Model.ShoppingCart.ShoppingCart;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class OrdersFileHandler implements OrdersFileHandlerService{
    File OrdersFile=new File("/Users/sangavi-pt5468/Desktop/Shopping-1/OrdersFile.txt");
    File OrderedUsers=new File("/Users/sangavi-pt5468/Desktop/Shopping-1/OrderedUsers.txt");
    public void writeOrders(int orderNum,int productId,int quantity){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(OrdersFile,true));
            bw.write(orderNum+"|"+productId+"|"+quantity+System.getProperty("line.separator"));
            bw.flush();
            bw.close();
        }
        catch(IOException e) {
            System.out.println();
        }
    }
    public  void writeOrderedUsers(int orderNum, String userName, String deliveryStatus){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(OrderedUsers,true));
            bw.write(userName+"|"+orderNum+"|"+deliveryStatus+System.getProperty("line.separator"));
            bw.flush();
            bw.close();
        }
        catch(IOException e) {
            System.out.println();
        }
    }
    public int readLastOrderNum(){
        int id=0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(OrderedUsers));
            String sentence;
            while ((sentence = br.readLine()) != null) {
                String[] words = sentence.split("\\|");
                id = Integer.parseInt(words[1]);
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
    public String readOrderedUser(int orderId){
        String userName="";
        try {
            BufferedReader br = new BufferedReader(new FileReader(OrderedUsers));
            String sentence;
            while ((sentence = br.readLine()) != null) {
                String[] words = sentence.split("\\|");
                if(words[1].equals(Integer.toString(orderId))) {
                    userName=words[0];
                }
            }
            br.close();
        }
        catch(IOException e) {
            System.out.println();
        }
        finally {
            return userName;
        }
    }
    public ArrayList<Integer> readUserOrderId(String userName){
        ArrayList<Integer>orders=new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(OrderedUsers));
            String sentence;
            while ((sentence = br.readLine()) != null) {
                String[] words = sentence.split("\\|");
                if(words[0].equals(userName)) {
                    if(!orders.contains(Integer.parseInt(words[1]))) {
                        orders.add(Integer.parseInt(words[1]));
                    }
                    else {
                        continue;
                    }
                }
                else {
                    continue;
                }
            }
            br.close();
        }
        catch(IOException e) {
            System.out.println();
        }
        finally {
            return orders;
        }
    }
   public ShoppingCart readProducts(int orderId){
        ShoppingCart shoppingCart =new ShoppingCart();
       try {
           BufferedReader br = new BufferedReader(new FileReader(OrdersFile));
           String sentence;
           while ((sentence = br.readLine()) != null) {
               String[] words = sentence.split("\\|");
               if(Integer.parseInt(words[0])==orderId) {
                   int productId=Integer.parseInt(words[1]);
                   int quantity=Integer.parseInt(words[2]);
                   shoppingCart.addProductsToCart(productId,quantity);
               }
               else{
                   continue;
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
   public ArrayList<Integer> readAllOrderFromFile(){
       ArrayList<Integer>orders=new ArrayList<>();
       try {
           BufferedReader br = new BufferedReader(new FileReader(OrdersFile));
           String sentence;
           while ((sentence = br.readLine()) != null) {
               String[] words = sentence.split("\\|");
               if(!orders.contains(Integer.parseInt(words[0]))) {
                   orders.add(Integer.parseInt(words[0]));
               }
               else {
                   continue;
               }
           }
           br.close();
       }
       catch(IOException e) {
           System.out.println();
       }
       finally {
           return orders;
       }
   }
    public HashMap<Integer, String> readStatusOfOrder(String userName){
        HashMap<Integer, String>orderStatus= new HashMap<>();
        String sentence;
        try{
            String[] words;
            BufferedReader br=new BufferedReader(new FileReader(OrderedUsers));
            while(br.ready()) {
                sentence=br.readLine();
                words=sentence.split("\\|");
                if(words[0].equals(userName)) {
                    orderStatus.put(Integer.parseInt(words[1]),words[2]);
                }
                else {
                    continue;
                }
            }
            br.close();
        }
        catch(IOException e) {
            System.out.println();
        }
        finally {
            return orderStatus;
        }
    }
   public  void updateStatusOfOrder(String userName,int orderId){
       try {
           File OrderedUsersTemp=new File("/Users/sangavi-pt5468/Desktop/Shopping-1/OrderedUsersTemp.txt");
           BufferedReader reader = new BufferedReader(new FileReader(OrderedUsers));
           BufferedWriter writer = new BufferedWriter(new FileWriter(OrderedUsersTemp,true));
           String currentLine;
           while((currentLine = reader.readLine()) != null) {
               String[] words=currentLine.split("\\|");
               String newLine=userName+"|"+orderId+"|"+"Delivered";
               if(words[0].equals(userName) && words[1].equals(Integer.toString(orderId))) {
                   writer.write(newLine + System.getProperty("line.separator"));
               }
               else {
                   writer.write(currentLine + System.getProperty("line.separator"));
               }

           }
           writer.flush();
           writer.close();
           reader.close();
           OrderedUsers.delete();
           OrderedUsersTemp.renameTo(OrderedUsers);

       }
       catch(IOException e) {
           System.out.println();
       }
   }
    public void removeOrderFromFile(int orderId,String userName){
        try {
            File OrdersFileTemp=new File("/Users/sangavi-pt5468/Desktop/Shopping-1/OrdersFileTemp.txt");
            File OrderedUsersTemp=new File("/Users/sangavi-pt5468/Desktop/Shopping-1/OrderedUsersTemp.txt");
            BufferedReader reader = new BufferedReader(new FileReader(OrdersFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(OrdersFileTemp,true));
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
            OrdersFile.delete();
            OrdersFileTemp.renameTo(OrdersFile);
            reader = new BufferedReader(new FileReader(OrderedUsers));
            writer = new BufferedWriter(new FileWriter(OrderedUsersTemp,true));
            while((currentLine = reader.readLine()) != null) {
                String[] words=currentLine.split("\\|");
                if(words[0].equals(userName) && words[1].equals(Integer.toString(orderId))) {
                    continue;
                }
                else {
                    writer.write(currentLine + System.getProperty("line.separator"));
                }

            }
            writer.flush();
            writer.close();
            reader.close();
            OrderedUsers.delete();
            OrderedUsersTemp.renameTo(OrderedUsers);
        }
        catch(IOException e) {
            System.out.println();
        }
    }
}
