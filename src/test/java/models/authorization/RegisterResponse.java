package models.authorization;

import lombok.Data;

@Data
public class RegisterResponse {
    private Integer id;
    private String token;
}
