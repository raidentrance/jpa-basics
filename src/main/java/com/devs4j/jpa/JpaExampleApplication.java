package com.devs4j.jpa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devs4j.jpa.entities.Address;
import com.devs4j.jpa.entities.Profile;
import com.devs4j.jpa.entities.Role;
import com.devs4j.jpa.entities.User;
import com.devs4j.jpa.entities.UserInRole;
import com.devs4j.jpa.repositories.AddressRepository;
import com.devs4j.jpa.repositories.ProfileRepository;
import com.devs4j.jpa.repositories.RoleRepository;
import com.devs4j.jpa.repositories.UserInRoleRepository;
import com.devs4j.jpa.repositories.UserRepository;
import com.github.javafaker.Faker;

@SpringBootApplication
public class JpaExampleApplication implements ApplicationRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserInRoleRepository userInRoleRepository;

	private static final Logger log = LoggerFactory.getLogger(JpaExampleApplication.class);

	public void initData() {
		log.info("Loading data application");
		Faker faker = new Faker();
		Role[] roles = new Role[] { new Role("ADMIN"), new Role("USER") };
		roleRepository.saveAll(Arrays.asList(roles));
		for (int i = 0; i < 100; i++) {
			List<Address> addresses = new ArrayList<>();
			Profile profile = new Profile(faker.name().name(), faker.name().lastName(), faker.date().birthday());
			for (int j = 0; j < new Random().nextInt(3); j++) {
				addresses.add(new Address(faker.address().streetAddress(), faker.address().streetAddressNumber(),
						faker.address().city(), profile));
			}
			profile = profileRepository.save(profile);
			addressRepository.saveAll(addresses);

			User user = userRepository.save(new User(faker.name().username(), faker.name().firstName(), profile));
			userInRoleRepository.save(new UserInRole(user, roles[new Random().nextInt(2)]));

		}
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		initData();
		log.info("Finding all the users");

		Iterable<User> users = userRepository.findAll();

		Iterator<User> iterator = users.iterator();

		while (iterator.hasNext()) {
			User user = iterator.next();

			log.info("User : {} ", user);
			List<Address> addresses = addressRepository.findByProfile(user.getProfile());
			log.info("Addresses : {} ", addresses);
		}

		Iterator<UserInRole> allUserInRole = userInRoleRepository.findAll().iterator();

		while (allUserInRole.hasNext()) {
			log.info("User in role : {} ", allUserInRole.next());
		}
		
		
		List<String> allUserNames = userRepository.findAllUserNames();
		
		for (String username : allUserNames) {
			log.info("Username {} ",username);
		}

	}

	public static void main(String[] args) {
		SpringApplication.run(JpaExampleApplication.class, args);
	}

}
