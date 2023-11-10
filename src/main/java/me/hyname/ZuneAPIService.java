package me.hyname;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import me.hyname.adapter.Adapter;
import me.hyname.storage.Storage;

@SpringBootApplication
public class ZuneAPIService {

    Logger logger = LogManager.getRootLogger();

    Storage storage;
    Adapter adapter;

    public static void main(String[] args) {
        SpringApplication.run(ZuneAPIService.class, args);
    }

    public ZuneAPIService(Storage storage, Adapter adapter) {
        this.storage = storage;
        this.adapter = adapter;
    }

    @PostConstruct
    public void init() {
        logger.info("Initializing ZuneAPI Service");
        logger.info("Initialized ZuneAPI Service");
    }

    @PreDestroy
    void shutdown() {
        List<String> failures = new ArrayList<>();
        logger.info("Shutting down ZuneAPI Service");
        
        if(!storage.shutdown()) {
            failures.add("Database connection");
        }

        if (failures.isEmpty()) {
            logger.info("Successfully shutdown ZuneAPI Service");
        } else {
            logger.error("The following items failed to shut down properly: " + failures);
        }
    }

}