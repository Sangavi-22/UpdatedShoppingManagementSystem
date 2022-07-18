package Controller.ControlOrder.AmountCalculationUtil;

public class CalculateBillAmount implements CalculateBillAmountService {
    private int totalCost=0;
    public void calculateBillAmount(int price, int orderedQuantity){
        totalCost+=price*orderedQuantity;
    }
    public int getTotalCost(){
        return this.totalCost;
    }
}
