package com.philately.model.view;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class HomeViewModel {

    List<StampViewModel> myStamps = new ArrayList<>();
    List<StampViewModel> offeredStamps = new ArrayList<>();
    List<StampViewModel> wishlist = new ArrayList<>();
    List<StampViewModel> purchases = new ArrayList<>();
}
