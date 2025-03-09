package domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "password")
@Entity
@Table(name = "users")
public class User {

    @Id
    private Long id;

    private String password;
    private String email;

    @OneToOne(mappedBy = "user")
    private Profile profile;

    public User(Long id, String password, String email) {
        this.id = id;
        this.password = password;
        this.email = email;
    }

    public User() {

    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
