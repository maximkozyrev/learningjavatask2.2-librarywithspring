package com.lazymax.springlibrary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LibraryMain {
    @Bean
    public LibraryMethods libraryMethods(){
        return new LibraryMethods();
    }
}
