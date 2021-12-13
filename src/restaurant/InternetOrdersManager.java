package restaurant;

import java.beans.PropertyEditorManager;

public class InternetOrdersManager implements OrdersManager
{
    public boolean add(Order order){

        return true;
    }
    public Order remove(){

        return new InternetOrder();
    }

    public Order order(){
        return new InternetOrder();
    }

    @Override
    public int itemsQuantity(String itemName) {
        return 0;
    }

    @Override
    public int itemsQuantity(MenuItem item) {
        return 0;
    }

    @Override
    public Order[] getOrders() {
        return new Order[0];
    }

    @Override
    public int ordersCostSummary() {
        return 0;
    }

    @Override
    public int ordersQuantity() {
        return 0;
    }
}
