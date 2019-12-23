package br.com.ortiz.security.repositories;

import br.com.ortiz.security.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    User findByGoogleUserId(String googleUserId);
    User findByEmail(String email);
}
