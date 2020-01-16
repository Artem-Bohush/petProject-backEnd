package com.gmail.bohush.art.petProjectBackEnd;

import com.gmail.bohush.art.petProjectBackEnd.entity.*;
import com.gmail.bohush.art.petProjectBackEnd.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class PetProjectBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetProjectBackEndApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(final UserService userService, final RoleService roleService,
								  final RecordService recordService, final RecordTypeService recordTypeService,
								  final CategoryService categoryService) {
		return new CommandLineRunner() {
			@Override
			public void run(String... strings) throws Exception {
				Role adminRole = new Role("ROLE_ADMIN");
				roleService.save(adminRole);
				Role userRole = new Role("ROLE_USER");
				roleService.save(userRole);

				User admin = new User();
				admin.setEmail("admin@gmail.com");
				admin.setPassword("$2a$10$LWW9BvyNeE1/Y7MbFQes0uXY6BBqJj5Rbck7hJfwPNS127WVsQ4gi");
				admin.setUsername("Artem");
				admin.setBalance("7777.77");
				admin.setCreated(new Date());
				admin.setUpdated(new Date());
				admin.setStatus(Status.ACTIVE);

				List<Role> roles = new ArrayList<>();
				roles.add(roleService.findByName("ROLE_ADMIN"));
				roles.add(roleService.findByName("ROLE_USER"));
				admin.setRoles(roles);

				List<Category> categories = new ArrayList<>();
				categories.add(new Category("Eда", "2500", admin));
				categories.add(new Category("Жилье", "8000", admin));
				categories.add(new Category("Машина", "5000", admin));
				admin.setCategories(categories);
				userService.save(admin);

				User user = new User();
				user.setEmail("user@gmail.com");
				user.setPassword("$2a$10$7nf7G945OnoXMjUWBqkG/eZ6pS/IX7VALdcj547XNC3KiVVBmvdaG");
				user.setUsername("Alex");
				user.setBalance("1188.55");
				user.setCreated(new Date());
				user.setUpdated(new Date());
				user.setStatus(Status.ACTIVE);

				List<Role> roles1 = new ArrayList<>();
				roles1.add(roleService.findByName("ROLE_USER"));
				user.setRoles(roles1);

				List<Category> categories1 = new ArrayList<>();
				categories1.add(new Category("Eда", "2500", user));
				categories1.add(new Category("Жилье", "8000", user));
				categories1.add(new Category("Машина", "5000", user));
				user.setCategories(categories1);
				userService.save(user);

				RecordType income = new RecordType("income");
				recordTypeService.save(income);
				RecordType outcome = new RecordType("outcome");
				recordTypeService.save(outcome);
				RecordType planning = new RecordType("planning");
				recordTypeService.save(planning);

				Record record = new Record();
				record.setDescription("incomeRecord");
				record.setSum("1550");
				record.setPlanningDate(null);
				record.setCreated(new Date());
				record.setType(recordTypeService.findByName("income"));
				record.setCategory(categoryService.findById((long) 4));
				record.setUser(user);
				recordService.save(record);
			}
		};
	}

}
