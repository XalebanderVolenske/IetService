package at.gv.ooe.ietservice.business;

import at.gv.ooe.ietservice.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by alexandervollovec on 26.02.18.
 */
@Stateless
public class UserDao {

    @PersistenceContext
    EntityManager em;

    public void create(User user) {
        em.persist(user);
    }

    public List<User> findAll() {
        return em.createNamedQuery("User.findAll", User.class).getResultList();
    }
    public User findUserByPersNo(String persNo) {
        return em.createNamedQuery("User.findUserByPersNo", User.class).setParameter("persNo", persNo).getSingleResult();
    }
}
