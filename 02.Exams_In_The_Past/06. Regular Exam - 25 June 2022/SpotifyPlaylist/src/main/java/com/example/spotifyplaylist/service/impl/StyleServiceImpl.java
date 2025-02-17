package com.example.spotifyplaylist.service.impl;

import com.example.spotifyplaylist.model.entity.Style;
import com.example.spotifyplaylist.model.entity.StyleName;
import com.example.spotifyplaylist.repository.StyleRepository;
import com.example.spotifyplaylist.service.StyleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StyleServiceImpl implements StyleService {

    private final StyleRepository styleRepository;

    @Override
    public void initData() {
        if (this.styleRepository.count() == 0) {
            List<Style> stylesList = new ArrayList<>();

            for (StyleName value : StyleName.values()) {
                Style style = new Style();
                style.setName(value);
                style.setDescription(String.format("Description for %s", value));
                stylesList.add(style);
            }

            styleRepository.saveAll(stylesList);
        }
    }

    @Override
    public Style findByName(StyleName name) {
        return this.styleRepository.findByName(name).orElse(null);
    }
}
