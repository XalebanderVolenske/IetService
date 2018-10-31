package at.gv.ooe.ietservice.rest;

import at.gv.ooe.ietservice.business.TicketDao;
import at.gv.ooe.ietservice.business.UserDao;
import at.gv.ooe.ietservice.model.Ticket;
import at.gv.ooe.ietservice.model.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by alexandervollovec on 06.05.17.
 */
@Path("ticketservice")
@Stateless
public class IetEndpoint {

    @Inject
    TicketDao ticketDao;

    @Inject
    UserDao userDao;


    @GET
    public JsonObject hello() {
        return Json.createObjectBuilder().add("greeting","hello").build();
    }

    @GET
    @Path("tickets")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ticket> getTickets() {

        return ticketDao.findAll();

    }

    @GET
    @Path("/persNo")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTicketsByTicketOwner()
    {
        return "test";
    }
   /* public List<Ticket> getTicketsByTicketOwner(@QueryParam("persNo") String persNo) {
        User ticketOwner = userDao.findUserByPersNo(persNo);
        if(ticketOwner == null)
            return null ;
        return ticketDao.findTicketsFromTicketOwner(ticketOwner);
    }*/
}
