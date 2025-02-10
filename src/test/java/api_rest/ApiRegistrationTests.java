package api_rest;

import config.AuthenticationController;
import dto.ErrorMessageDto;
import dto.UserDTO;
import helper.BaseApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static helper.RandomHelper.*;

public class ApiRegistrationTests extends AuthenticationController implements BaseApi
{
    SoftAssert softAssert = new SoftAssert();

        @Test
    public void registrationPositive()
        {
            UserDTO user = UserDTO.builder()
                    .firstName(generateString(7))
                    .lastName(generateString(10))
                    .username(generateEmail(6))
                    .password("Password123!")
                    .build();

            Response response = requestRegLogin(user,REGISTRATION);
            System.out.println(response.getStatusCode());
            Assert.assertEquals(response.getStatusCode(),200);
        }

        @Test
    public void registrationNegative_EmptyName_400()
        {
            UserDTO user = UserDTO.builder()
                    .firstName("")
                    .lastName(generateString(10))
                    .username(generateEmail(6))
                    .password("Password123!")
                    .build();

            Response response = requestRegLogin(user,REGISTRATION);
            System.out.println(response.getStatusCode());
            ErrorMessageDto errorMessageDto = response.body().as(ErrorMessageDto.class);
            System.out.println(errorMessageDto.toString());
            softAssert.assertEquals(response.getStatusCode(),400);
            softAssert.assertTrue(errorMessageDto.toString().contains("must not be blank"));
            softAssert.assertAll();
        }

        @Test
    public void registrationNegative_EmptyLastName_400()
        {
            UserDTO user = UserDTO.builder()
                    .firstName(generateString(5))
                    .lastName("")
                    .username(generateEmail(6))
                    .password("Password123!")
                    .build();

            Response response = requestRegLogin(user,REGISTRATION);
            System.out.println(response.getStatusCode());
            ErrorMessageDto errorMessageDto = response.body().as(ErrorMessageDto.class);
            System.out.println(errorMessageDto.toString());
            softAssert.assertEquals(response.getStatusCode(),400);
            softAssert.assertTrue(errorMessageDto.toString().contains("must not be blank"));
            softAssert.assertAll();
        }

        @Test
    public void registrationNegative_WrongEmail_400()
        {
            UserDTO user = UserDTO.builder()
                    .firstName(generateString(5))
                    .lastName(generateString(7))
                    .username(generateNumbers(8))
                    .password("Password123!")
                    .build();

            Response response = requestRegLogin(user,REGISTRATION);
            System.out.println(response.getStatusCode());
            ErrorMessageDto errorMessageDto = response.body().as(ErrorMessageDto.class);
            System.out.println(errorMessageDto.toString());
            softAssert.assertEquals(response.getStatusCode(),400);
            softAssert.assertTrue(errorMessageDto.toString().contains("must be a well-formed email address"));
            softAssert.assertAll();
        }

        @Test
    public void registrationNegative_WrongPassword_400()
        {
            UserDTO user = UserDTO.builder()
                    .firstName(generateString(5))
                    .lastName(generateString(7))
                    .username(generateEmail(5))
                    .password(generateNumbers(7))
                    .build();

            Response response = requestRegLogin(user,REGISTRATION);
            System.out.println(response.getStatusCode());
            ErrorMessageDto errorMessageDto = response.body().as(ErrorMessageDto.class);
            System.out.println(errorMessageDto.toString());
            softAssert.assertEquals(response.getStatusCode(),400);
            softAssert.assertTrue(errorMessageDto.toString().contains("At least 8 characters"));
            softAssert.assertAll();
        }

        @Test
    public void registrationNegative_500()
        {
            UserDTO user = UserDTO.builder()
                    .firstName(generateString(6))
                    .lastName(generateString(7))
                    .username(generateEmail(5))
                    .password("Password123!")
                    .build();

            Response response = requestRegLogin_500(user,REGISTRATION);
            System.out.println(response.getStatusCode());
            ErrorMessageDto errorMessageDto = response.body().as(ErrorMessageDto.class);
            System.out.println(errorMessageDto.toString());
            softAssert.assertEquals(response.getStatusCode(),500);
            softAssert.assertTrue(errorMessageDto.toString().contains("text/plain;charset=ISO-8859-1' not supported"));
            softAssert.assertAll();
        }
}
