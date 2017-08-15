package ua.lardi.phoneBook.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.lardi.phoneBook.dao.PersistenceException;
import ua.lardi.phoneBook.model.Contact;
import ua.lardi.phoneBook.model.User;
import ua.lardi.phoneBook.service.ContactService;
import ua.lardi.phoneBook.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class MainController {
    private static final Logger LOGGER = LogManager.getLogger(MainController.class);
    private ContactService contactService;
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        return "redirect:/home";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home(Principal principal, Model model) {
        ModelAndView modelAndView = new ModelAndView("home");
        try {
            User currentUser = userService.findUserByLogin(principal.getName());
            if (currentUser != null) {
                List<Contact> userContacts = contactService.findAllContactsByUser(currentUser);
                modelAndView.addObject("contacts", userContacts);
            }
            modelAndView.addObject("username", currentUser.getName());
            if (!model.containsAttribute("contactForm")) {
                modelAndView.addObject("contactForm", new Contact());
            }
        } catch (PersistenceException e) {
            LOGGER.error(e);
        }

        return modelAndView;
    }

    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
