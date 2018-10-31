package at.gv.ooe.ietservice.business;

import at.gv.ooe.ietservice.model.Ticket;
import at.gv.ooe.ietservice.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by alexandervollovec on 17.01.18.
 */
@Stateless
public class TicketDao {

    @PersistenceContext
    EntityManager em;

    public void create(Ticket ticket) {
        em.persist(ticket);
    }

    public List<Ticket> findAll() {
        TypedQuery<Ticket> query = em
                .createNamedQuery("Ticket.findAll", Ticket.class);
        return query.getResultList();

    }

    public List<Ticket> findTicketsFromTicketOwner(User ticketOwner) {
        TypedQuery<Ticket> query = em
                .createNamedQuery("Ticket.findTicketsFromTicketOwner", Ticket.class);
        return query.setParameter("ticketOwner", ticketOwner).getResultList();
    }
}
