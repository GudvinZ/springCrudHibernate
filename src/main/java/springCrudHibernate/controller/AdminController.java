package springCrudHibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import springCrudHibernate.model.User;
import springCrudHibernate.service.IUserService;
import springCrudHibernate.service.UserService;

@Controller
public class AdminController {
    private final IUserService userService;

    @Autowired
    private AdminController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public ModelAndView adminPage() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("users", userService.getAllUsers());

        modelAndView.setViewName("admin");
        return modelAndView;
    }

    @PostMapping(value = "/admin/add")
    public ModelAndView addUser(@ModelAttribute User user) {
        ModelAndView modelAndView = new ModelAndView();

        if (user.getLogin().isEmpty() || user.getPassword().isEmpty() || user.getName().isEmpty() || user.getRoles().isEmpty()) {
            modelAndView.addObject("isEmptyForm", true);
        } else {
            modelAndView.addObject("isValidate", userService.addUser(user));
        }

        modelAndView.addObject("users", userService.getAllUsers());
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    @PostMapping(value = "/admin/delete")
    public ModelAndView deleteUser(@ModelAttribute User user) {
        ModelAndView modelAndView = new ModelAndView();

        userService.deleteUserById(user.getId());

        modelAndView.addObject("users", userService.getAllUsers());
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    @GetMapping("/admin/update/{id}")
    public ModelAndView updatePage(@PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("id", id);

        modelAndView.addObject("users", userService.getAllUsers());
        modelAndView.setViewName("update");
        return modelAndView;
    }

    @PostMapping(value = "/admin/update")
    public ModelAndView updateUser(@ModelAttribute User user) {
        ModelAndView modelAndView = new ModelAndView();

        boolean isAlreadyExist = !userService.updateUser(user);
        modelAndView.addObject("isAlreadyExist", isAlreadyExist);
        modelAndView.addObject("users", userService.getAllUsers());

        if(isAlreadyExist) {
            modelAndView.addObject("login", user.getLogin());
            modelAndView.addObject("id", user.getId());
            modelAndView.setViewName("update");
        } else {
            modelAndView.setViewName("admin");
        }

        return modelAndView;
    }
}
