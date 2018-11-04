package at.gv.ooe.ietservice.model;

import javax.persistence.*;

/**
 * Created by alexandervollovec on 13.05.17.
 */

@NamedQueries({
        @NamedQuery(name = "User.findAll", query = "select u from User u"),
        @NamedQuery(name = "User.getUserByPersNo", query = "select u from User u where u.persno = :persNo")
})

@Entity
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String persno;
    private String name;
    private String telephoneNumber;
    private String emailAddress;
    private String postcode;
    private String address;
    private String roomNo;

    @ManyToOne
    private Department department;

    public User() {
    }

    public User(String persno, String name, String telephoneNumber, String emailAddress, String postcode, String address, String roomNo, Department department) {
        this.persno = persno;
        this.name = name;
        this.telephoneNumber = telephoneNumber;
        this.emailAddress = emailAddress;
        this.postcode = postcode;
        this.address = address;
        this.roomNo = roomNo;
        this.department = department;
    }

    public String getPersno() {
        return persno;
    }

    public void setPersno(String persno) {
        this.persno = persno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", persno='" + persno + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
