package br.com.matheus.usertransactionsapi.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 92, nullable = false)
    private String name;

    @Column(length = 11, nullable = false, unique = true)
    private String cpf;

    @Column(length = 62, nullable = false, unique = true)
    private String email;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String password;

    @Column(length = 6, nullable = false)
    private String type;

    @Column(columnDefinition = "FLOAT DEFAULT 0.0")
    private Float balance = 0.0f;


    public User() {
    }

    public User(String name, String cpf, String email, String password, String type) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name=" + name +
                ", cpf=" + cpf +
                ", email=" + email +
                ", password=" + password +
                ", type=" + type +
                ", balance=" + balance +
                '}';
    }
}