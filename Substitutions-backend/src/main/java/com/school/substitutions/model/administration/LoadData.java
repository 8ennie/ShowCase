package com.school.substitutions.model.administration;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.school.substitutions.repository.RoleRepository;
import com.school.substitutions.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoadData implements ApplicationRunner {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@SuppressWarnings("unused")
	@Override
	public void run(ApplicationArguments args) throws Exception {

		// Roles
		Role adminRole = roleRepository.save(new Role(ERole.ROLE_ADMIN));
		Role userRole = roleRepository.save(new Role(ERole.ROLE_USER));
		Role studnentRole = roleRepository.save(new Role(ERole.ROLE_STUDENT));
		Role teacherRole = roleRepository.save(new Role(ERole.ROLE_TEACHER));

		//User
		User admin = new User("admin", "admin@admin.com", encoder.encode("adminadmin"));
		Set<Role> roles = new HashSet<>();
		roles.add(adminRole);
		admin.setRoles(roles);

		log.info("Preload: " + userRepository.save(admin));
	}

}
