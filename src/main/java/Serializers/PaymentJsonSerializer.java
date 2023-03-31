package Serializers;

import Models.Payment;
import Models.Resident;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class PaymentJsonSerializer implements JsonSerializer<Payment> {
    @Override
    public JsonElement serialize(Payment payment, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject paymentJson = new JsonObject();
        paymentJson.addProperty("payment_id", payment.getPaymentId());
        paymentJson.addProperty("type", payment.getPaymentType());
        paymentJson.addProperty("method", payment.getPaymentMethod());
        paymentJson.addProperty("month", payment.getPaymentMonth().toString());
        paymentJson.addProperty("amount", payment.getPaymentAmount());
        paymentJson.addProperty("resident", payment.getResident().getName());

        return paymentJson;
    }
}
