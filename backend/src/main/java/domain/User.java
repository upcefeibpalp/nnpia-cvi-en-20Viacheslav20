package domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "password")
public class User {
    private Long id;
    private String password;
    private String email;

    public User(Long id, String password, String email) {
        this.id = id;
        this.password = password;
        this.email = email;
    }
}
