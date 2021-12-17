package restaurant;



public class InternetOrder implements Order {


    CircularDoublyLinkedList<MenuItem> orderItems;
    int itemsCount=0;
    Customer customer;

    InternetOrder()
    {

        orderItems=new CircularDoublyLinkedList<MenuItem>();
        itemsCount=0;
    }
    InternetOrder(String firstName,
                  String secondName,
                  int age,
                  String cityName,
                  int zipCode,
                  String streetName,
                  int buildingNumber,
                  char buildingLetter,
                  int apartmentNumber){
        orderItems=new CircularDoublyLinkedList<MenuItem>();
        itemsCount=0;
        customer=new Customer(firstName,
                 secondName,
         age,
         cityName,
         zipCode,
         streetName,
         buildingNumber,
         buildingLetter,
         apartmentNumber);

    }

    InternetOrder(MenuItem[] itemsArray)
    {
        orderItems=new CircularDoublyLinkedList<MenuItem>();
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
        CircularDoublyLinkedList<MenuItem>.Node<MenuItem> lastNode = orderItems.getTail();
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

    @Override
    public boolean deleteItemByMenuItem(MenuItem item) {
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

    @Override
    public int deleteAllItemsWithMenuItem(MenuItem item) {
        return 0;
    }

    /*
    −добавляющий позицию в заказ
    (принимает ссылку типа Item).
    Пока этот метод возвращает истину
    после выполнения операции добавления элемента.
    */
    public boolean add(MenuItem newItem){
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
    public MenuItem[] getItemsArray(){
        if(itemsCount==0){
            return null;
        }
        MenuItem[] items =new MenuItem[itemsCount];

        CircularDoublyLinkedList<MenuItem>.Node<MenuItem> lastNode = orderItems.getHead();
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
        CircularDoublyLinkedList<MenuItem>.Node<MenuItem> lastNode = orderItems.getHead();
        while (true){
            if(lastNode==null){
                return cost;
            }
            cost+=lastNode.getData().getCost();
            lastNode=lastNode.getNextNode();
            if (lastNode == orderItems.getHead()) {
                break;

            }
        }
        return cost;
    }

    @Override
    public Customer getCustomer() {
        return null;
    }

    @Override
    public void setCustomer(Customer customer) {


    }

    /*
    −возвращающий массив названий заказанных блюд и напитков
    (без повторов).
     */
    public String[] getItemsNames() {
        if (itemsCount == 0) {
            return null;
        }
        MenuItem[] items = getItemsArray();
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

    CircularDoublyLinkedList<MenuItem>.Node<MenuItem> lastNode = orderItems.getHead();
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

    @Override
    public int getItemCountByMenuItem(MenuItem item) {
        return 0;
    }

    /*
    −возвращающий массив позиций заказа,
    отсортированный по убыванию цены.
     */
     public MenuItem[] getItemsArrayOrderedByCostDesc(){
         if(itemsCount==0){
             return null;
         }
         MenuItem[] items =getItemsArray();
         for(int i = itemsCount ; i > 0 ; i--){
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
