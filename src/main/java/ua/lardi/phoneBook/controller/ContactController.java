package ua.lardi.phoneBook.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.lardi.phoneBook.dao.PersistenceException;
import ua.lardi.phoneBook.model.Contact;
import ua.lardi.phoneBook.service.ContactService;
import ua.lardi.phoneBook.service.UserService;
import ua.lardi.phoneBook.validator.ContactFormValidator;

import java.security.Principal;

@Controller
@RequestMapping(value = "/contact")
public class ContactController {
    private static final Logger LOGGER = LogManager.getLogger(ContactController.class);
    private ContactService contactService;
    private UserService userService;
    private ContactFormValidator contactFormValidator;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Contact getContact(@PathVariable("id") long id) {
        Contact contact = null;
        try {
             contact = contactService.findById(id);
        } catch (PersistenceException e) {
            LOGGER.error(e);
        }
        return contact;
    }

    @RequestMapping(value = "/delete/{id}")
    public String deleteContact(@PathVariable("id") long id) {
        try {
            contactService.delete(id);
        } catch (PersistenceException e) {
            LOGGER.error(e);
        }
        return "redirect:/home";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("contactForm") Contact contact,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes,
                                Principal principal) {

        contactFormValidator.validate(contact, bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.contactForm", bindingResult);
            redirectAttributes.addFlashAttribute("contactForm", contact);
        } else {
            try {
                contact.setUser(userService.findUserByLogin(principal.getName()));
                contactService.save(contact);
            } catch (PersistenceException e) {
                LOGGER.error(e);
            }
        }
        return "redirect:/home";
    }

    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setContactFormValidator(ContactFormValidator contactFormValidator) {
        this.contactFormValidator = contactFormValidator;
    }
}
