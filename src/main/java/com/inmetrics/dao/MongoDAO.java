package com.inmetrics.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.inmetrics.controler.FeederControler;
import com.inmetrics.domain.Sites;
import com.inmetrics.repo.CustomRepository;
import com.inmetrics.repo.SiteRepository;

public class MongoDAO implements CommandLineRunner {

	@Autowired
	SiteRepository repository;

	@Autowired
	CustomRepository crepo;

	static final Logger logger = Logger.getLogger(MongoDAO.class);

	public void deleteAll() {
		logger.info("Deleting all records..");

		repository.deleteAll();
	}

	public void deleteById(String id) {
		logger.info("Deleting the registry by id");
		repository.deleteById(id);
	}

	public void addSampleData() {
		logger.info("Adding sample data");
		repository.save(new Sites("Google", "www.google.com.br"));

	}

	public void listAll() {

		logger.info("Listing mongoDB queue");
		repository.findAll().forEach(u -> logger.info(u));

	}

	public void findFirst() {
		logger.info("Finding first by Name");
		Sites u = repository.findFirstByName("Google");
		logger.info(u);
	}

	public void addSites() {

		List<Sites> sites = new ArrayList<>();

		sites = FeederControler.lerExcel("Sites.xls");

		if (null != sites) {
			for (Sites sites2 : sites) {
				repository.save(sites2);
			}
		} else {
			logger.info("null excel spreadsheet");
		}
	}

	@Override
	public void run(String... args) throws Exception {

		BasicConfigurator.configure();
		addSites();
//	addSampleData();
		listAll();
//	findFirst();
	}
}
