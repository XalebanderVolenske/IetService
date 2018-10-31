package at.gv.ooe.ietservice.model;

import javax.persistence.*;

/**
 * Created by alexandervollovec on 13.05.17.
 */

@NamedQueries({
        @NamedQuery(name = "Department.findAll", query = "select dept from Department dept")
})

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String name;

//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<User> users = new ArrayList<>();
//
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<ConfigurationItem> ciList = new ArrayList<>();

    public Department() {
    }

    public Department(String name) {
        this.setName(name);
    }


    public String getName() {
        return name;
    }

    public void setName(String departmentName) {
        this.name = departmentName;
    }

//    public List<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }
//
//    public List<ConfigurationItem> getCiList() {
//        return ciList;
//    }
//
//    public void setCiList(List<ConfigurationItem> ciList) {
//        this.ciList = ciList;
//    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
