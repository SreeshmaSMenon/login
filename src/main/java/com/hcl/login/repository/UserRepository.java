package com.hcl.login.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hcl.login.entity.User;
/**
 * Repository for User entity operations.
 * @author KiruthikaK
 * @since 2019/10/18
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);
	Optional<User> findByEmailAndPassword(String email, String password);

}
