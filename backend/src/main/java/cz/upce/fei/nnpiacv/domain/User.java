package cz.upce.fei.nnpiacv.domain;


import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String password;

    private String email;

    public User(String password, String mail) {
        this.email = mail;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
}
