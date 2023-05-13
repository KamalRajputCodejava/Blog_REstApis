package com.blog;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.blog.entities.Role;
import com.blog.payloads.AppConstents;
import com.blog.repositories.RoleRepo;
import com.blog.security.CustomUserDetailService;

@SpringBootApplication
public class BlogREstApisApplication implements CommandLineRunner {
	// @Autowired
	// private PasswordEncoder passwordEncoder ;
	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BlogREstApisApplication.class, args);
	}

//bean 
	@Bean
	public ModelMapper modelMapped() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		// System.out.println(passwordEncoder.encode("abc"));

		try {
			Role role = new Role();
			role.setId(AppConstents.ADMIN_USER);
			role.setRole("ROLE_ADMIN");

			Role role1 = new Role();
			role1.setId(AppConstents.NORMAL_USER);
			role1.setRole("ROLE_NORMAL");

			List<Role> roles = List.of(role, role1);
			List<Role> result = this.roleRepo.saveAll(roles);

			result.forEach(r -> System.out.println(r.getRole()));

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
