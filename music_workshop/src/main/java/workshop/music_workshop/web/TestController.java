package workshop.music_workshop.web;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import workshop.music_workshop.model.entities.UserEntity;
import workshop.music_workshop.model.services.UserServiceModel;
import workshop.music_workshop.services.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/test")
public class TestController {

    private final UserService userService;
    private final ModelMapper mapper;

    public TestController(UserService userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }


    @GetMapping("/update")
    public String update(Principal principal, Model model){
        UserEntity userEntity = this.userService.findByUsername(principal.getName());
        UserServiceModel userServiceModel = this.mapper.map(userEntity, UserServiceModel.class);
        model.addAttribute("username", userServiceModel.getUsername());
        model.addAttribute("userId", userServiceModel.getId());
        return "test";

    }

    @PatchMapping("/update/{id}")
    public String updateConfirm(@PathVariable Long id){
        System.out.println();
        return "redirect:/home";
    }
}
