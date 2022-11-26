package app.persistence.repository.user;

import org.springframework.stereotype.Repository;
import app.persistence.entity.user.Personal;

@Repository
public interface PersonalRepository extends UserRepository<Personal> { }