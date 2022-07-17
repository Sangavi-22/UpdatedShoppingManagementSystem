package DataStorage;

import FileOperations.*;
import Model.Users.*;
import Model.MobilePhone.*;
import Model.ShoppingCart.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DataSource implements DataSourceService {
    private static DataSource dataSource = null;
    private final AdminDetailsFileHandlerService adminDetailsFile = new AdminDetailsFileHandler();
    private final SellerDetailsFileHandlerService sellerDetailsFile = new SellerDetailsFileHandler();
    private final CustomerDetailsFileHandlerService customerDetailsFile = new CustomerDetailsFileHandler();
    private final PhoneSpecificationsFileHandler phoneSpecificationsFile=new PhoneSpecificationsFileHandler();
    private final PhoneCameraFileHandlerService phoneCameraFile=new PhoneCameraFileHandler();
    private final PhoneDimensionsFileHandlerService phoneDimensionsFile=new PhoneDimensionsFileHandler();
    private final PhoneManufacturerFileHandlerService phoneManufacturerFile=new PhoneManufacturerFileHandler();
    private final PhoneProcessorFileHandlerService phoneProcessorFile=new PhoneProcessorFileHandler();
    private final PhoneStorageCapacityFileHandlerService phoneStorageCapacityFile=new PhoneStorageCapacityFileHandler();
    private final ShopFileHandlerService shopFile=new ShopFileHandler();
    private final ShoppingCartFileHandlerService shoppingCartFile=new ShoppingCartFileHandler();
    private final StockNotAvailableFileHandlerService stockNotAvailableFile=new StockNotAvailableFileHandler();
    private final OrdersFileHandlerService ordersFile=new OrdersFileHandler();
    private final BillAmountFileHandlerService billFile=new BillAmountFileHandler();
    private HashMap<String, Seller> sellers = new HashMap<>();
    private HashMap<String, Customer> customers = new HashMap<>();
    private HashMap<Integer,Integer>shopProductsMap=new HashMap<>();
    private HashMap<Integer,PhoneSpecifications>phoneSpecificationsMap=new HashMap<>();
    private HashMap<Integer,PhoneCamera>phoneCameraMap=new HashMap<>();
    private HashMap<Integer,PhoneDimensions>phoneDimensionsMap=new HashMap<>();
    private HashMap<Integer, PhoneManufacturer> phoneManufacturerMap=new HashMap<>();
    private HashMap<Integer, PhoneProcessor> phoneProcessorMap=new HashMap<>();
    private HashMap<Integer, PhoneStorageCapacity> phoneStorageCapacityMap=new HashMap<>();
    private DataSource() {
        //constructor
    }
    public static DataSource getInstance() {
        if (dataSource == null) {
            dataSource = new DataSource();
        }
        return dataSource;
    }
    public boolean containsAdmin(String userName){
        return adminDetailsFile.containsAdmin(userName);
    }
    public boolean passwordOfAdminMatches(String userName, String password){
        return adminDetailsFile.passwordMatches(userName,password);
    }
    public void addSeller(Seller seller){
        sellerDetailsFile.writeSellerDetails(seller.getUserName(), seller.getPassword(), seller.getName(), seller.getEmail(), seller.getMobileNum());
        sellers.put(seller.getUserName(), seller);
    }
    public boolean containsSeller(String userName) {
        return getSellers().containsKey(userName);
    }
    public boolean passwordOfSellerMatches(String userName, String password){
        return getSellers().containsKey(userName) && getSellers().get(userName).getPassword().equals(password);
    }
    public void updateSellerDetailsInDataSource(Seller seller){
        sellerDetailsFile.updateSellerDetails(seller.getUserName(), seller.getPassword(), seller.getName(), seller.getEmail(), seller.getMobileNum());
        sellers.put(seller.getUserName(),seller);
    }
    public Seller getSeller(String userName){
        return getSellers().get(userName);
    }
    public HashMap<String, Seller> getSellers() {
        if (sellers.size() == 0) {
            sellers = sellerDetailsFile.readSellers();
        }
        return sellers;
    }
    public void removeSeller(String userName){
        sellerDetailsFile.removeSellerDetailsFromFile(userName);
        sellers.remove(userName);
    }
    public void addCustomer(Customer customer) {
        customerDetailsFile.writeCustomerDetails(customer.getUserName(), customer.getPassword(), customer.getName(), customer.getEmail(), customer.getMobileNum(), customer.getShippingAddress());
        customers.put(customer.getUserName(),customer);
    }
    public boolean containsCustomer(String userName) {
        return getCustomers().containsKey(userName);
    }
    public boolean passwordOfCustomerMatches(String userName, String password){
        return getCustomers().containsKey(userName) && getCustomers().get(userName).getPassword().equals(password);
    }
    public void updateCustomerDetailsInDataSource(Customer customer){
        customerDetailsFile.updateCustomerDetails(customer.getUserName(), customer.getPassword(), customer.getName(), customer.getEmail(), customer.getMobileNum(), customer.getShippingAddress());
        customers.put(customer.getUserName(),customer);
    }
    public Customer getCustomer(String userName){
        return getCustomers().get(userName);
    }
    public HashMap<String, Customer> getCustomers() {
        if (customers.size() == 0) {
            customers = customerDetailsFile.readCustomers();
        }
        return customers;
    }
    public void removeCustomer(String userName){
        customerDetailsFile.removeCustomerDetailsFromFile(userName);
        customers.remove(userName);
    }
    public int getLastPhoneId(){
        return phoneSpecificationsFile.readLastPhoneId();
    }
    public void addPhoneToShop(int phoneId,int quantity) {
        shopFile.writeProductToShop(phoneId,quantity);
        shopProductsMap.put(phoneId,quantity);
    }
    public ArrayList<Integer> searchFromShop(String productId) {
        ArrayList<Integer>ids=new ArrayList<>();
        for(int id:getPhones().keySet()) {
            if(Integer.toString(id).contains(productId)) {
                ids.add(id);
            }
        }
        return ids;
    }
    public boolean containsPhone(int phoneId){
        return shopProductsMap.containsKey(phoneId);
    }
    public HashMap<Integer,Integer>getPhones(){
        if (shopProductsMap.size() == 0) {
            shopProductsMap= shopFile.readProducts();
        }
        return shopProductsMap;
    }
    public void updatePhoneQuantity(int phoneId,int quantity){
        if(shopProductsMap.containsKey(phoneId)) {
            int previousQuantity=shopProductsMap.get(phoneId);
            shopProductsMap.put(phoneId,previousQuantity+quantity);
            shopFile.updateProductInShop(phoneId,previousQuantity+quantity);
        }
    }
    public void removePhone(int phoneId){
        shopFile.removePhoneFromShop(phoneId);
        shopProductsMap.remove(phoneId);
        phoneSpecificationsFile.removePhoneSpecifications(phoneId);
        phoneSpecificationsMap.remove(phoneId);
        phoneCameraFile.removePhoneCamera(phoneId);
        phoneCameraMap.remove(phoneId);
        phoneDimensionsFile.removePhoneDimensions(phoneId);
        phoneDimensionsMap.remove(phoneId);
        phoneManufacturerFile.removePhoneManufacturer(phoneId);
        phoneManufacturerMap.remove(phoneId);
        phoneProcessorFile.removePhoneProcessor(phoneId);
        phoneStorageCapacityFile.removePhoneStorageCapacity(phoneId);
        phoneProcessorMap.remove(phoneId);
    }
    public void addPhoneSpecifications(int phoneId, PhoneSpecifications phoneSpecifications){
        phoneSpecificationsFile.writePhoneSpecifications(phoneId,phoneSpecifications.getModelName(),phoneSpecifications.getBatteryCapacity(),phoneSpecifications.getDisplaySize(),phoneSpecifications.getPrice());
        phoneSpecificationsMap.put(phoneId,phoneSpecifications);
    }
    public ArrayList<Integer>searchFromPhoneDetails(String input) {
        ArrayList<Integer> ids = new ArrayList<>();
        for(int id: getPhoneDetails().keySet()) {
            if(getPhoneDetails().get(id).getModelName().toLowerCase().contains(input.toLowerCase())) {
                ids.add(id);
            }
            else if(Integer.toString(getPhoneDetails().get(id).getPrice()).contains(input)){
                ids.add(id);
            }
            else if(getPhoneDetails().get(id).getBatteryCapacity().contains(input)) {
                ids.add(id);
            }
            else if(getPhoneDetails().get(id).getDisplaySize().contains(input)) {
                ids.add(id);
            }
        }
        return ids;
    }
    public PhoneSpecifications getParticularPhone(int phoneId){
        return getPhoneDetails().get(phoneId);
    }
    public HashMap<Integer,PhoneSpecifications>getPhoneDetails(){
        if(phoneSpecificationsMap.size()==0) {
            phoneSpecificationsMap=phoneSpecificationsFile.readPhoneSpecifications();
        }
        return phoneSpecificationsMap;

    }
    public void addPhoneCamera(int phoneId, PhoneCamera phoneCamera){
        phoneCameraFile.writePhoneCameraDetails(phoneId,phoneCamera.getPrimaryCamera(),phoneCamera.getSecondaryCamera());
        phoneCameraMap.put(phoneId,phoneCamera);
    }
    public ArrayList<Integer>searchFromPhoneCameraDetails(String input){
        ArrayList<Integer>ids=new ArrayList<>();
        for(int id:getPhoneCameraDetails().keySet()) {
            if(getPhoneCameraDetails().get(id).getPrimaryCamera().toLowerCase().contains(input.toLowerCase())) {
                ids.add(id);
            }
            else if(getPhoneCameraDetails().get(id).getSecondaryCamera().toLowerCase().contains(input.toLowerCase())) {
                ids.add(id);
            }
        }
        return ids;
    }
    public PhoneCamera getParticularPhoneWithCamera(int phoneId){
        return getPhoneCameraDetails().get(phoneId);
    }
    public HashMap<Integer, PhoneCamera> getPhoneCameraDetails(){
        if(phoneCameraMap.size()==0) {
            phoneCameraMap=phoneCameraFile.readPhoneCameraDetails();
        }
        return phoneCameraMap;
    }
    public void addPhoneDimensions(int phoneId,PhoneDimensions phoneDimensions){
        phoneDimensionsFile.writePhoneDimensions(phoneId,phoneDimensions.getWidth(),phoneDimensions.getHeight(),phoneDimensions.getWeight());
        phoneDimensionsMap.put(phoneId,phoneDimensions);
    }
    public ArrayList<Integer>searchFromPhoneDimensionsDetails(String input){
        ArrayList<Integer>ids=new ArrayList<>();
        for(int id: getPhoneDimensions().keySet()) {
            if(getPhoneDimensions().get(id).getWeight().contains(input)) {
                ids.add(id);
            }
            else if(getPhoneDimensions().get(id).getHeight().contains(input)) {
                ids.add(id);
            }
            else if(getPhoneDimensions().get(id).getWidth().contains(input)) {
                ids.add(id);
            }
        }
        return ids;
    }
    public PhoneDimensions getParticularPhoneWithDimensions(int phoneId){
        return getPhoneDimensions().get(phoneId);
    }
    public HashMap<Integer, PhoneDimensions>getPhoneDimensions(){
        if(phoneDimensionsMap.size()==0) {
            phoneDimensionsMap=phoneDimensionsFile.readPhoneDimensionsDetails();
        }
        return phoneDimensionsMap;
    }
    public void addPhoneManufacturer(int phoneId,PhoneManufacturer phoneManufacturer ){
        phoneManufacturerFile.writePhoneManufacturerDetails(phoneId,phoneManufacturer.getManufacturerName());
        phoneManufacturerMap.put(phoneId,phoneManufacturer);
    }
    public ArrayList<Integer>searchFromPhoneManufacturer(String input){
        ArrayList<Integer>ids=new ArrayList<>();
        for(int id: getPhoneManufacturer().keySet()) {
            if(getPhoneManufacturer().get(id).getManufacturerName().toLowerCase().contains(input.toLowerCase())) {
                ids.add(id);
            }

        }
        return ids;
    }
    public PhoneManufacturer getParticularPhoneWithManufacturer(int phoneId){
        return getPhoneManufacturer().get(phoneId);
    }
    public HashMap<Integer, PhoneManufacturer> getPhoneManufacturer(){
        if(phoneManufacturerMap.size()==0) {
            phoneManufacturerMap=phoneManufacturerFile.readPhoneManufacturerDetails();
        }
        return phoneManufacturerMap;
    }
    public void addPhoneProcessor(int phoneId,PhoneProcessor phoneProcessor){
        phoneProcessorFile.writePhoneProcessorDetails(phoneId,phoneProcessor.getProcessorType(),phoneProcessor.getOperatingSystem());
        phoneProcessorMap.put(phoneId,phoneProcessor);
    }
    public ArrayList<Integer>searchFromPhoneProcessor(String input){
        ArrayList<Integer>ids=new ArrayList<>();
        for(int id: getPhoneProcessorDetails().keySet()) {
            if(getPhoneProcessorDetails().get(id).getProcessorType().toLowerCase().contains(input.toLowerCase())) {
                ids.add(id);
            }
            else if(getPhoneProcessorDetails().get(id).getOperatingSystem().toLowerCase().contains(input.toLowerCase())) {
                ids.add(id);
            }
        }
        return ids;
    }
    public PhoneProcessor getParticularPhoneWithProcessor(int phoneId){
        return getPhoneProcessorDetails().get(phoneId);
    }
    public HashMap<Integer, PhoneProcessor> getPhoneProcessorDetails(){
        if(phoneProcessorMap.size()==0) {
            phoneProcessorMap=phoneProcessorFile.readPhoneProcessorDetails();
        }
        return phoneProcessorMap;
    }
    public void addPhoneStorageCapacity(int phoneId, PhoneStorageCapacity phoneStorageCapacity){
        phoneStorageCapacityFile.writePhoneProcessorDetails(phoneId,phoneStorageCapacity.getStorageCapacity());
        phoneStorageCapacityMap.put(phoneId,phoneStorageCapacity);
    }

    public ArrayList<Integer>searchFromPhoneStorageCapacity(String input){
        ArrayList<Integer>ids=new ArrayList<>();
        for(int id: getPhoneStorageCapacityDetails().keySet()) {
            if(getPhoneStorageCapacityDetails().get(id).getStorageCapacity().contains(input)){
                ids.add(id);
            }

        }
        return ids;
    }
    public PhoneStorageCapacity getParticularPhoneWithStorageCapacity(int phoneId){
        return getPhoneStorageCapacityDetails().get(phoneId);
    }
    public HashMap<Integer,PhoneStorageCapacity>getPhoneStorageCapacityDetails(){
        if(phoneStorageCapacityMap.size()==0) {
            phoneStorageCapacityMap=phoneStorageCapacityFile.readPhoneStorageCapacityDetails();
        }
        return phoneStorageCapacityMap;
    }
    public ArrayList<Integer> searchPhoneFromFile(String input) {
        ArrayList<Integer>productIds=new ArrayList<>();
        productIds.addAll(searchFromShop(input));
        productIds.addAll(searchFromPhoneDetails(input));
        productIds.addAll(searchFromPhoneManufacturer(input));
        productIds.addAll(searchFromPhoneCameraDetails(input));
        productIds.addAll(searchFromPhoneDimensionsDetails(input));
        productIds.addAll(searchFromPhoneProcessor(input));
        productIds.addAll(searchFromPhoneStorageCapacity(input));
        return productIds;
    }
    public void addToStockNillList(int productId){
        stockNotAvailableFile.writeToFile(productId);
    }
    public  ArrayList<Integer> readStockNilProducts(){
        return stockNotAvailableFile.readStockNotAvailableProducts();
    }
    public  void removeFromStockNilList(int productId){
        stockNotAvailableFile.removePhoneId(productId);
    }
    public void writeToCartFile(String userName, HashMap<Integer, Integer> productsFromCart){
        shoppingCartFile.writeToCart(userName,productsFromCart);
    }
    public boolean containsCart(String userName){
        return shoppingCartFile.containsShoppingCart(userName);
    }
    public ShoppingCart getCart(String userName){
        return shoppingCartFile.getShoppingCart(userName);
    }
    public int getLastOrderNum(){
        return ordersFile.readLastOrderNum();
    }
    public void writeToOrdersList(String userName, int orderNum, int productId, int quantity, String deliveryStatus){
        ordersFile.writeOrders(orderNum,productId,quantity);
        ordersFile.writeOrderedUsers(orderNum,userName,deliveryStatus);
    }
    public String getUserFromOrderedUsers(int orderId){
        return ordersFile.readOrderedUser(orderId);
    }
    public ArrayList<Integer> readParticularOrder(String userName){
        return ordersFile.readUserOrderId(userName);
    }
    public ShoppingCart getProductsOfThatOrder(int orderId){
        return ordersFile.readProducts(orderId);
    }
    public ArrayList<Integer> readAllOrders(){
        return ordersFile.readAllOrderFromFile();
    }
    public HashMap<Integer, String> readOrderStatus(String userName){
        return ordersFile.readStatusOfOrder(userName);
    }
    public void updateOrderStatus(String userName, int orderId){
        ordersFile.updateStatusOfOrder(userName,orderId);
    }
    public void removeOrder(int orderId,String userName){
        ordersFile.removeOrderFromFile(orderId,userName);
        billFile.removeBillAmount(orderId);
    }
    public void writeToBillFile(int orderId, int totalAmount){
        billFile.writeBillAmount(orderId,totalAmount);
    }
    public int readFromBillFile(int orderId){
        return billFile.readBillAmount(orderId);
    }
    public void freeCartForUser(String userName){
        shoppingCartFile.removeFromCart(userName);
    }
    public void updateCartOwner(String userName){
        shoppingCartFile.updateCartOwnerName(userName);
    }
}
