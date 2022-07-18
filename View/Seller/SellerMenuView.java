package View.Seller;

import Inputs.InputsFromUser;

public class SellerMenuView implements SellerMenuViewService{
    private final InputsFromUser inputsFromUser=new InputsFromUser();
    public void displayMenuPage(){
        System.out.println();
        System.out.println("*******************************************************");
        System.out.println("!                        MENU                         !");
        System.out.println("*******************************************************");
        System.out.println("!          Enter 1 to view products in shop           !");
        System.out.println("!          Enter 2 to add a product to shop           !");
        System.out.println("!          Enter 3 to update quantity of a product    !");
        System.out.println("!          Enter 4 to remove a product from shop      !");
        System.out.println("!          Enter 5 to search for a product            !");
        System.out.println("!          Enter 6 to view orders placed by customers !");
        System.out.println("!          Enter 7 to check products with nill stock  !");
        System.out.println("!          Enter 8 to view profile details            !");
        System.out.println("!          Enter 9 to update profile details          !");
        System.out.println("!          Enter 10 to logout of account              !");
        System.out.println("*******************************************************");
        System.out.println();
    }
    public boolean displayBackToList(){
        System.out.println("Want to go back to the list? 1.Yes 2.No\n");
        return getChoiceOfUser()==1;
    }
    public void displayNoProducts(){
        System.out.println("No products available\n");
    }
    public boolean confirmViewParticularProduct(){
        System.out.println("Want to view any product in detail? 1.Yes 2.No\n");
        return getChoiceOfUser()==1;
    }
    public void displayProductAdded(){
        System.out.println("Product added successfully\n");
    }
    public void displayProductPresentAlready(){
        System.out.println("Product present in shop already\n");
    }
    public void displayProductCannotBeAdded(){
        System.out.println("Sorry product cannot be added\n");
    }
    public boolean confirmUpdateProductQuantity(){
        System.out.println("Want to update any product quantity? 1.Yes 2.No\n");
        return getChoiceOfUser()==1;
    }
    public void displayQuantityUpdated(){
        System.out.println("Product quantity updated\n");
    }
    public void displayCannotUpdate(){
        System.out.println("Cannot update product quantity\n");
    }
    public int displayRemoveProductOptions(){
        System.out.println("1.Remove product with id\n2.Remove product with name\n3.Back to menu\n");
        return getChoiceOfUser();
    }
    public void displayProductRemoved(){
        System.out.println("Product has been removed\n");
    }
    public void displayNoRecords(){
        System.out.println("No records found\n");
    }
    public boolean confirmDispatchOrder(){
        System.out.println("Want to dispatch any order? 1.Yes 2.No\n");
        return getChoiceOfUser()==1;
    }
    public  void displayOrderDispatched(int orderId){
        System.out.println("The order with id "+orderId+" has been dispatched\n");
    }
    public void displayInvalidInput(){
        System.out.println("Invalid input\n");
    }
    public void displayMessage(){
        System.out.println("Taking you back to the menu\n");
    }
    public void displayDefaultMessage(){
        System.out.println(" Some problem occurred !!!");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("!   Please wait while we take you back to the menu    !");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
    public int getChoiceOfUser(){
        return inputsFromUser.inputChoice();
    }
    public int getProductId(){
        return inputsFromUser.inputProductId();
    }
    public  int getOrderId(){
        return inputsFromUser.inputOrderId();
    }
    public String getProductName(){
        return inputsFromUser.inputModelName();
    }
    public String getUserInputToSearch(){
        System.out.println("Enter some specification of a product to start searching\n");
        return inputsFromUser.inputValue();
    }
    public int getQuantity(){
        return inputsFromUser.inputCount();
    }
}
