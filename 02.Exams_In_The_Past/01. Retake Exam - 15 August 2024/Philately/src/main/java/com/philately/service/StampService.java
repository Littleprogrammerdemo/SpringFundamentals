package com.philately.service;

import com.philately.model.service.StampServiceModel;
import com.philately.model.view.HomeViewModel;

import java.util.List;

public interface StampService {

    boolean add(StampServiceModel stampServiceModel, String username);

    List<StampServiceModel> getMyStamps(String username);

    List<StampServiceModel> getOfferedStamps(String username);

    List<StampServiceModel> getWishlist(String username);

    List<StampServiceModel> getPurchasedStamps(String username);

    HomeViewModel getHomeViewModel();
}
