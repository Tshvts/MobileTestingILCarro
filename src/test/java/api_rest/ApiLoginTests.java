package api_rest;

import config.AuthenticationController;
import dto.ErrorMessageDto;
import dto.UserDTO;
import helper.BaseApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static helper.RandomHelper.*;

public class ApiLoginTests extends AuthenticationController implements BaseApi
{
    private String email = "test132@gmail.com";
    private String password = "Ngfg*135";

    @Test
    public void loginPositive()
    {
        UserDTO user = UserDTO.builder()
                .username(email)
                .password(password)
                .build();

        Response response = requestRegLogin(user,LOGIN);
        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.statusCode(),200);
    }

    @Test
    public void loginNegative_WrongEmail()
    {
        UserDTO user = UserDTO.builder()
                .username(generateString(7))
                .password(password)
                .build();

        Response response = requestRegLogin(user,LOGIN);
        System.out.println(response.getStatusCode());
        ErrorMessageDto errorMessageDto = response.getBody().as(ErrorMessageDto.class);
        System.out.println(errorMessageDto);
        Assert.assertTrue(errorMessageDto.getMessage().toString().equals("Login or Password incorrect"));
    }
}
