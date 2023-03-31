package Models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Month;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;
    @ManyToOne
    private Resident resident = new Resident();
    private String paymentType = "";
    private String paymentMonth = "";
    private String paymentMethod = "";
    private Long paymentAmount = 0L;

    public Long getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Long paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Payment(Resident resident, String paymentType, String paymentMonth, String paymentMethod, Long paymentAmount) {
        this.resident = resident;
        this.paymentType = paymentType;
        this.paymentMonth = paymentMonth;
        this.paymentMethod = paymentMethod;
        this.paymentAmount = paymentAmount;
    }

    public Payment() {

    }


    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentMonth() {
        return paymentMonth;
    }

    public void setPaymentMonth(String paymentDate) {
        this.paymentMonth = paymentDate;
    }

    public Resident getResident() {
        return resident;
    }

    public void setResident(Resident resident) {
        this.resident = resident;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
