package springCrudHibernate.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springCrudHibernate.model.User;
import springCrudHibernate.service.UserService;

@Controller
@RequestMapping(value = "/user")
@PreAuthorize("hasAnyAuthority('user')")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String userPage(ModelMap model) {
        model.addAttribute("currentUser", userService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName()));
        return "user";
    }
}
