package at.gv.ooe.ietservice.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by alexandervollovec on 07/05/2017.
 */
@NamedQueries({
        @NamedQuery(name = "Ticket.findAll", query = "select t from Ticket t"),
        @NamedQuery(name = "Ticket.getTicketsFromUser", query = "select t from Ticket t where t.ticketOwner.id = :userId"),
        @NamedQuery(name = "Ticket.getTicketsFromDepartment", query = "select t from Ticket t where t.ticketOwner.department.id = :departmentId"),
        @NamedQuery(name = "Ticket.getTicketsByDateRangeFromUser", query = "select t from Ticket t where t.ticketOwner.id = :userId and t.startDate between :startDate and :endDate")
})
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String shortDesc;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime answerDate;
    private String status;
    private String priority;
    private String solution;
    private String service;

    @ManyToOne(cascade =  CascadeType.ALL)
    private User ticketOwner;

    @ManyToOne(cascade =  CascadeType.ALL)
    private User problemUser;

    @ManyToOne(cascade = CascadeType.ALL)
    private User specialistUser;

    public Ticket() {
    }

    public Ticket(String shortDesc, String description, LocalDateTime startDate, LocalDateTime answerDate, String status, String priority, String solution, String service, User ticketOwner, User problemUser, User specialistUser) {
        this.shortDesc = shortDesc;
        this.description = description;
        this.startDate = startDate;
        this.answerDate = answerDate;
        this.status = status;
        this.priority = priority;
        this.solution = solution;
        this.service = service;
        this.ticketOwner = ticketOwner;
        this.problemUser = problemUser;
        this.specialistUser = specialistUser;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(LocalDateTime answerDate) {
        this.answerDate = answerDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public User getTicketOwner() {
        return ticketOwner;
    }

    public void setTicketOwner(User ticketOwner) {
        this.ticketOwner = ticketOwner;
    }

    public User getProblemUser() {
        return problemUser;
    }

    public void setProblemUser(User problemUser) {
        this.problemUser = problemUser;
    }

    public User getSpecialistUser() {
        return specialistUser;
    }

    public void setSpecialistUser(User specialistUser) {
        this.specialistUser = specialistUser;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }

}

