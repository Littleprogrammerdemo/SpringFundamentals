package com.philately.init;

import com.philately.service.PaperService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitialize implements CommandLineRunner {

    private final PaperService paperService;

    @Override
    public void run(String... args) {

        this.paperService.initData();
    }
}
