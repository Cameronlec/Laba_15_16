package restaurant;

public final class Dish extends MenuItem
{
//    private int cost;
//    private String name;
//    private String description;

    Dish(String name, String description)
    {
        if (name==null||name.equals("")||description==null||description.equals("")){
            throw new IllegalArgumentException("Неверные данные для нового блюда");
        }
        this.cost =0;
        this.name=name;
        this.description=description;
    }

    Dish(int price, String name, String description)
    {
        if (price<0 || name==null||name.equals("")||description==null||description.equals("")){
            throw new IllegalArgumentException("Неверные данные для нового блюда");
        }
        this.cost =price;
        this.name=name;
        this.description=description;
    }

//    public String getDescription() {
//        return description;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public int getCost() {
//        return cost;
//    }
}
