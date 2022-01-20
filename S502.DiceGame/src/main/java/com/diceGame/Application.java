package com.diceGame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.diceGame.model.persistance.PlayerMongoRepository;
import com.diceGame.model.persistance.PlayerMysqlRepository;

@EnableMongoRepositories(basePackageClasses = PlayerMongoRepository.class)
@EnableJpaRepositories (basePackageClasses = PlayerMysqlRepository.class)
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
