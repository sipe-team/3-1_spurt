package com.order.perf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import reactor.core.scheduler.Schedulers;

@SpringBootApplication
@EnableR2dbcAuditing
public class JavaSpringbootOrderPerfOptimizerApplication {

	public static void main(String[] args) {
		Schedulers.enableMetrics();
		SpringApplication.run(JavaSpringbootOrderPerfOptimizerApplication.class, args);
	}

}
