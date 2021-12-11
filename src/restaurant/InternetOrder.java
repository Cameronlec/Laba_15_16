package restaurant;



public class InternetOrder implements Order {


    CircularDoublyLinkedList<Item> orderItems;
    int itemsCount=0;

    InternetOrder()
    {
        orderItems=new CircularDoublyLinkedList<Item>();
        itemsCount=0;
    }

    InternetOrder(Item[] itemsArray)
    {
        orderItems=new CircularDoublyLinkedList<Item>();
        for(int i=0; i<itemsArray.length; i++)
        {
            orderItems.add(itemsArray[i]);
        }
    }


//−удаляющий позицию из заказа по его названию
//(принимает название блюда или напитка в качестве параметра).
//Если позиций с заданным названием несколько, всегда удаляются последние.
//Возвращает логическое значение (true, если элемент был удален).
    public boolean deleteItemByName(String itemName)
    {
        if(orderItems.size()==0){
            return false;
        }
        CircularDoublyLinkedList<Item>.Node<Item> lastNode = orderItems.getTail();
        while (lastNode.getData().getName()!=itemName){
            if (lastNode == orderItems.getHead()) {

                return false;
            }
            lastNode=lastNode.getPreviousNode();
        }
        if (lastNode != orderItems.getHead()) {
            orderItems.remove(lastNode);
            itemsCount--;
            return true;
        }

        return false;
    }

    /*
    −удаляющий все позиции с заданным именем
    (принимает название в качестве параметра).
    Возвращает число удаленных элементов.
     */
    public int deleteAllItemsWithName(String itemName)
    {
        int deletedItemsCount=0;
        while (deleteItemByName(itemName)){
            deletedItemsCount++;
        }
        return deletedItemsCount;
    }

/*
−добавляющий позицию в заказ
(принимает ссылку типа Item).
Пока этот метод возвращает истину
после выполнения операции добавления элемента.
*/
    public boolean addItem(Item newItem){
        orderItems.add(newItem);

        itemsCount++;
        return true;
    }

/*
−возвращающий общее число позиций заказа
(повторяющиеся тоже считаются)
в заказе.
 */
    public int getItemsCount(){
        if(orderItems.size()==itemsCount) {
            return itemsCount;
        }
        else{
            return -1;
        }
    }

/*
−возвращающий массив заказанных блюд и напитков
(значений null в массиве быть не должно).
 */
    public Item[] getItemsArray(){
        if(itemsCount==0){
            return null;
        }
        Item[] items =new Item[itemsCount];

        CircularDoublyLinkedList<Item>.Node<Item> lastNode = orderItems.getHead();
        if (lastNode!=null){
            for(int i=0;i<itemsCount;i++) {
                items[i] = lastNode.getData();
                lastNode=lastNode.getNextNode();
            }
        }
        return items;
    }

/*
−возвращающий общую стоимость заказа.
 */
    public double getOrderCost(){
        double cost=0.0;
        CircularDoublyLinkedList<Item>.Node<Item> lastNode = orderItems.getHead();
        while (true){
            if(lastNode==null){
                return 0.0;
            }
            cost+=lastNode.getData().getCost();
            lastNode=lastNode.getNextNode();
            if (lastNode == orderItems.getHead()) {
                break;

            }
        }
        return cost;
    }

/*
−возвращающий массив названий заказанных блюд и напитков
(без повторов).
 */
    public String[] getItemsNames() {
        if (itemsCount == 0) {
            return null;
        }
        Item[] items = getItemsArray();
        String[] tmpNames=new String[itemsCount];
        tmpNames[0]= items[0].getName();
        int uniqNamesCount=1;

        for(int i=1; i<itemsCount; i++){
            String currentItemName= items[i].getName();
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



/*
−возвращающий число заказанных блюд или напитков
(принимает название блюда или напитка в качестве параметра).
 */
public int getItemCountByName(String itemName){
    if(itemsCount==0){
        return 0;
    }
    int count=0;

    CircularDoublyLinkedList<Item>.Node<Item> lastNode = orderItems.getHead();
    if (lastNode!=null){
        for(int i=0;i<itemsCount;i++) {
            if( lastNode.getData().getName().equals(itemName)){
                count++;
            };
            lastNode=lastNode.getNextNode();
        }
    }
    return count;
}

/*
−возвращающий массив позиций заказа,
отсортированный по убыванию цены.
 */
     public Item[] getItemsArrayOrderedByCostDesc(){
         if(itemsCount==0){
             return null;
         }
         Item[] items =getItemsArray();
         for(int i = itemsCount ; i > 0 ; i--){
             for(int j = 0 ; j < i ; j++){

                if( items[j].getCost()> items[j+1].getCost() ){
                    Item tmp = items[j];
                    items[j] = items[j+1];
                    items[j+1] = tmp;
                }
            }
        }
         return items;
     }
}
