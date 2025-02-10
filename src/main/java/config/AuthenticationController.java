package config;

import dto.UserDTO;
import helper.BaseApi;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthenticationController implements BaseApi
{
    public Response requestRegLogin(UserDTO user, String url)
    {
       return given()
                .body(user)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL + url)
                .thenReturn();
    }

    public Response requestRegLogin_500(UserDTO user, String url)
    {
       return given()
                .body(user)
                .when()
                .post(BASE_URL + url)
                .thenReturn();
    }
}
