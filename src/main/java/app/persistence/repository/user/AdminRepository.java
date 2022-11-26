package app.persistence.repository.user;

import org.springframework.stereotype.Repository;
import app.persistence.entity.user.Admin;

@Repository
public interface AdminRepository extends UserRepository<Admin> { }
