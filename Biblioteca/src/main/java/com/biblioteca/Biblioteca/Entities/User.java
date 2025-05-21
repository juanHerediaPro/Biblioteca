package com.biblioteca.Biblioteca.Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(name = "last_name",nullable = false)
    private String lastName;
    @Column(name = "id_card",nullable = false, unique = true)
    private long idCard;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(name = "phone_number",nullable = false)
    private Long phoneNumber;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Loan> loans;

    public User() {
    }

    public User(long id, String name, String lastName, long idCard, String email, Long phoneNumber, List<Loan> loans) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.idCard = idCard;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.loans = loans;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getIdCard() {
        return idCard;
    }

    public void setIdCard(long idCard) {
        this.idCard = idCard;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }
}
