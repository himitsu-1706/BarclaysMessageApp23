package com.example;

import com.example.dataaccess.PeoplePopulator;
import com.example.dataaccess.Populator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BarclayMessageApplication {
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(BarclayMessageApplication.class, args);

		Populator populator = context.getBean(Populator.class);
		populator.populate();

		PeoplePopulator populator1 = context.getBean(PeoplePopulator.class);
		populator1.populate();
	}

}
