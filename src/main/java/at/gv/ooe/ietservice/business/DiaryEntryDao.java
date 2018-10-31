package at.gv.ooe.ietservice.business;

import at.gv.ooe.ietservice.model.DiaryEntry;
import at.gv.ooe.ietservice.model.Ticket;
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
public class DiaryEntryDao {

    @PersistenceContext
    EntityManager em;

    public void create(DiaryEntry diaryEntry) {
        em.persist(diaryEntry);
    }


    public List<DiaryEntry> findAll() {
        TypedQuery<DiaryEntry> query = em
                .createNamedQuery("DiaryEntry.findAll", DiaryEntry.class);
        return query.getResultList();
    }

}
