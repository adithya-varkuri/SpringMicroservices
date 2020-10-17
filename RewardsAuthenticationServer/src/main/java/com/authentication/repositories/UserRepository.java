package com.authentication.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.authentication.entities.User;

/*Spring @Repository annotation is used to indicate that the class provides the mechanism for storage, retrieval, search, update and delete operation on objects.*/
/*Spring Repository classes are autodetected by spring framework through classpath scanning.*/
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}