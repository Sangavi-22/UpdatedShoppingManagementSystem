package View.Customer;

import Inputs.InputsFromUser;

public class CustomerMenuView implements CustomerMenuViewService{

    private final InputsFromUser inputsFromUser=new InputsFromUser();
    public void displayMenuPage(){
        System.out.println();
        System.out.println("*********************************************************");
        System.out.println("!                         MENU                          !");
        System.out.println("*********************************************************");
        System.out.println("!             Enter  1 to view products in shop         !");
        System.out.println("!             Enter  2 to view shopping cart            !");
        System.out.println("!             Enter  3 to remove product from cart      !");
        System.out.println("!             Enter  4 to update quantity in cart       !");
        System.out.println("!             Enter  5 to search for a product          !");
        System.out.println("!             Enter  6 to Track orders placed           !");
        System.out.println("!             Enter  7 to remove an order placed        !");
        System.out.println("!             Enter  8 to view history of orders        !");
        System.out.println("!             Enter  9 to view profile details          !");
        System.out.println("!             Enter  10 to update profile details       !");
        System.out.println("!             Enter  11 to view shop details            !");
        System.out.println("!             Enter  12 to Exit                         !");
        System.out.println("*********************************************************");
        System.out.println();
    }
    public boolean goBackToList(){
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
    public boolean confirmAddToCart(){
        System.out.println("Do you want to add this product to cart? 1.Yes 2.No\n");
        return getChoiceOfUser()==1;
    }
    public void displayProductPresentInCart(){
        System.out.println("Product present in cart already\n");
    }
    public void displayProductAddedToCart(){
        System.out.println("Product added to cart successfully\n");
    }
    public boolean confirmRemoveFromCart(){
        System.out.println("Want to remove any product from cart? 1.Yes 2.No\n");
        return getChoiceOfUser()==1;
    }
    public void displayProductRemovedFromCart(){
        System.out.println("Product removed from cart\n");
    }
    public boolean confirmUpdateProductQuantity(){
        System.out.println("Do you want to update the quantity of any product in cart? 1.Yes 2.No\n");
        return getChoiceOfUser()==1;
    }
    public  void displayEnterNewQuantity(){
        System.out.println("Enter the new value of product quantity\n");
    }
    public void displayProductQuantityUpdated(){
        System.out.println("Product quantity updated\n");
    }
    public void displayCannotUpdateQuantity(){
        System.out.println("Quantity cannot be updated\n");
    }
    public boolean confirmPlaceOrder(){
        System.out.println("Want to place order? 1.Yes 2.No\n");
        return getChoiceOfUser()==1;
    }
    public boolean confirmShippingAddress(String shippingAddress){
        System.out.println("Please check the shipping address\n");
        System.out.println(shippingAddress);
        System.out.println("Do you want to change the shipping address? 1.Yes 2.No\n");
        return getChoiceOfUser()==1;
    }
    public int displayPaymentSection(){
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("    Processing... Taking you to the payment section    ");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("1.Payment using card\n2.Payment using googlePay\n3.Cash on Delivery\n");
        return getChoiceOfUser();
    }
    public void confirmCODOption(){
        System.out.println("You have chosen cash on delivery option\n");
    }
    public void displayOrderPlaced(){
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("  Order placed successfully.Thank you for shopping!!!  ");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
    public void displayOrderRemoved(){
        System.out.println("Order has been removed\n");
    }
    public void displayNoOrders(){
        System.out.println("No records available\n");
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
    public boolean displayMessageForGuest(){
        System.out.println("Please create an account to proceed further");
        System.out.println("1. Create Account or 2.Continue as Guest\n");
        return getChoiceOfUser()==1;
    }
    public String getInput(){
        System.out.println("Enter something to start searching\n");
        return inputsFromUser.inputValue();
    }
    public int getChoiceOfUser(){
        return inputsFromUser.inputChoice();
    }
    public int getProductId(){
        return inputsFromUser.inputProductId();
    }
    public int getQuantity(){
        return inputsFromUser.inputCount();
    }
    public int getOrderId(){
        return inputsFromUser.inputOrderId();
    }
    public String inputAddressToChange(){
        return inputsFromUser.inputShippingAddress();
    }
    public String getGooglePayId(){
        return inputsFromUser.inputGooglePayId();
    }
    public String getCardNumber(){
        return inputsFromUser.inputAccountId();
    }
}
