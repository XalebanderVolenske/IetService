package at.gv.ooe.ietservice.business;

import at.gv.ooe.ietservice.model.Department;
import at.gv.ooe.ietservice.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by alexandervollovec on 26.02.18.
 */
@Stateless
public class DepartmentDao {

    @PersistenceContext
    EntityManager em;

    public void create(Department department) {
        em.persist(department);
    }

    public List<Department> findAll() {
        TypedQuery<Department> query = em
                .createNamedQuery("Department.findAll", Department.class);
        return query.getResultList();

    }

    public Department getDepartmentFromUser(long userId) {
        System.out.println("------------------ DptDao: ---------------- UserID: " + userId + "-----------------");
        return em.createNamedQuery("Department.getDepartmentFromUser", Department.class).setParameter("userId", userId).getSingleResult();
    }

}
