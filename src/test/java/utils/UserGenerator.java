package utils;

import models.user.CreateUserRQ;
import org.apache.commons.lang3.RandomStringUtils;

public class UserGenerator {
    public static CreateUserRQ createRandomUser() {
        return CreateUserRQ.builder()
                .job(RandomStringUtils.random(8, "abcdefghijklmnopqrstuvwxyz" +
                        "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"))
                .name(RandomStringUtils.random(8, "abcdefghijklmnopqrstuvwxyz" +
                        "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"))
                .build();
    }
}
