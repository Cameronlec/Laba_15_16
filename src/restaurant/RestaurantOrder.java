package restaurant;



public class RestaurantOrder implements Order
{
    private Integer orderNumber;
    private Integer tableNumber;
    private int size;
    private int capacity;

    private boolean isClosed;

    private MenuItem[] orderItemsArray;

    RestaurantOrder(Integer orderNumber, Integer tableNumber)
    {
        this.orderNumber = orderNumber;
        this.tableNumber = tableNumber;

        orderItemsArray=new MenuItem[0];
        capacity = 5;
        size = 0;
    }

    RestaurantOrder(Integer orderNumber, Integer tableNumber, MenuItem[] items)
    {
        this.orderNumber = orderNumber;
        this.tableNumber = tableNumber;

        int n = items.length;
        orderItemsArray=new MenuItem[n];
        for(int i=0; i<n; i++){
            orderItemsArray[i]= items[i];
        }
        size =n;
        capacity =n+5;
    }
    @Override
    public boolean add(MenuItem addingItem) {
        if(size == capacity){
            capacity +=5;
            MenuItem[] resizedOrderItemsArray=new MenuItem[capacity];
            for(int i=0; i<size; i++){
                resizedOrderItemsArray[i] = orderItemsArray[i];
            }
            orderItemsArray=resizedOrderItemsArray;
        }
        orderItemsArray[size]= addingItem;
        size++;

        return true;
    }

    @Override
    public boolean deleteItemByName(String removingItemName) {
        for(int i=size-1; i>0; i--){
            if( orderItemsArray[i].getName().equals(removingItemName)){
                for(int j=i; j<size-1; j++){
                    orderItemsArray[j]=orderItemsArray[j+1];
                }
                size--;
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean deleteItemByMenuItem(MenuItem item) {
        return false;
    }

    @Override
    public int deleteAllItemsWithName(String itemName) {
        int deletedItemsCount=0;
        while (deleteItemByName(itemName)){
            deletedItemsCount++;
        }
        return deletedItemsCount;
    }

    @Override
    public int deleteAllItemsWithMenuItem(MenuItem item) {
        return 0;
    }


    public int dishQuantity(String dishName)
    {
        return 0;
    }





    public String[] dishesNames()
    {
        return new String[0];
    }

    public Dish[] sortedDishesByCostDesc()
    {
        return new Dish[0];
    }

    //    public int dishQuantity()
    //    {
    //        return 0;
    //    }
    @Override
    public int getItemsCount() {
        return size;
    }

    //    public Dish[] getDishes()
    //    {
    //        return new Dish[0];
    //    }
    @Override
    public MenuItem[] getItemsArray() {
        MenuItem[] retArray = new MenuItem[size];
        for(int i=0; i<size; i++){
            retArray[i] = orderItemsArray[i];
        }
        return retArray;
    }
    //    public double costTotal()
    //    {
    //        return 0.0;
    //    }
    @Override
    public double getOrderCost() {
        double total=0.0;
        for(int i=0; i<size; i++){
            total += orderItemsArray[i].getCost();
        }
        return total;
    }

    @Override
    public Customer getCustomer() {
        return null;
    }

    @Override
    public void setCustomer(Customer customer) {

    }

    @Override
    public int getItemCountByName(String itemName) {
        int count=0;
        for(int i=size-1; i>0; i--) {
            if (orderItemsArray[i].getName().equals(itemName)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int getItemCountByMenuItem(MenuItem item) {
        return 0;
    }

    @Override
    public String[] getItemsNames() {

        String[] tmpNames=new String[size];
        tmpNames[0]=orderItemsArray[0].getName();
        int uniqNamesCount=1;
        for(int i=0; i<size; i++) {
            String currentItemName=orderItemsArray[i].getName();
            boolean isPresent=false;
            for(int j=0; j<uniqNamesCount; j++){
                if(currentItemName.equals(tmpNames[j])){
                    isPresent=true;
                    break;
                }
            }
            if(! isPresent){
                tmpNames[uniqNamesCount++]=currentItemName;
            }
        }
        String[] names=new String[uniqNamesCount];
        for(int i=0; i<uniqNamesCount; i++){
            names[i]=tmpNames[i];
        }
        return names;
    }

    @Override
    public MenuItem[] getItemsArrayOrderedByCostDesc() {
        MenuItem[] items =getItemsArray();
        for(int i = size ; i > 0 ; i--){
            for(int j = 0 ; j < i ; j++){

                if( items[j].getCost()> items[j+1].getCost() ){
                    MenuItem tmp = items[j];
                    items[j] = items[j+1];
                    items[j+1] = tmp;
                }
            }
        }
        return items;
    }
}
