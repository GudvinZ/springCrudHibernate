package springCrudHibernate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @GetMapping(value = {"/", "/login"})
    public ModelAndView loginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect: /admin");
        return modelAndView;
    }

//    @PostMapping(value = "/login")
//    public ModelAndView login() {
//
//    }
}
