package com.nttdata.TableIdservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class TableIdServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TableIdServiceApplication.class, args);
	}

}
