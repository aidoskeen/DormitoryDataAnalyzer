import FileReaders.PaymentFileReader;
import FileReaders.ResidentFileReader;
import Hibernate.PaymentHibernateController;
import Hibernate.ResidentHibernateController;
import Models.Payment;
import Models.PaymentType;
import Models.Resident;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
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
    public static void main(String[] args) throws IOException {


        System.out.println("Creating entity Manager Factory");
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("DormitoryDataAnalyzer");
        ResidentHibernateController hibernateController = new ResidentHibernateController(entityManagerFactory);
        PaymentHibernateController paymentHibernateController = new PaymentHibernateController(entityManagerFactory);
        System.out.println("Reading the file");
        List<Resident> residents = ResidentFileReader.getResidentsFromFile(
                "C:\\Users\\aidos\\IdeaProjects\\DormitoryDataAnalyzer\\dormitory.xlsx");
        System.out.println(residents.get(0).toString());
        List<Payment> payments = PaymentFileReader.getPaymentsFromFile(
                "C:\\Users\\aidos\\IdeaProjects\\DormitoryDataAnalyzer\\dormitory.xlsx");
        payments.forEach( payment -> {
                    payment.setResident(residents.get(payments.indexOf(payment)));
                }
        );

        residents.forEach( resident -> {
            if (!residentExists(hibernateController, resident.getResidentId())) {
                hibernateController.create(resident);
            }
        });
        payments.forEach(payment -> {
                    if (!paymentExists(paymentHibernateController, payment.getPaymentId())) {
                        paymentHibernateController.create(payment);
                    }
                }

        );


    }
}