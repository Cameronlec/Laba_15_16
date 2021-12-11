package restaurant;

import java.io.StringReader;
enum DrinkTypeEnum{
    BEER,
    WINE,
    VODKA,
    BRANDY,
    CHAMPAGNE,
    WHISKEY,
    TEQUILA,
    RUM,
    VERMUTH,
    LIQUOR,
    JAGERMEISTER,
    JUICE,
    COFFEE,
    GREEN_TEA,
    BLACK_TEA,
    MILK,
    WATER,
    SODA
}

public final class Drink extends MenuItem implements Alcoholable
{
//    private float price;
//    private String name;
//    private String description;
    private double alcoholVol;
    private DrinkTypeEnum type;

    Drink(String name, String description)
    {
        if (name==null||name.equals("")||description==null||description.equals("")){
            throw new IllegalArgumentException("Неверные данные для нового блюда");
        }
        this.cost=0;
        this.name=name;
        this.description=description;
    }

    Drink(int price, String name, String description)
    {
        if (price<0 || name==null||name.equals("")||description==null||description.equals("")){
            throw new IllegalArgumentException("Неверные данные для нового блюда");
        }
        this.cost=price;
        this.name=name;
        this.description=description;
    }

    public DrinkTypeEnum getType(){
        return type;
    }

    @Override
    public boolean isAlcoholicDrink() {
        if(alcoholVol==0.0){
            return false;
        }
        return true;
    }

    @Override
    public double getAlcoholVol() {
        return alcoholVol;
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
//        return price;
//    }
}