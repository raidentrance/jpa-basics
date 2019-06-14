/**
 * 
 */
package com.devs4j.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.devs4j.jpa.entities.User;

/**
 * @author maagapi
 *
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	@Query("SELECT u.username FROM User u ")
	public List<String> findAllUserNames();
}
