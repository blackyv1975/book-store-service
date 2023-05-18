package com.vinc.bookstoreservice.config;

import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportRuntimeHints;

@ImportRuntimeHints(BookStoreServiceRuntimeHints.class)
@Configuration
public class BookStoreServiceRuntimeHints implements RuntimeHintsRegistrar {

    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        // Register resources
        hints.resources().registerPattern("db/changelog/db.changelog-master.xml");
        hints.resources().registerPattern("db/changelog/db.changelog-1.0.xml");
    }

}