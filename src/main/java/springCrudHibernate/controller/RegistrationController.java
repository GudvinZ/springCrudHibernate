package springCrudHibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import springCrudHibernate.model.User;
import springCrudHibernate.service.UserService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String registrationPage(@RequestParam(value = "error", required = false) String error,
                                   @RequestParam(value = "matchingError", required = false) String logout,
                                   ModelMap model) {
        model.addAttribute("error", error !=null);
        model.addAttribute("matchingError", logout !=null);
        return "registration";
    }

    @PostMapping
    public String registration(@ModelAttribute User user, @RequestParam String passwordConfirm, ModelMap model) {
        if(!user.getPassword().equals(passwordConfirm))
            return "redirect: /registration?matchingError";
        if(userService.addUser(user)) {
            model.addAttribute("postRegistration", true);
            model.addAttribute("login", user.getLogin());
            return "login";
        }
        else
            return "redirect: /registration?error";
    }
}
