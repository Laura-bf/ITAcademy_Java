package com.diceGame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.diceGame.model.persistance.MongoPlayerRepository;
import com.diceGame.model.persistance.PlayerRepository;

@EnableMongoRepositories(basePackageClasses = MongoPlayerRepository.class)
@EnableJpaRepositories (basePackageClasses = PlayerRepository.class)
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
