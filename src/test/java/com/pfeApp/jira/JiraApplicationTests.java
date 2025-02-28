package com.pfeApp.jira;

import com.pfeApp.jira.entities.Role;
import com.pfeApp.jira.entities.User;
import com.pfeApp.jira.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class JiraApplicationTests {
@Autowired
UserRepo ur;
@Autowired
	 PasswordEncoder passwordEncoder ;
	@Test
	void contextLoads() {

		User user = new User();
		user.setEmail("Admin@gmail.com");
		user.setFirstname("admin");
		user.setLastname("Admin");
		user.setRole(Role.ADMIN);
		user.setPassword(passwordEncoder.encode("Admin123"));

		 ur.save(user);//Méthode pour inscrire un nouvel utilisateur. Le mot de passe est encodé avant d'être sauvegardé.
	}

}
