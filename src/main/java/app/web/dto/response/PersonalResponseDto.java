package app.web.dto.response;

import app.persistence.entity.server.Server;
import app.persistence.entity.user.Personal;

import java.util.Collections;
import java.util.Set;

public class PersonalResponseDto extends ResponseDto{

    private String firstName;
    private String lastName;
    private String email;
    private Integer balance;
    private Set<Server> rented;

    public PersonalResponseDto() {
    }

    public PersonalResponseDto(Personal personal) {
        setId(personal.getId());
        setCreated(personal.getCreated());
        setUpdated(personal.getUpdated());
        this.email = personal.getEmail();
        this.firstName = personal.getFirstName();
        this.lastName = personal.getLastName();
        this.balance = personal.getBalance();
        if (personal.getRented() != null) {
            this.rented = personal.getRented();
        } else {
            this.rented = Collections.emptySet();
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Set<Server> getRented() {
        return rented;
    }

    public void setRented(Set<Server> rented) {
        this.rented = rented;
    }
}
