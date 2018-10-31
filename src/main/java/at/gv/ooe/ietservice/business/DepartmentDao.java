package at.gv.ooe.ietservice.business;

import at.gv.ooe.ietservice.model.Department;

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

}
