package models.user;

import lombok.Data;

@Data
public class UpdateUserResponse extends CreateUserRQ {
    private String updatedAt;
}
