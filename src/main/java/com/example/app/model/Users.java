
package com.example.app.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
// TODO: Call it user
@Table(name="Users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name = "firstName")
    private String firstName;

    @NotNull
    @Column(name = "lastName")
    private String lastName;

    @NotNull
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "userName")
    private String userName;

    @NotNull
    @Column(name = "userPassword")
    private String user_Password;

    @Enumerated(EnumType.STRING)
    @Column(name="roles")
    private Role roles;
    @OneToMany(mappedBy = "users", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Invoice>invoices= new ArrayList<>();

    public Users() {
    }

    public Users(int id, String firstName, String lastName, String email, String userName, String user_Password, Role roles, List<Invoice> invoices) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.user_Password = user_Password;
        this.roles = roles;
        this.invoices = invoices;
    }

    public int getID() {
        return id;
    }

    public void setID(int ID) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUser_Password() {
        return user_Password;
    }

    public void setUser_Password(String user_Password) {
        this.user_Password = user_Password;
    }

    public Role getRole() {
        return roles;
    }

    public void setRole(Role role) {
        this.roles = roles;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", user_Password='" + user_Password + '\'' +
                ", roles=" + roles +
                ", invoices=" + invoices +
                '}';
    }
}
