package app.persistence.repository.user;

import org.springframework.stereotype.Repository;
import app.persistence.entity.user.User;
import app.persistence.repository.BaseRepository;

@Repository
public interface UserRepository<U extends User> extends BaseRepository<U> {

    U findByEmail(String email);

    boolean existsByEmail(String email);
}
