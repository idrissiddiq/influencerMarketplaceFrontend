package com.InfluencerMarketplace.clientapp;

import com.InfluencerMarketplace.clientapp.services.FilesStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class FinalProjectApplication implements CommandLineRunner {
	@Resource
	FilesStorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(FinalProjectApplication.class, args);
		System.out.println("FrontEnd Running Success!!!");
	}

	@Override
	public void run(String... arg) throws Exception {
//    storageService.deleteAll();
		storageService.init();
	}
}
