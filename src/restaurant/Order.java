package restaurant;

public interface Order {
    boolean addItem(Item addingItem);
    boolean deleteItemByName(String removingItemName);
    int deleteAllItemsWithName(String itemName);
    int getItemsCount();
    Item[] getItemsArray();
    double getOrderCost();
    int getItemCountByName(String itemName);
    String[] getItemsNames();
    Item[] getItemsArrayOrderedByCostDesc();


}
