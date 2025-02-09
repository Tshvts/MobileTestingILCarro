package api_rest;

import config.CarController;
import helper.BaseApi;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class ApiGetUserCars extends CarController implements BaseApi
{
    @Test
    public void getUsersCarsPositive()
    {
        login();
        Response response = getUserCars(tokenDto.getAccessToken());
        System.out.println("==> " + response.getStatusCode());
    }
}
