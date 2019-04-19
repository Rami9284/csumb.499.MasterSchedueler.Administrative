package com.csumb.Administrative;

import com.csumb.Administrative.fileEntities.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class AdministrativeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdministrativeApplication.class, args);
	}

}
