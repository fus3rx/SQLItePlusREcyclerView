package nazmul.problem2;

/**
 * Created by Nazmul on 4/15/2017.
 */

public class Employee {
    private long id;
    private String first_name;
    private String last_name;
    private String phone;
    private String country_name;

    public Employee(long id, String first_name, String last_name, String phone, String country_name) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phone;
        this.country_name = country_name;
    }

    public Employee(String first_name, String last_name, String phone, String country_name) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phone;
        this.country_name = country_name;
    }

    public Employee() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }
}
