package com.example.spotifyplaylist.service;

import com.example.spotifyplaylist.model.entity.Style;
import com.example.spotifyplaylist.model.entity.StyleName;

public interface StyleService {

    void initData();

    Style findByName(StyleName paperName);
}
