package WebControllers;

import Hibernate.ResidentHibernateController;
import Models.Resident;
import Serializers.ResidentJsonSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ResidentWebController {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("DormitoryDataAnalyzer");
    ResidentHibernateController residentController = new ResidentHibernateController(entityManagerFactory);

    @RequestMapping(value = "/resident/withdebts", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getResidentsWithDebts() {
        List<Resident> residents = residentController.getAllResidents(true, -1, -1);

        residents = residents.stream().filter(resident -> resident.getDebtAmount() > 0).collect(Collectors.toList());

        GsonBuilder gson = new GsonBuilder();
        Type residentType = new TypeToken<List<Resident>>() {
        }.getType();
        gson.registerTypeAdapter(Resident.class, new ResidentJsonSerializer())
                .registerTypeAdapter(residentType, new ResidentJsonSerializer());
        Gson parser = gson.create();
        return parser.toJson(residents);
    }

    @RequestMapping(value = "/resident/debtorsnum", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getDebtorsAmount() {
        List<Resident> residents = residentController.getAllResidents(true, -1, -1);

        residents = residents.stream().filter(resident -> resident.getDebtAmount() > 0).collect(Collectors.toList());

        GsonBuilder gson = new GsonBuilder();
        JsonObject json = new JsonObject();
        json.addProperty("debtorsAmount", residents.size());

        return gson.create().toJson(json);
    }

}
