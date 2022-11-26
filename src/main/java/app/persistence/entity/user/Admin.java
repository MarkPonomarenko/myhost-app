package app.persistence.entity.user;

import app.persistence.type.Roles;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User {

    public Admin() {
        super();
        setRole(Roles.ROLE_ADMIN);
    }
}