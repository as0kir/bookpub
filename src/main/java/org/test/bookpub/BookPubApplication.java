package org.test.bookpub;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.test.bookpubstarter.dbcount.DbCountRunner;
import org.test.bookpubstarter.dbcount.EnableDbCounting;

import java.util.Collection;
@Configuration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = @ComponentScan.Filter(UsedForTesting.class))
@EnableScheduling
@EnableDbCounting
@SpringBootApplication
public class BookPubApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookPubApplication.class, args);
	}

	@Bean
	public StartupRunner scheduleRunner() {
		return new StartupRunner();
	}
}

@interface UsedForTesting {}