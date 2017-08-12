package ua.lardi.phoneBook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.lardi.phoneBook.model.User;
import ua.lardi.phoneBook.service.UserService;
import ua.lardi.phoneBook.validator.UserFormValidator;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    UserFormValidator userFormValidator;

    @RequestMapping("/signin")
    public String signin(Model model) {
        return "signin";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationForm(User userForm, Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm,
                               BindingResult bindingResult, Model model) {
        userFormValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.save(userForm);
        model.addAttribute("message", "Вы успешно зарегестрировались.");
        return "forward:/signin";
    }
}
