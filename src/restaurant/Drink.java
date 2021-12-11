package restaurant;

public final class Drink implements Item
{
    private float price;
    private String name;
    private String description;

    Drink(String name, String description)
    {
        if (name==null||name.equals("")||description==null||description.equals("")){
            throw new IllegalArgumentException("Неверные данные для нового блюда");
        }
        this.price=0;
        this.name=name;
        this.description=description;
    }

    Drink(float price, String name, String description)
    {
        if (price<0 || name==null||name.equals("")||description==null||description.equals("")){
            throw new IllegalArgumentException("Неверные данные для нового блюда");
        }
        this.price=price;
        this.name=name;
        this.description=description;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return price;
    }
}