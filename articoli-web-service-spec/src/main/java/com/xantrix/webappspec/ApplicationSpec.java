package com.xantrix.webappspec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.sviluppatoredisuccesso.webapp.Application;
import com.sviluppatoredisuccesso.webapp.Export;




@SpringBootApplication
@Import(Export.class)
public class ApplicationSpec extends Application {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationSpec.class, args);
	}
}
