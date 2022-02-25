package utilities;

import io.restassured.response.Response;
import pojos.Employee;

public class ApiTest2 {
    public static void main(String[] args) {

        // Uncomment all

//        Response response1 = ApiUtils.apiGet("/api/employees/100");
//        System.out.println(response1.body().asString());
//
//        response1.then().log().all();
//
//       System.out.println(response1.andReturn().statusCode());
//
//        Employee responseBody= new Employee();
//        responseBody= response1.body().as(Employee.class); // Deserialization -> converting JSON to POJO
//
//        System.out.println(responseBody.getLastName());
//        System.out.println(responseBody.getJob().getSalary());
//        System.out.println(responseBody.getDepartment().getLocation().getLocationCity());
//        System.out.println(responseBody.getDepartment().getDepartmentName());
//





    }
}


