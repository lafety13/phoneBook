package ua.lardi.phoneBook.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.lardi.phoneBook.dao.PersistenceException;
import ua.lardi.phoneBook.model.User;
import ua.lardi.phoneBook.service.UserService;
import ua.lardi.phoneBook.validator.UserFormValidator;

@Controller
public class AuthController {
    private static final Logger LOGGER = LogManager.getLogger(AuthController.class);
    private UserService userService;
    private UserFormValidator userFormValidator;

    @RequestMapping("/signin")
    public String signin(Model model) {
        return "signin";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registrationForm(User userForm, Model model) {
        return new ModelAndView("registration", "userForm", new User());
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm,
                               BindingResult bindingResult,
                               Model model) {
        try {
            userFormValidator.validate(userForm, bindingResult);
            if (bindingResult.hasErrors()) {
                return "registration";
            }
            userService.save(userForm);
        } catch (PersistenceException e) {
            LOGGER.error(e);
        }
        model.addAttribute("message", "Вы успешно зарегестрировались.");
        return "forward:/signin";
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserFormValidator(UserFormValidator userFormValidator) {
        this.userFormValidator = userFormValidator;
    }
}
