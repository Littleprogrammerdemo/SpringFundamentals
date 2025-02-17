package com.example.spotifyplaylist.init;

import com.example.spotifyplaylist.service.StyleService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitialize implements CommandLineRunner {

    private final StyleService styleService;

    @Override
    public void run(String... args) {

        this.styleService.initData();
    }
}
