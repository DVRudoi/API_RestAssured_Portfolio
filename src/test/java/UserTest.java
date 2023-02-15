import io.restassured.response.Response;
import models.user.CreateUserRQ;
import models.user.CreateUserResponse;
import models.user.DataUserResponse;
import models.user.UpdateUserResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import steps.UserStep;
import utils.UserGenerator;

import java.util.List;

import static utils.EndPoints.*;

public class UserTest {
    private String singleUserId = SINGLEUSERID;

    @Test(description = "TC 100.001")
    public void testCreateUser() {
        CreateUserRQ rq = UserGenerator.createRandomUser();

        CreateUserResponse response = new UserStep()
                .createUser201(rq, singleUserId);

        Assert.assertEquals(response.getName(), rq.getName());
        Assert.assertEquals(response.getJob(), rq.getJob());
        Assert.assertNotNull(response.getId());
    }

    @Test(description = "TC 100.002")
    public void testListUser() {
        List<DataUserResponse> response = new UserStep()
                .getDataSingleUser200();

        Assert.assertTrue(response.stream().allMatch(x -> x.getEmail().endsWith("@reqres.in")));
        response.forEach(x -> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));
        List<String> emails = response.stream().map(DataUserResponse::getEmail).toList();
        List<String> firstNames = response.stream().map(x -> x.getFirstName().toLowerCase()).toList();
        for (int i = 0; i < emails.size(); i++) {
            Assert.assertTrue(emails.get(i).contains(firstNames.get(i)));
        }
    }

    @Test(description = "TC 100.003")
    public void testUpdateUser() {
        CreateUserRQ rq = UserGenerator.createRandomUser();
        CreateUserRQ rq2 = UserGenerator.createRandomUser();

        UpdateUserResponse response =
                new UserStep()
                        .createUser201(rq, singleUserId)
                        .andStep()
                        .updateUser200(rq2, singleUserId);

        Assert.assertEquals(response.getName(), rq2.getName());
        Assert.assertEquals(response.getJob(), rq2.getJob());
    }

    @Test(description = "TC 100.004")
    public void testDeleteUser() {
        CreateUserRQ rq = UserGenerator.createRandomUser();

        Response response = new UserStep()
                .createUser201(rq, singleUserId)
                .andStep()
                .deleteUser();

        Assert.assertEquals(response.getStatusCode(), 204);
    }

}
