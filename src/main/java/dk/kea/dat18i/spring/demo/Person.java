package dk.kea.dat18i.spring.demo;


public class Person {

    private int id;
    private String name;
    private String phoneNumber;

    private int[] carId;

    public Person(int id, String name, String phoneNumber)
    {
        this.id=id;
        this.name=name;
        this.phoneNumber=phoneNumber;
        carId=new int[100];

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public int[] getCarId() {
        return carId;
    }

    public void setCarId(int[] carId) {
        this.carId = carId;
    }
}
