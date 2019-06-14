/**
 * 
 */
package com.devs4j.jpa.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.devs4j.jpa.entities.Address;
import com.devs4j.jpa.entities.Profile;

/**
 * @author maagapi
 *
 */
@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {
	
	List<Address> findByProfile(Profile profile);

}
