package com.dellemc.devops.meta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.dellemc.devops.meta.controller")
public class DevopsMetadataApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevopsMetadataApplication.class, args);
	}
}
