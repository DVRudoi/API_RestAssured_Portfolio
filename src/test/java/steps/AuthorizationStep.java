package steps;

import models.authorization.RegisterFailedResponse;
import models.authorization.RegisterRQ;
import models.authorization.RegisterResponse;
import utils.EndPoints;

import static javax.net.ssl.HttpsURLConnection.*;

public class AuthorizationStep extends BaseStep {
    public RegisterResponse registerSuccess200(RegisterRQ user) {
        return createsPostResponse(user, HTTP_OK, EndPoints.REGISTER).as(RegisterResponse.class);
    }

    public RegisterFailedResponse registerUnSuccess400(RegisterRQ user) {
        return createsPostResponse(user, HTTP_BAD_REQUEST, EndPoints.REGISTER).as(RegisterFailedResponse.class);
    }
}
