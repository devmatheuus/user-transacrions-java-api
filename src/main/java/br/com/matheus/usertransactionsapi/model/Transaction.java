package br.com.matheus.usertransactionsapi.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, referencedColumnName = "id")
    private User payer;

    @ManyToOne
    @JoinColumn(nullable = false, referencedColumnName = "id")
    private User payee;

    @Column(nullable = false, columnDefinition = "FLOAT")
    private Float value;

    @CreationTimestamp
    @Column(updatable = false)
    private Date date;

    public Transaction() {
    }

    public Transaction(User payer, User payee, Float value) {
        this.payer = payer;
        this.payee = payee;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", payer=" + payer +
                ", payee=" + payee +
                ", value=" + value +
                ", date=" + date +
                '}';
    }
}