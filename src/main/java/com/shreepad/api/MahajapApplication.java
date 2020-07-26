package com.shreepad.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.shreepad.api.helper.ExcelToDatabaseHelper;

@SpringBootApplication
public class MahajapApplication {

	public static void main(String[] args) {
		SpringApplication.run(MahajapApplication.class, args);
		ExcelToDatabaseHelper excelTest = new ExcelToDatabaseHelper();
		
	}

}
