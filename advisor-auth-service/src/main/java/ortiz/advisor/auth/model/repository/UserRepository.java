package ortiz.advisor.auth.model.repository;

import org.springframework.data.repository.CrudRepository;
import ortiz.advisor.auth.model.User;

public interface UserRepository extends CrudRepository<User, String> {

    User findByGoogleUserId(String googleUserId);

}
