package com.philately.service.impl;

import com.philately.model.entity.Paper;
import com.philately.model.entity.Stamp;
import com.philately.model.entity.User;
import com.philately.model.service.StampServiceModel;
import com.philately.model.view.HomeViewModel;
import com.philately.model.view.StampViewModel;
import com.philately.repository.StampRepository;
import com.philately.service.PaperService;
import com.philately.service.StampService;
import com.philately.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class StampServiceImpl implements StampService {

    private final StampRepository stampRepository;
    private final ModelMapper modelMapper;
    private final PaperService paperService;
    private final UserService userService;
    private final HttpSession session;

    @Override
    public boolean add(StampServiceModel stampServiceModel, String username) {
        Paper paper = this.paperService.findByName((stampServiceModel.getPaper()));
        User user = this.userService.findUserByUsername(username);

        if (paper != null && user != null) {
            Stamp stamp = this.modelMapper.map(stampServiceModel, Stamp.class);
            stamp.setPaper(paper);
            stamp.setOwner(user);
            this.stampRepository.save(stamp);
            log.info("Successfully added stamp.");
            return true;
        } else {
            log.info("Failed to add stamp.");
            return false;
        }

    }

    @Override
    public List<StampServiceModel> getMyStamps(String username) {
        User owner = userService.findUserByUsername(username);

        return stampRepository.findAllByOwner(owner).stream()
                .map(stamp -> {
                    StampServiceModel stampServiceModel = this.modelMapper.map(stamp, StampServiceModel.class);
                    stampServiceModel.setPaper(stamp.getPaper().getName());
                    return stampServiceModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<StampServiceModel> getOfferedStamps(String username) {
        User currentUser = userService.findUserByUsername(username);

        return stampRepository.findAllByOwnerNot(currentUser)
                .stream()
                .map(stamp ->{
                    StampServiceModel stampServiceModel = this.modelMapper.map(stamp, StampServiceModel.class);
                    stampServiceModel.setOwner(stamp.getOwner().getUsername());
                    return stampServiceModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<StampServiceModel> getWishlist(String username) {

        return userService.findUserByUsername(username)
                .getWishedStamps()
                .stream()
                .map(stamp -> modelMapper.map(stamp, StampServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<StampServiceModel> getPurchasedStamps(String username) {

        return userService.findUserByUsername(username)
                .getPurchasedStamps()
                .stream()
                .map(stamp -> modelMapper.map(stamp, StampServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public HomeViewModel getHomeViewModel() {
        HomeViewModel homeViewModel = new HomeViewModel();

        String username = session.getAttribute("username").toString();

        List<StampViewModel> myStamps = getMyStamps(username).stream()
                .map(s -> modelMapper.map(s, StampViewModel.class))
                .collect(Collectors.toList());

        List<StampViewModel> offeredStamps = getOfferedStamps(username)
                .stream()
                .map(s -> modelMapper.map(s, StampViewModel.class))
                .collect(Collectors.toList());

        List<StampViewModel> wishlist = getWishlist(username)
                .stream()
                .map(s -> modelMapper.map(s, StampViewModel.class))
                .collect(Collectors.toList());

        List<StampViewModel> purchases = getPurchasedStamps(username)
                .stream()
                .map(s -> modelMapper.map(s, StampViewModel.class))
                .collect(Collectors.toList());

        homeViewModel.setMyStamps(myStamps);
        homeViewModel.setOfferedStamps(offeredStamps);
        homeViewModel.setWishlist(wishlist);
        homeViewModel.setPurchases(purchases);

        return homeViewModel;
    }
}
