package restaurant;

public final class Customer {

    String firstName;
    String secondName;
    int age;
    Address address;
    Customer MATURE_UNKNOWN_CUSTOMER=null;
    Customer NOT_MATURE_UNKNOWN_CUSTOMER=null;
    public Customer(String firstName,
            String secondName,
            int age,
                    String cityName,
                            int zipCode,
                            String streetName,
                            int buildingNumber,
                            char buildingLetter,
                            int apartmentNumber
                    ){
        this.firstName=firstName;
        this.secondName=secondName;
        this.age=age;
        this.address=new Address(cityName, zipCode, streetName, buildingNumber, buildingLetter, apartmentNumber);
    }
    public String getFirstName(){
        return firstName;
    }

    public String getSecondName(){
        return secondName;
    }
    public int  getAge(){
    return age;
    }

    public Address getAddress(){
        return address;
    }
}