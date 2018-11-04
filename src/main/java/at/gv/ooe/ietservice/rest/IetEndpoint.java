package at.gv.ooe.ietservice.rest;

import at.gv.ooe.ietservice.business.DepartmentDao;
import at.gv.ooe.ietservice.business.DiaryEntryDao;
import at.gv.ooe.ietservice.business.TicketDao;
import at.gv.ooe.ietservice.business.UserDao;
import at.gv.ooe.ietservice.model.*;
import jdk.nashorn.internal.objects.annotations.Getter;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    DepartmentDao departmentDao;

    @Inject
    DiaryEntryDao diaryEntryDao;

    @Inject
    UserDao userDao;

    @GET
    public JsonObject hello() {
        return Json.createObjectBuilder().add("greeting","hello").build();
    }

    @GET
    @Path("ticket")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ticket> getTickets() {
        return ticketDao.findAll();
    }

    @GET
    @Path("diaryEntry")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DiaryEntry> getDiaryEntries() {
        return diaryEntryDao.findAll();
    }

    @GET
    @Path("department")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Department> getDepartments() {
        return departmentDao.findAll();
    }

    @GET
    @Path("user")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsers() {
        return userDao.findAll();
    }

    @GET
    @Path("ci")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ConfigurationItem> getCIs() {
        return new ArrayList<>();
    }

    @GET
    @Path("ticket/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ticket> getTicketsFromUser(@PathParam("userId") String userId) {
        return ticketDao.getTicketsFromUser(Long.parseLong(userId));
    }

    @GET
    @Path("departmentticket/{departmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ticket> getTicketsFromDepartment(@PathParam("departmentId") String departmentId) {
        System.out.println("------------------ IetEndpoint: ---------------- DptId: " + departmentId + "-----------------");
        return ticketDao.getTicketsFromDepartment(Long.parseLong(departmentId));
    }

    @GET
    @Path("ticket/{userId}/{strgStartRange}/{strgEndRange}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ticket> getTicketsByDateRangeFromUser(@PathParam("userId") String userId, @PathParam("strgStartRange") String strgStartRange, @PathParam("strgEndRange") String strgEndRange) {
        System.out.println("------------------ IetEndpoint: ---------------- UserId: " + userId + "-----------------");
        return ticketDao.getTicketsByDateRangeFromUser(Long.parseLong(userId), strgStartRange, strgEndRange);
    }

    @GET
    @Path("ticketdiary/{ticketId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DiaryEntry> getDiaryEntriesFromTicket(@PathParam("ticketId") String ticketId) {
        System.out.println("------------------ IetEndpoint: ---------------- TicketId: " + ticketId + "-----------------");
        return diaryEntryDao.getDiaryEntriesFromTicket(Long.parseLong(ticketId));
    }

    @GET
    @Path("/ci/{departmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ConfigurationItem> getCIsFromDepartment(@PathParam("departmentId") String departmentId) {
        System.out.println("------------------ IetEndpoint: ---------------- DptID: " + departmentId + "-----------------");
        return new ArrayList<>();
    }


    @GET
    @Path("/user/{persNo}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserByPersNo(@PathParam("persNo") String persNo) {
        System.out.println("------------------ IetEndpoint: ---------------- PersNo: " + persNo + "-----------------");
        return userDao.findUserByPersNo(persNo);
    }
}
