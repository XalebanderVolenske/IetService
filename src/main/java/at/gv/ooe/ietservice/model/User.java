package at.gv.ooe.ietservice.model;

import javax.persistence.*;

/**
 * Created by alexandervollovec on 13.05.17.
 */

@NamedQueries({
        @NamedQuery(name = "User.findAll", query = "select u from User u"),
        @NamedQuery(name = "User.findUserByPersNo", query = "select u from User u where u.persno = :persNo")
})

@Entity
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String persno;
    private String name;

    public User() {
    }

    public User(String persno, String name) {
        this.persno = persno;
        this.name = name;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", persno='" + persno + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
