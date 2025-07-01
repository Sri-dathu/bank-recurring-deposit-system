package bank;

public class Customer {
    private String name;
    private String dob;
    private int age;
    private String sex;
    private String phoneNumber;
    private String email;
    private String address;

    public Customer(String name, String dob, int age, String sex, String phoneNumber, String email, String address) {
        this.name = name;
        this.dob = dob;
        this.age = age;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }
}
