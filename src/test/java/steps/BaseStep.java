package steps;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.user.CreateUserRQ;
import utils.EndPoints;

import static io.restassured.RestAssured.given;
import static utils.Configuration.*;

public abstract class BaseStep {
    protected RequestSpecification REQ_SPEC;

    public BaseStep() {
        REQ_SPEC = new RequestSpecBuilder()
                .setBaseUri(getConfigurationValue("baseUrl"))
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }

    public <T> Response createsPostResponse(T rq, int code, String endPoint) {
        return given()
                .spec(REQ_SPEC)
                .basePath(endPoint)
                .body(rq)
                .post()
                .then()
                .statusCode(code)
                .extract().response();
    }

    public Response getResponse(int code, String endPoint) {
        return given()
                .spec(REQ_SPEC)
                .basePath(endPoint)
                .get()
                .then()
                .statusCode(code)
                .extract().response();
    }

    public Response updateUserPutResponse(CreateUserRQ rq, int code, String endPoint) {
        return given()
                .spec(REQ_SPEC)
                .basePath(endPoint)
                .body(rq)
                .put()
                .then()
                .statusCode(code)
                .extract().response();
    }

    @Step("Delete user")
    public Response deleteUser() {
        return given()
                .spec(REQ_SPEC)
                .basePath(EndPoints.USER)
                .delete();
    }
}
