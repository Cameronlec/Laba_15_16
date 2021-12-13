package restaurant;

public interface Order {
    boolean add(MenuItem addingItem);
    String[] getItemsNames();
    int getItemsCount();
    int getItemCountByName(String itemName);
    int getItemCountByMenuItem(MenuItem item);
    MenuItem[] getItemsArray();
    boolean deleteItemByName(String removingItemName);
    boolean deleteItemByMenuItem(MenuItem item);
    int deleteAllItemsWithName(String itemName);
    int deleteAllItemsWithMenuItem(MenuItem item);
    MenuItem[] getItemsArrayOrderedByCostDesc();
    double getOrderCost();
    Customer getCustomer();
    void setCustomer(Customer customer);

}
