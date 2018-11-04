package at.gv.ooe.ietservice.model;

import javax.naming.Name;
import javax.persistence.*;

/**
 * Created by alexandervollovec on 13.05.17.
 */

@NamedQueries({
        @NamedQuery(name="DiaryEntry.findAll", query = "select de from DiaryEntry de" ),
        @NamedQuery(name="DiaryEntry.getDiaryEntriesFromTicket", query = "select de from DiaryEntry de where de.ticket.id = :ticketId" )
})

@Entity
public class DiaryEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String createdDate;
    private String specialistName;
    private String entry;

    @ManyToOne
    private Ticket ticket;

    public DiaryEntry() {
    }

    public DiaryEntry(String createdDate, String specialistName, String entry, Ticket ticket) {
        this.createdDate = createdDate;
        this.specialistName = specialistName;
        this.entry = entry;
        this.ticket = ticket;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getSpecialistName() {
        return specialistName;
    }

    public void setSpecialistName(String specialistName) {
        this.specialistName = specialistName;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "DiaryEntry{" +
                "id=" + id +
                ", createdDate='" + createdDate + '\'' +
                ", specialistName='" + specialistName + '\'' +
                ", entry='" + entry + '\'' +
                '}';
    }
}
