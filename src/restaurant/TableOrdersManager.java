package restaurant;

import java.util.HashMap;

public class TableOrdersManager implements OrdersManager
{
    //private Integer tableNumber;
    private HashMap<Integer, Order> restaurantOrders;
    private HashMap<String, Order> internetOrders;

    TableOrdersManager()
    {
//        this.tableNumber=tableNumber;
        restaurantOrders = new HashMap<Integer, Order>();
        internetOrders =new HashMap<String, Order>();
    }

    public void addOrder(Integer tableNumber, Order order) throws OrderAlreadyAddedException {
        if(restaurantOrders.containsKey(tableNumber)){
            throw new OrderAlreadyAddedException("Ошибка добавления заказа", "Данный столик занят");
        }

        restaurantOrders.put(tableNumber, order);
    }

    public void addOrder(String address, Order order) throws OrderAlreadyAddedException {
        if(internetOrders.containsKey(address)){
            throw new OrderAlreadyAddedException("Ошибка добавления заказа", "К данному адресу уже привязан заказ");
        }
        internetOrders.put(address, order);
    }

    public Order getOrder(String address){
        return internetOrders.get(address);
    }

    public Order getOrder(Integer tableNumber){
        if(!restaurantOrders.containsKey(tableNumber)){
            throw new IllegalTableNumber("" + tableNumber, "Столика с таким номером не существует");
        }
        return restaurantOrders.get(tableNumber);
    }


    public void removeOrder(String address){
        internetOrders.remove(address);
    }

    public void removeOrder(Integer tableNumber){
        if(!restaurantOrders.containsKey(tableNumber)){
            throw new IllegalTableNumber("" + tableNumber, "Столика с таким номером не существует");
        }
        internetOrders.remove(tableNumber);
    }

    public void addItemToOrder(String address, MenuItem item){
        getOrder(address).add(item);
    }

    public void addItemToOrder(Integer tableNumber, MenuItem item){
        if(!restaurantOrders.containsKey(tableNumber)){
            throw new IllegalTableNumber("" + tableNumber, "Столика с таким номером не существует");
        }
        getOrder(tableNumber).add(item);
    }

    public Order [] getOrdersArray(){
        //Order [] ordersArray = new Order[orders.size()];
        return internetOrders.values().toArray(new Order[0]);
    }

    public double getOrdersTotal(){
        double total=0.0;

        Order[] ordersArray=getOrdersArray();
        for(int i = 0; i< internetOrders.size(); i++    ){
            total+=ordersArray[i].getOrderCost();
        }
        return total;
    }

    public int getItemCountByNameTotal(String name){
        int count=0;
        Order[] ordersArray=getOrdersArray();
        for(int i = 0; i< internetOrders.size(); i++    ){
            count+=ordersArray[i].getItemCountByName(name);
        }

        return  count;
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
