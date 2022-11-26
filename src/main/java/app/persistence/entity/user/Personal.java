package app.persistence.entity.user;

import app.persistence.entity.server.Server;
import app.persistence.listener.FullNameGenerationListener;
import app.persistence.type.Roles;

import javax.persistence.*;
import java.util.Set;

@Entity
@DiscriminatorValue("PERSONAL")
@EntityListeners({
        FullNameGenerationListener.class,
})
public class Personal extends User {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private Integer balance = 0;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "personal")
    private Set<Server> rented;

    @Transient
    private String fullName;

    public Personal() {
        super();
        setRole(Roles.ROLE_PERSONAL);
    }

    public void addRented(Server server) {
        rented.add(server);
        server.setPersonal(this);
    }

    public void removeRented(Server server) {
        rented.remove(server);
        server.setPersonal(null);
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
