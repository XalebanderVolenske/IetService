package at.gv.ooe.ietservice.business;


import at.gv.ooe.ietservice.model.Department;
import at.gv.ooe.ietservice.model.DiaryEntry;
import at.gv.ooe.ietservice.model.Ticket;
import at.gv.ooe.ietservice.model.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.time.LocalDateTime;

/**
 * Created by alexandervollovec on 07/05/2017.
 */
@Startup
@Singleton
public class InitBean {

    @Inject
    TicketDao ticketDao;

    @Inject
    DepartmentDao departmentDao;

    @Inject
    DiaryEntryDao diaryEntryDao;

    @Inject
    UserDao userDao;

    @PostConstruct
    private void init() {
        System.err.println("*************** InitBean Version 2 ******************");
        Department dept1 = new Department("Wohnbaufoerderung");
        Department dept2 = new Department("Naturschutz");
        Department dept3 = new Department("Informationstechnologie");
        departmentDao.create(dept1);
        departmentDao.create(dept2);
        departmentDao.create(dept3);

        //User Data: String persno, String name, String telephoneNumber, String emailAddress, String postcode, String address, String roomNo, Department department
        User ticketowner1 = new User("P12345678", "Simon Seppl", "0732 673333", "alex_v@gmx.at", "4021", "Bahnhofplatz 1", "1E200", dept1);
        User problemuser1 = new User("P87654321", "Papa", "0660 803408405", "alex_v@gmx.at", "4021", "Bahnhofplatz 1", "1E200", dept1);
        User problemuser2 = new User("P23456789", "Onkel Thomas", "0664 9166835", "alex_v@gmx.at", "4021", "Bahnhofplatz 1", "1E200", dept1);
        User ticketowner2= new User("P42345678", "Konrad Knauser", "0732 673333", "alex_v@gmx.at", "4021", "Bahnhofplatz 1", "1E200", dept2);
        User problemuser3 = new User("P12345654", "Emma Ehrlich", "0732 673333", "alex_v@gmx.at", "4021", "Bahnhofplatz 1", "1E200", dept2);
        User problemuser4 = new User("P54323456", "Gisela Grusel", "0732 673333", "alex_v@gmx.at", "4021", "Bahnhofplatz 1", "1E200", dept2);
        User problemuser5 = new User("P10987654", "Harald Haudrauf", "0732 673333", "alex_v@gmx.at", "4021", "Bahnhofplatz 1", "1E200", dept2);
        User specialistuser1 = new User("P23467654", "Gottwalt Gottschalk", "0732 673333", "alex_v@gmx.at", "4021", "Bahnhofplatz 1", "1E200", dept3);
        User specialistuser2 = new User("P65432345", "Arno Ärmlich","0732 673333", "alex_v@gmx.at", "4021", "Bahnhofplatz 1", "1E200", dept3);
        User specialistuser3 = new User("P87654322", "Hansi Hinterseer", "0732 673333", "d-volli@hotmail.com", "4021", "Bahnhofplatz 1", "1E200", dept3);
        userDao.create(ticketowner1);
        userDao.create(problemuser1);
        userDao.create(problemuser2);
        userDao.create(ticketowner2);
        userDao.create(problemuser3);
        userDao.create(problemuser4);
        userDao.create(problemuser5);
        userDao.create(specialistuser1);
        userDao.create(specialistuser2);
        userDao.create(specialistuser3);


        //Ticket Data: String shortDesc, String description, LocalDateTime startDate, LocalDateTime answerDate, String status, String priority, String solution, String service, User ticketOwner, User problemUser, User specialistUser
        Ticket ticket2 = new Ticket("Toner nicht geliefert", "Toner bereits seit zwei Tagen leer. Wir brauch dringend Ersatz!", LocalDateTime.of(2018, 10, 18, 15, 0), LocalDateTime.of(2018, 10, 25, 15, 0), "Aktiv", "Nicht Dringend", "", "Arbeitsplatz", ticketowner1, problemuser1, specialistuser1);
        Ticket ticket3 = new Ticket("Kein Strom", "Bildschirm lässt sich nicht einschalten.", LocalDateTime.of(2018, 10, 25, 14, 30), LocalDateTime.of(2018, 10, 30, 12, 0), "Ausgesetzt","Dringend", "", "Arbeitsplatz", ticketowner1, problemuser2, specialistuser1);
        Ticket ticket4 = new Ticket("Einrichten digitales Diktieren", "Bitte um Hilfestellung beim Einrichten des 'Digitale Diktieren'-Programms", LocalDateTime.of(2018, 10, 30, 15, 0), LocalDateTime.of(2018, 11, 02, 15, 0),  "Aktiv","Sehr dringend","", "Digitales Diktieren", ticketowner1, problemuser3, specialistuser3);
        Ticket ticket5 = new Ticket("Drucker schmiert", "Beim Ausdruck werden immer Schlieren gezogen. Vermutlich ist der Tintenkopf verschmutzt. Bitte um Anforderung eines Technikers.", LocalDateTime.of(2018, 11, 01, 10, 0), LocalDateTime.of(2018, 04, 06, 10, 0), "Aktiv",  "Dringend", "", "Drucken", ticketowner1, problemuser4, specialistuser3);
        Ticket ticket6 = new Ticket("PC fährt nicht hoch.", "Beim Einschalten des Geräts bleibt der Bildschirm schwarz obwohl der PC läuft.", LocalDateTime.of(2018, 9, 27, 12, 15), LocalDateTime.of(2018, 10, 01, 12,0), "Ausgesetzt", "Sehr dringend", "", "Arbeitsplatz", ticketowner1, problemuser5, specialistuser3);
        Ticket ticket7 = new Ticket("Probleme beim Anmelden des iPhones", "Mobile Registrierung nicht möglich da kein Passwort gesendet wurde.", LocalDateTime.of(2018, 10, 16, 8,30), LocalDateTime.of(2018, 11,1,7,30), "Aktiv", "Nicht dringedn", "",  "PIM", ticketowner1, problemuser4, specialistuser3);
        Ticket ticket8 = new Ticket("Toner nicht geliefert", "Toner bereits seit zwei Tagen leer. Wir brauch dringend Ersatz!", LocalDateTime.of(2018, 10, 18, 15, 0), LocalDateTime.of(2018, 10, 25, 15, 0), "Aktiv", "Nicht Dringend", "", "Arbeitsplatz", ticketowner1, problemuser1, specialistuser1);
        Ticket ticket9 = new Ticket("Kein Strom", "Bildschirm lässt sich nicht einschalten.", LocalDateTime.of(2018, 10, 25, 14, 30), LocalDateTime.of(2018, 10, 30, 12, 0), "Ausgesetzt","Dringend", "", "Arbeitsplatz", ticketowner1, problemuser2, specialistuser1);
        Ticket ticket10 = new Ticket("Einrichten digitales Diktieren", "Bitte um Hilfestellung beim Einrichten des 'Digitale Diktieren'-Programms", LocalDateTime.of(2018, 10, 30, 15, 0), LocalDateTime.of(2018, 11, 02, 15, 0),  "Aktiv","Sehr dringend","", "Digitales Diktieren", ticketowner1, problemuser3, specialistuser3);
        Ticket ticket11 = new Ticket("Drucker schmiert", "Beim Ausdruck werden immer Schlieren gezogen. Vermutlich ist der Tintenkopf verschmutzt. Bitte um Anforderung eines Technikers.", LocalDateTime.of(2018, 11, 01, 10, 0), LocalDateTime.of(2018, 04, 06, 10, 0), "Aktiv",  "Dringend", "", "Drucken", ticketowner1, problemuser4, specialistuser3);
        Ticket ticket12 = new Ticket("PC fährt nicht hoch.", "Beim Einschalten des Geräts bleibt der Bildschirm schwarz obwohl der PC läuft.", LocalDateTime.of(2018, 9, 27, 12, 15), LocalDateTime.of(2018, 10, 01, 12,0), "Ausgesetzt", "Sehr dringend", "", "Arbeitsplatz", ticketowner1, problemuser5, specialistuser3);
        Ticket ticket13 = new Ticket("Probleme beim Anmelden des iPhones", "Mobile Registrierung nicht möglich da kein Passwort gesendet wurde.", LocalDateTime.of(2018, 10, 16, 8,30), LocalDateTime.of(2018, 11,1,7,30), "Aktiv", "Nicht dringedn", "",  "PIM", ticketowner1, problemuser4, specialistuser3);
        Ticket ticket14 = new Ticket("Toner nicht geliefert", "Toner bereits seit zwei Tagen leer. Wir brauch dringend Ersatz!", LocalDateTime.of(2018, 10, 18, 15, 0), LocalDateTime.of(2018, 10, 25, 15, 0), "Aktiv", "Nicht Dringend", "", "Arbeitsplatz", ticketowner1, problemuser1, specialistuser1);
        Ticket ticket15 = new Ticket("Kein Strom", "Bildschirm lässt sich nicht einschalten.", LocalDateTime.of(2018, 10, 25, 14, 30), LocalDateTime.of(2018, 10, 30, 12, 0), "Ausgesetzt","Dringend", "", "Arbeitsplatz", ticketowner1, problemuser2, specialistuser1);
        Ticket ticket16= new Ticket("Einrichten digitales Diktieren", "Bitte um Hilfestellung beim Einrichten des 'Digitale Diktieren'-Programms", LocalDateTime.of(2018, 10, 30, 15, 0), LocalDateTime.of(2018, 11, 02, 15, 0),  "Aktiv","Sehr dringend","", "Digitales Diktieren", ticketowner2, problemuser3, specialistuser3);
        Ticket ticket17 = new Ticket("Drucker schmiert", "Beim Ausdruck werden immer Schlieren gezogen. Vermutlich ist der Tintenkopf verschmutzt. Bitte um Anforderung eines Technikers.", LocalDateTime.of(2018, 11, 01, 10, 0), LocalDateTime.of(2018, 04, 06, 10, 0), "Aktiv",  "Dringend", "", "Drucken", ticketowner1, problemuser4, specialistuser3);
        Ticket ticket18 = new Ticket("PC fährt nicht hoch.", "Beim Einschalten des Geräts bleibt der Bildschirm schwarz obwohl der PC läuft.", LocalDateTime.of(2018, 9, 27, 12, 15), LocalDateTime.of(2018, 10, 01, 12,0), "Ausgesetzt", "Sehr dringend", "", "Arbeitsplatz", ticketowner2, problemuser5, specialistuser3);
        Ticket ticket19 = new Ticket("Probleme beim Anmelden des iPhones", "Mobile Registrierung nicht möglich da kein Passwort gesendet wurde.", LocalDateTime.of(2018, 10, 16, 8,30), LocalDateTime.of(2018, 11,1,7,30), "Aktiv", "Nicht dringedn", "",  "PIM", ticketowner2, problemuser4, specialistuser3);
        ticketDao.create(ticket2);
        ticketDao.create(ticket3);
        ticketDao.create(ticket4);
        ticketDao.create(ticket5);
        ticketDao.create(ticket6);
        ticketDao.create(ticket7);
        ticketDao.create(ticket8);
        ticketDao.create(ticket9);
        ticketDao.create(ticket10);
        ticketDao.create(ticket11);
        ticketDao.create(ticket12);
        ticketDao.create(ticket13);
        ticketDao.create(ticket14);
        ticketDao.create(ticket15);
        ticketDao.create(ticket16);
        ticketDao.create(ticket17);
        ticketDao.create(ticket18);
        ticketDao.create(ticket19);

        diaryEntryDao.create(new DiaryEntry("28.10.2018 15:30", "Gottwalt Gottschalk", "Tonerbestellung muss manuell durchgeführt werden.", ticket3));
        diaryEntryDao.create(new DiaryEntry("29.10.2018 16:15", "Gottwalt Gottschalk", "Warten auf Anlieferung", ticket3));
        diaryEntryDao.create(new DiaryEntry("01.10.2018 15:30", "Gottwalt Gottschalk", "Einsetzen des neuen Toners und Monitoring starten um Ursache zu finden.", ticket3));

    }

}
