package me.hyname;

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
    }

    @PreDestroy
    void shutdown() {
        logger.info("Shutting down ZuneAPI Service");
    }

}