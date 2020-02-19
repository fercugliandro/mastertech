package br.com.fercugliandro.mastertech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;

@EnableAutoConfiguration
@Configuration
@ComponentScan
@SpringBootApplication
public class MastertechApplication {
	public static void main(String[] args) {
		SpringApplication.run(MastertechApplication.class, args);
	}
}
