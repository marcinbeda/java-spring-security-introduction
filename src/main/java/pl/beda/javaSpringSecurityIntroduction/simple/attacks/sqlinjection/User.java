package pl.beda.javaSpringSecurityIntroduction.simple.attacks.sqlinjection;

import lombok.Data;

@Data
public class User {
    private long id;
    private String username;

    public User(String username) {
        this.username = username;
    }
}
