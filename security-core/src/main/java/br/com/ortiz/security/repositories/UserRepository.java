package br.com.ortiz.security.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.ortiz.security.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    User findByGoogleUserId(String googleUserId);
}
