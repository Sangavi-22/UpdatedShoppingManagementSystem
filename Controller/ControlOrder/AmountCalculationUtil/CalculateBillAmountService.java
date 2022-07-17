package Controller.ControlOrder.AmountCalculationUtil;

public interface CalculateBillAmountService {
    void calculateBillAmount(int price, int orderedQuantity);
    int getTotalCost();
}
