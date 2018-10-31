package at.gv.ooe.ietservice.model;

import at.gv.ooe.ietservice.model.DiaryEntry;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandervollovec on 07/05/2017.
 */
@NamedQueries({
        @NamedQuery(name = "Ticket.findAll", query = "select t from Ticket t"),
        @NamedQuery(name = "Ticket.findTicketsFromTicketOwner", query = "select t from Ticket t where t.ticketOwner = :ticketOwner" )
})
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private String startDate;
    private String answerDate;
    private String closedDate;
    private String status;
    private String specialistName;
    private String problemUserName;
    private String priority;
    private String solution;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DiaryEntry> diaryEntries;

    @ManyToOne(cascade =  CascadeType.ALL)
    private User ticketOwner;

    public Ticket() {
    }

    public Ticket(String description, String startDate, String answerDate,
                  String closedDate, String status, String specialistName,
                  String problemUserName, String priority, String solution) {
        this.description = description;
        this.setStartDate(startDate);
        this.setAnswerDate(answerDate);
        this.setClosedDate(closedDate);
        this.setStatus(status);
        this.setSpecialistName(specialistName);
        this.setProblemUserName(problemUserName);
        this.setPriority(priority);
        this.setSolution(solution);
        diaryEntries = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(String answerDate) {
        this.answerDate = answerDate;
    }

    public String getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(String closedDate) {
        this.closedDate = closedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpecialistName() {
        return specialistName;
    }

    public void setSpecialistName(String specialistName) {
        this.specialistName = specialistName;
    }

    public String getProblemUserName() {
        return problemUserName;
    }

    public void setProblemUserName(String problemUserName) {
        this.problemUserName = problemUserName;
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

    public List<DiaryEntry> getDiaryEntries() {
        return diaryEntries;
    }

    public void setDiaryEntries(List<DiaryEntry> diaryEntries) {
        this.diaryEntries = diaryEntries;
    }

    public User getTicketOwner() {
        return ticketOwner;
    }

    public void setTicketOwner(User ticketOwner) {
        this.ticketOwner = ticketOwner;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}

