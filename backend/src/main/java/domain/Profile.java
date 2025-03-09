package domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Profile {

    @Id
    private Long id;

    private String birthDate;
    private String description;

    @OneToOne
    private User user;

    public Profile(Long id, String birthDate, String description) {
        this.id = id;
        this.birthDate = birthDate;
        this.description = description;
    }

    public Profile() {

    }

    public void setUser(User user) {
        this.user = user;
    }
}
