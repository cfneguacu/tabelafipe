package br.com.springboot.tabelafipe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TabelafipeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TabelafipeApplication.class, args);
	}

}
