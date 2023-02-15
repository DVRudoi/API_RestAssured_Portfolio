import models.authorization.RegisterFailedResponse;
import models.authorization.RegisterRQ;
import models.authorization.RegisterResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import steps.AuthorizationStep;

import static utils.Configuration.*;

public class AuthorizationTest {
    private final String EMAIL = getConfigurationValue("email");

    @Test(description = "TC 101.001")
    public void registerTest() {
        RegisterRQ user = new RegisterRQ(EMAIL, getConfigurationValue("password"));
        RegisterResponse response = new AuthorizationStep()
                .registerSuccess200(user);

        Assert.assertNotNull(response.getId());
        Assert.assertNotNull(response.getToken());
    }

    @Test(description = "TC 101.002")
    public void registerFailedTest() {
        RegisterRQ user = new RegisterRQ(EMAIL, "");
        RegisterFailedResponse response = new AuthorizationStep()
                .registerUnSuccess400(user);

        Assert.assertEquals(response.getError(), "Missing password", "Expected result isn't correct");
    }
}
