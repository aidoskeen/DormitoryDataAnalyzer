package Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Resident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int residentId;
    private String name = "";
    private String surname = "";
    private String country = "";
    private String dormitory = "";
    private String room = "";
    private Long debtAmount;

    public Resident(String name, String surname, String country, String dormitory, String room, Long debtAmount) {
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.dormitory = dormitory;
        this.room = room;
        this.debtAmount = debtAmount;
    }

    public Resident() {

    }

    public int getResidentId() {
        return residentId;
    }

    public void setResidentId(int residentId) {
        this.residentId = residentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDormitory() {
        return dormitory;
    }

    public void setDormitory(String dormitory) {
        this.dormitory = dormitory;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Long getDebtAmount() {
        return debtAmount;
    }

    public void setDebtAmount(Long debtAmount) {
        this.debtAmount = debtAmount;
    }

    @Override
    public String toString() {
        return "Resident{" +
                "residentId=" + residentId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", country='" + country + '\'' +
                ", dormitory='" + dormitory + '\'' +
                ", room='" + room + '\'' +
                ", debtAmount=" + debtAmount +
                '}';
    }
}
