package com.cfc.contagino;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages={"com.cfc"})
//@ComponentScan("com.cfc.*")
@EnableConfigurationProperties
public class PandemicAlertSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(PandemicAlertSvcApplication.class, args);
	}

}
