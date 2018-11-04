package at.gv.ooe.ietservice.business;

import at.gv.ooe.ietservice.model.DiaryEntry;
import at.gv.ooe.ietservice.model.Ticket;
import at.gv.ooe.ietservice.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public List<Ticket> getTicketsFromUser(long userId) {
        return em.createNamedQuery("Ticket.getTicketsFromUser", Ticket.class).setParameter("userId", userId).getResultList();
    }

    public List<Ticket> getTicketsFromDepartment(long departmentId) {
        return em.createNamedQuery("Ticket.getTicketsFromDepartment", Ticket.class).setParameter("departmentId", departmentId).getResultList();
    }

    public List<Ticket> getTicketsByDateRangeFromUser(long userId, String strgStartRange, String strgEndRange) {
        System.out.println("------------------ IetEndpoint: ---------------- UserId: " + userId + "-----------------");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime startDate = LocalDateTime.parse(strgStartRange, formatter);
        LocalDateTime endDate = LocalDateTime.parse(strgEndRange, formatter);
        return em.createNamedQuery("Ticket.getTicketsByDateRangeFromUser", Ticket.class)
                .setParameter("userId", userId)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate).getResultList();
    }

}
