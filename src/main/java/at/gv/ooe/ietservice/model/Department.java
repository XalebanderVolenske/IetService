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

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
