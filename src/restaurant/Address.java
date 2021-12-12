package restaurant;

import java.util.ArrayDeque;

public final class Address {
    String cityName;
    int zipCode;
    String streetName;
    int buildingNumber;
    char buildingLetter;
    int apartmentNumber;
    public Address EMPTY_ADDRESS;

    Address(){
         cityName="";
         zipCode=-1;
         streetName="";
         buildingNumber=-1;
         buildingLetter= 0x0;
         apartmentNumber=-1;
         EMPTY_ADDRESS=null;
    }

    Address(String cityName, int zipCode, String streetName, int buildingNumber, char buildingLetter, int apartmentNumber){
       this.cityName=cityName;
        this.zipCode=zipCode;
        this.streetName=streetName;
        this.buildingNumber=buildingNumber;
        this.buildingLetter= buildingLetter;
        this.apartmentNumber=apartmentNumber;
        this.EMPTY_ADDRESS=null;
    }

    public String getC1tyName(){
        return cityName;
    }

    public int getZpCode(){
        return zipCode;
    }

    public String getStreetName(){
        return streetName;
    }
    public int getBuildingNumber(){
        return buildingNumber;
    }
    public char getBuildingLetter(){
        return buildingLetter;
    }
    public int getApartmentNumber(){
        return apartmentNumber;
    }

}
