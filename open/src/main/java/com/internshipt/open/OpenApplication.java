package com.internshipt.open;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.internshipt.students",
		"com.internshipt.entity",
		"com.internshipt.users",
		"com.internshipt.internships",
		"com.internshipt.applications",
		"com.internshipt.hr"
})
@EnableJpaRepositories(basePackages = {
		"com.internshipt.students.Repository",
		"com.internshipt.users.Repository",
		"com.internshipt.internships.Repository",
		"com.internshipt.applications.Repository",
		"com.internshipt.hr.Repository"
})
@EntityScan(basePackages = {
		"com.internshipt.entity.Model"
})
public class OpenApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenApplication.class, args);
	}
}