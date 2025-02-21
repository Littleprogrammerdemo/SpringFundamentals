package app.web;

import app.user.model.User;
import app.user.service.UserService;
import app.web.dto.EditProfileRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{id}/profile")
    public ModelAndView showProfile(@PathVariable UUID id) {
        User user = userService.findById(id);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("edit-profile");
        mav.addObject("user", user);
        mav.addObject("editProfileRequest", new EditProfileRequest());

        return mav;
    }

    @PostMapping("/{id}/profile")
    public ModelAndView editProfile(@PathVariable UUID id, @Valid EditProfileRequest editProfileRequest, BindingResult bindingResult) {
        User user = userService.findById(id);

        if (bindingResult.hasErrors()) {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("edit-profile");
            mav.addObject("user", user);
            mav.addObject("editProfileRequest", editProfileRequest);  // Pass the form object back to the view
            return mav;
        }


        userService.editProfileFields(user, editProfileRequest);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/home");
        mav.addObject("user",user);

        return mav;
    }
}
