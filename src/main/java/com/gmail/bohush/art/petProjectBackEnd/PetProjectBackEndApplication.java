package com.gmail.bohush.art.petProjectBackEnd;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PetProjectBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetProjectBackEndApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner demo(final GeneralService generalService) {
//		return new CommandLineRunner() {
//			@Override
//			public void run(String... strings) throws Exception {
//
//				UserRole admin = new UserRole("admin");
//				generalService.addUserRole(admin);
//				UserRole user = new UserRole("user");
//				generalService.addUserRole(user);

//                CategoryType income = new CategoryType("income");
//                generalService.addCategoryType(income);
//                CategoryType expense = new CategoryType("expense");
//                generalService.addCategoryType(expense);
//
//                RecordType incomeRec = new RecordType("income");
//                generalService.addRecordType(incomeRec);
//                RecordType expenseRec = new RecordType("expense");
//                generalService.addRecordType(expenseRec);
//                RecordType planningRec = new RecordType("planning");
//                generalService.addRecordType(planningRec);

//				CustomUser cu = new CustomUser(admin, "art.bohush@gmail.com", "kalka1223",
//						"Artem", "0.00");
//				generalService.addCustomUser(cu);
//                cu = new CustomUser(admin, "art.bohush@gmail.com", "kalka1223",
//                        "Artem", "0.00");
//                generalService.addCustomUser(cu);
//				for (int i = 1; i < 11; i++) {
//					cu = new CustomUser(user, "email@gmail.com" + i, "password" + i,
//							"Name" + i, "0.00");
//					generalService.addCustomUser(cu);
//				}

//                Category c;
//                Category c1 = new Category(income, "ForTest", "dsfs");
//                generalService.addCategory(c1);
//                for (int i = 1; i < 5; i++) {
//                    if(i % 2 == 0) {
//                        c = new Category(income, "Category" + i, null);
//                    } else {
//                        c = new Category(expense, "Category" + i, "5000");
//                    }
//                    generalService.addCategory(c);
//                }
//
//                Record r;
//                for (int i = 1; i < 21; i++) {
//                    if (i % 2 == 0) {
//                        r = new Record(cu, incomeRec, c1, "some descr", "100500", "today");
//                    } else if (i == 5 || i == 11 || i == 19) {
//                        r = new Record(cu, expenseRec, c1, "some descr", "100500", "today");
//                    } else {
//                        r = new Record(cu, planningRec, c1, "some descr", "100500", "today");
//                    }
//                    generalService.addRecord(r);
//                }
//			}
//		};
//	}

}
