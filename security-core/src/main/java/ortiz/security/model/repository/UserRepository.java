package ortiz.security.model.repository;

import org.springframework.data.repository.CrudRepository;
import ortiz.security.model.User;

public interface UserRepository extends CrudRepository<User, String> {
    User findByGoogleUserId(String googleUserId);
}
