package at.gv.ooe.ietservice.model;

import javax.naming.Name;
import javax.persistence.*;

/**
 * Created by alexandervollovec on 13.05.17.
 */

@NamedQueries({
        @NamedQuery(name="DiaryEntry.findAllForTicket", query = "select de from DiaryEntry de" )
})

@Entity
public class DiaryEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String createdDate;
    private String specialistName;
    private String entry;

    public DiaryEntry() {
    }

    public DiaryEntry(String createdDate, String specialistName, String entry) {
        this.setCreatedDate(createdDate);
        this.setSpecialistName(specialistName);
        this.setEntry(entry);
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
