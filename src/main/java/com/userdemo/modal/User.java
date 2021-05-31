package com.userdemo.modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Objects;
@Entity
@Table(name = "users")
public class User {
    private @Id @GeneratedValue long id;
    @Column(unique = true)
    private @NotBlank String username;
    private @NotBlank String password;
    private @NotBlank String api_token;
    public User() {
    }
    public User(@NotBlank String username, 
                @NotBlank String password) {
        this.username = username;
        this.password = password;
//        this.api_token = false;
    }
    public long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String isApi_token() {
        return api_token;
    }
    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(password, user.password);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, 
        		api_token);
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", loggedIn=" + api_token +
                '}';
    }
}
