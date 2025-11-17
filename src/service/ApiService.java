package service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import exception.ApiException;
import model.Employee;
import model.Position;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ApiService {

    public List<Employee> fetchEmployeesFromApi(String url) throws ApiException {

        HttpClient httpClient = HttpClient.newHttpClient();

        List<Employee> employees = new ArrayList<>();

        try{
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {

                Gson gson = new Gson();

                JsonArray array = gson.fromJson(response.body(), JsonArray.class);

                for (JsonElement element : array) {
                    JsonObject object = element.getAsJsonObject();
                    String [] name = object.get("name").getAsString().split(" ");
                    String firstName = name[0];
                    String surname = name[1];
                    String email = object.get("email").getAsString();
                    JsonObject companyObject = object.getAsJsonObject("company");
                    String companyName = companyObject.get("name").getAsString();
                    Employee employee = new Employee(firstName, surname, email, companyName, Position.PROGRAMISTA, Position.PROGRAMISTA.baseSalary());

                    employees.add(employee);
                }
            }
            else {
                System.out.println("Błąd HTTP: " + response.statusCode());
            }

        }
        catch (Exception e){
            throw new ApiException(e.getMessage());
        }
        return employees;
    }
}