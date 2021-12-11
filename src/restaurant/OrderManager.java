package restaurant;

import java.util.HashMap;

public class OrderManager
{
    //private Integer tableNumber;
    private HashMap<Integer, Order> restaurantOrders;
    private HashMap<String, Order> internetOrders;

    OrderManager()
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

    public void addItemToOrder(String address, Item item){
        getOrder(address).addItem(item);
    }

    public void addItemToOrder(Integer tableNumber, Item item){
        if(!restaurantOrders.containsKey(tableNumber)){
            throw new IllegalTableNumber("" + tableNumber, "Столика с таким номером не существует");
        }
        getOrder(tableNumber).addItem(item);
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

}
