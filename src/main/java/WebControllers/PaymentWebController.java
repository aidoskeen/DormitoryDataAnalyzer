package WebControllers;

import Hibernate.PaymentHibernateController;
import Hibernate.ResidentHibernateController;
import Models.Payment;
import Models.Resident;
import Serializers.PaymentJsonSerializer;
import Serializers.ResidentJsonSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.reflect.Type;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;
@Controller
public class PaymentWebController {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("DormitoryDataAnalyzer");
    PaymentHibernateController paymentController = new PaymentHibernateController(entityManagerFactory);

    @RequestMapping(value = "/payment/byMethod/{method}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getPaymentByMethod(@PathVariable(name = "method") String method) {
        List<Payment> payments = paymentController.getAllPayments(true, -1, -1);

        payments = payments.stream().filter(payment -> payment.getPaymentMethod().equals(method)).collect(Collectors.toList());

        GsonBuilder gson = new GsonBuilder();
        Type paymentType = new TypeToken<List<Payment>>() {
        }.getType();
        gson.registerTypeAdapter(Payment.class, new PaymentJsonSerializer())
                .registerTypeAdapter(paymentType, new PaymentJsonSerializer());
        Gson parser = gson.create();
        return parser.toJson(payments);
    }

    @RequestMapping(value = "/payment/byType/{type}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getPaymentByType(@PathVariable(name = "type") String type) {
        List<Payment> payments = paymentController.getAllPayments(true, -1, -1);

        payments = payments.stream().filter(payment -> payment.getPaymentType().equals(type)).collect(Collectors.toList());

        GsonBuilder gson = new GsonBuilder();
        Type paymentType = new TypeToken<List<Payment>>() {
        }.getType();
        gson.registerTypeAdapter(Payment.class, new PaymentJsonSerializer())
                .registerTypeAdapter(paymentType, new PaymentJsonSerializer());
        Gson parser = gson.create();
        return parser.toJson(payments);
    }

    @RequestMapping(value = "/payment/income", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getOverallIncome() {
        List<Payment> payments = paymentController.getAllPayments(true, -1, -1);

        double income = 0.0;
        for (Payment payment: payments) {
            income = income + payment.getPaymentAmount();
        }

        GsonBuilder gson = new GsonBuilder();

        Gson parser = gson.create();
        return parser.toJson(Double.toString(income));
    }

    @RequestMapping(value = "/payment/incomediffByMonth/{month1}/{month2}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getMonthIncomeDifference(@PathVariable(name = "month1") String month1, @PathVariable(name = "month2") String month2) {
        List<Payment> payments = paymentController.getAllPayments(true, -1, -1);
        List<Payment> otherPayments = payments;
        payments = payments.stream().filter(payment -> payment.getPaymentMonth().equals(month1)).collect(Collectors.toList());
        otherPayments = otherPayments.stream().filter(payment -> payment.getPaymentMonth().equals(month2)).collect(Collectors.toList());


        double firstIncome = 0.0;
        for (Payment payment: payments) {
            firstIncome = firstIncome + payment.getPaymentAmount();
        }
        double secondIncome = 0.0;
        for (Payment payment: otherPayments) {
            secondIncome = secondIncome + payment.getPaymentAmount();
        }

        GsonBuilder gson = new GsonBuilder();

        Gson parser = gson.create();
        return parser.toJson(Double.toString(firstIncome - secondIncome));
    }
    @RequestMapping(value = "/payment/incomediffByDormotry/{dorm1}/{dorm2}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getDormIncomeDifference(@PathVariable(name = "dorm1") String dorm1, @PathVariable(name = "dorm2") String dorm2) {
        List<Payment> payments = paymentController.getAllPayments(true, -1, -1);
        List<Payment> otherPayments = payments;
        payments = payments.stream().filter(
                payment -> payment.getResident().getDormitory().equals(dorm1)).collect(Collectors.toList());
        otherPayments = otherPayments.stream().filter(
                payment -> payment.getResident().getDormitory().equals(dorm2)).collect(Collectors.toList());


        double firstIncome = 0.0;
        for (Payment payment: payments) {
            firstIncome = firstIncome + payment.getPaymentAmount();
        }
        double secondIncome = 0.0;
        for (Payment payment: otherPayments) {
            secondIncome = secondIncome + payment.getPaymentAmount();
        }

        GsonBuilder gson = new GsonBuilder();

        Gson parser = gson.create();
        return parser.toJson(Double.toString(firstIncome - secondIncome));
    }
}

