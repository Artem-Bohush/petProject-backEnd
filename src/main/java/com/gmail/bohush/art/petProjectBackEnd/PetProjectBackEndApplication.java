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
								  final CategoryService categoryService, final ChartDataService chartDataService) {
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
				admin.setBalance(7777.77);
				admin.setCreated(new Date());
				admin.setUpdated(new Date());
				admin.setStatus(Status.ACTIVE);

				List<Role> roles = new ArrayList<>();
				roles.add(roleService.findByName("ROLE_ADMIN"));
				roles.add(roleService.findByName("ROLE_USER"));
				admin.setRoles(roles);

				Category category1 = new Category("Eда", 2500, admin);
				ChartData chartData1 = new ChartData();
				chartData1.setCategory(category1);
				chartData1.setLabel(category1.getName());
				category1.setChartData(chartData1);

				Category category2 = new Category("Жилье", 8000, admin);
				ChartData chartData2 = new ChartData();
				chartData2.setCategory(category2);
				chartData2.setLabel(category2.getName());
				category2.setChartData(chartData2);

				Category category3 = new Category("Машина", 5000, admin);
				ChartData chartData3 = new ChartData();
				chartData3.setCategory(category3);
				chartData3.setLabel(category3.getName());
				category3.setChartData(chartData3);

				List<Category> categories1 = new ArrayList<>();
				categories1.add(category1);
				categories1.add(category2);
				categories1.add(category3);
				admin.setCategories(categories1);
				userService.save(admin);

				User user = new User();
				user.setEmail("user@gmail.com");
				user.setPassword("$2a$10$7nf7G945OnoXMjUWBqkG/eZ6pS/IX7VALdcj547XNC3KiVVBmvdaG");
				user.setUsername("Alex");
				user.setBalance(1188.55);
				user.setCreated(new Date());
				user.setUpdated(new Date());
				user.setStatus(Status.ACTIVE);

				List<Role> roles1 = new ArrayList<>();
				roles1.add(roleService.findByName("ROLE_USER"));
				user.setRoles(roles1);


				Category category4 = new Category("Eда", 2500, user);
				ChartData chartData4 = new ChartData();
				chartData4.setCategory(category4);
				chartData4.setLabel(category4.getName());
				category4.setChartData(chartData4);

				Category category5 = new Category("Жилье", 8000, user);
				ChartData chartData5 = new ChartData();
				chartData5.setCategory(category5);
				chartData5.setLabel(category5.getName());
				category5.setChartData(chartData5);

				Category category6 = new Category("Машина", 5000, user);
				ChartData chartData6 = new ChartData();
				chartData6.setCategory(category6);
				chartData6.setLabel(category6.getName());
				category6.setChartData(chartData6);

				List<Category> categories2 = new ArrayList<>();
				categories2.add(category4);
				categories2.add(category5);
				categories2.add(category6);
				user.setCategories(categories2);
				userService.save(user);

				RecordType income = new RecordType("income");
				recordTypeService.save(income);
				RecordType outcome = new RecordType("outcome");
				recordTypeService.save(outcome);
				RecordType planning = new RecordType("planning");
				recordTypeService.save(planning);

//				Record record = new Record();
//				record.setDescription("купил еду");
//				record.setSum(755);
//				record.setPlanningDate(null);
//				record.setCreated(new Date());
//				record.setType(recordTypeService.findByName("outcome"));
//				record.setCategory(categoryService.findById((long) 7));
//				record.setUser(user);


//				recordService.save(record);

			}
		};
	}

}
