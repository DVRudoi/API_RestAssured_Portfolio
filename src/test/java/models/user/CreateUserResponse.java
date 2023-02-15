package models.user;

import io.qameta.allure.Step;
import lombok.Data;
import steps.UserStep;

@Data
public class CreateUserResponse extends CreateUserRQ {
    private String id;
    private String createdAt;
    @Step("And")
    public UserStep andStep() {
        return new UserStep();
    }
}
