package workshop.music_workshop.web;

import org.modelmapper.ModelMapper;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import workshop.music_workshop.model.binding.UserRegisterBindingModel;
import workshop.music_workshop.model.services.UserRegistrationServiceModel;
import workshop.music_workshop.services.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final ModelMapper mapper;
    private final UserService userService;

    public UserController(ModelMapper mapper, UserService userService) {
        this.mapper = mapper;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping ("/login-error")
    public ModelAndView falledLogin(@ModelAttribute (UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
                                    RedirectAttributes redirectAttributes){

        ModelAndView modelAndView = new ModelAndView();
        redirectAttributes.addFlashAttribute("bad_credentials", true);
        redirectAttributes.addFlashAttribute("username", username);
        // modelAndView.addObject("bad_credentials", true);
        //modelAndView.addObject("username", username);
        modelAndView.setViewName("redirect:/users/login");
        return modelAndView;
    }

    @ModelAttribute("userRegisterBindingModel")
    public UserRegisterBindingModel createNindingModel(){
        return new UserRegisterBindingModel();
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String registerAndLoginUser(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                       BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            return "redirect:/users/register";
        }

        if(this.userService.userIsExists(userRegisterBindingModel.getUsername())){
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("userIsExists", true);
            return "redirect:/users/register";
        }

       UserRegistrationServiceModel userRegistrationServiceModel = this.mapper.map(userRegisterBindingModel, UserRegistrationServiceModel.class);
       this.userService.registerAndLoginUser(userRegistrationServiceModel);
        return "redirect:/home";
    }


}
