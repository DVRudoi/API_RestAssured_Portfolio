package steps;

import io.qameta.allure.Step;
import models.user.CreateUserRQ;
import models.user.CreateUserResponse;
import models.user.DataUserResponse;
import models.user.UpdateUserResponse;
import utils.EndPoints;

import java.util.List;

import static javax.net.ssl.HttpsURLConnection.*;

public class UserStep extends BaseStep {
    @Step("Create user")
    public CreateUserResponse createUser201(CreateUserRQ rq, String path) {
        return createsPostResponse(rq, HTTP_CREATED, EndPoints.USER + path).as(CreateUserResponse.class);
    }

    public List<DataUserResponse> getDataSingleUser200() {
        return getResponse(HTTP_OK, EndPoints.USER).body().jsonPath()
                .getList("data", DataUserResponse.class);
    }
    @Step("Update user")
    public UpdateUserResponse updateUser200(CreateUserRQ rq, String path) {
        return updateUserPutResponse(rq, HTTP_OK, EndPoints.USER + path).as(UpdateUserResponse.class);
    }
}
