import Hibernate.PaymentHibernateController;
import Hibernate.ResidentHibernateController;
import Models.Payment;
import Models.PaymentType;
import Models.Resident;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class Main {
    private static Boolean residentExists(ResidentHibernateController hibernateController, int id) {
        Resident resident = hibernateController.getResidentById(id);
        return resident != null;
    }

    private static Boolean paymentExists(PaymentHibernateController hibernateController, int id) {
        Payment payment = hibernateController.getPaymentById(id);
        return payment != null;
    }
    public static void main(String[] args) {

        System.out.println("Creating entity Manager Factory");
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("DormitoryDataAnalyzer");
        ResidentHibernateController hibernateController = new ResidentHibernateController(entityManagerFactory);
        PaymentHibernateController paymentHibernateController = new PaymentHibernateController(entityManagerFactory);

        List<Resident> residents = List.of(
                new Resident(
                        "Aidos",
                        "Alimkhan",
                        "Kaz", "Vgtu",
                        "803A",
                        100L),
                new Resident(
                        "John",
                        "Smith",
                        "USA",
                        "Vgtu",
                        "803B",
                        200L),
                new Resident(
                        "Jurgis",
                        "Kazlauskas",
                        "Lithuania",
                        "Vgtu2",
                        "803B",
                        300L)

        );
        List<Payment> payments = List.of(
                new Payment(
                        residents.get(0),
                        "rent",
                        Month.MARCH.toString(),
                        "Card",
                200L),
                new Payment(
                        residents.get(1),
                        "Deposit",
                        Month.APRIL.toString(),
                        "Post office",
                        150L),
                new Payment(
                        residents.get(2),
                        "Deposit",
                        Month.APRIL.toString(),
                        "Post office",
                        150L)

        );

        residents.forEach( resident -> {
            //if (!residentExists(hibernateController, resident.getResidentId())) {
                hibernateController.create(resident);
           // }
        });
        payments.forEach(payment -> {
                    //if (!paymentExists(paymentHibernateController, payment.getPaymentId())) {
                        paymentHibernateController.create(payment);
                    //}
                }

        );


    }
}