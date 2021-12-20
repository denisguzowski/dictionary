package com.danny.dictionary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(WordRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Word(
                    1L,
                    "given name",
                    "n",
                    "",
                    "first name (American English)",
                    "My given name is Nicholas."
            )));
            log.info("Preloading " + repository.save(new Word(
                    2L,
                    "maiden name",
                    "n",
                    "",
                    "a woman’s family name before she got married and started using her husband’s family name",
                    "I’m Mrs Mary Smith, but my maiden name is Mary Black."
            )));
        };
    }
}
