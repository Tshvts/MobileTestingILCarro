package api_rest;

import config.CarController;
import dto.CarDto;
import helper.BaseApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static helper.RandomHelper.generateNumbers;
import static helper.RandomHelper.generateString;

public class ApiAddNewCarTests extends CarController implements BaseApi
{
    @Test
    public void addNewCarPositive()
    {
        CarDto car = CarDto.builder()
                .serialNumber(generateNumbers(6))
                .manufacture(generateString(6))
                .model(generateNumbers(3))
                .city("Haifa")
                .pricePerDay(233.22)
                .carClass("A")
                .fuel("Gas")
                .year("1975")
                .seats(4)
                .about(generateString(10))
                .build();
        login();
        Response response = addUserCar(car,tokenDto.getAccessToken());
        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
