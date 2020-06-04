import java.io.Serializable;

public class DanhBa implements Serializable {
    private int phone;
    private String tem;
    private String name;
    private String gender;
    private String address;
    private String dateOfBirth;
    private String email;

    public DanhBa() {
    }

    public DanhBa(int phone, String tem, String name, String gender, String address, String dateOfBirth, String email) {
        this.phone = phone;
        this.tem = tem;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getTem() {
        return tem;
    }

    public void setTem(String tem) {
        this.tem = tem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "phone : " + phone +
                "\nTem : " + tem +
                "\nname : " + name +
                "\ngender : " + gender +
                " \naddress: " + address +
                "\ndateOfBirth : "+dateOfBirth+
                "\nemail: " + email;
    }
}
