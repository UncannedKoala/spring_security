package com.spring_security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.spring_security.entities.User;

/**
 * Since this is an interface that extends the JpaRepository interface, there is no need to use {@code @Repo} annotation
 */
public interface UserRepository extends JpaRepository<User, Integer>{

	@Query("""
			SELECT u FROM User u WHERE u.user_name = :userName
			""")
	Optional<User> findUserByUserName(String userName);
}
