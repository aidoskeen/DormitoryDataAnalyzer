package Serializers;

import Models.Resident;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class ResidentJsonSerializer implements JsonSerializer<Resident> {

    @Override
    public JsonElement serialize(Resident resident, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject residentJson = new JsonObject();
        residentJson.addProperty("resident_id", resident.getResidentId());
        residentJson.addProperty("name", resident.getName());
        residentJson.addProperty("surname", resident.getSurname());
        residentJson.addProperty("debt", resident.getDebtAmount());

        return residentJson;
    }
}

