package com.cydeo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JdTicketingProjectOrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(JdTicketingProjectOrmApplication.class, args);
	}
	@Bean    //if the class is not your class/you didn't write the class. annotate @Bean
	public ModelMapper mapper(){
		return new ModelMapper();
	}

}
