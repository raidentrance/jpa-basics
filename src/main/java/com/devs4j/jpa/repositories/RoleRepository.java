/**
 * 
 */
package com.devs4j.jpa.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.devs4j.jpa.entities.Role;

/**
 * @author maagapi
 *
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

}
