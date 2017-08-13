package ua.lardi.phoneBook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.lardi.phoneBook.model.Contact;
import ua.lardi.phoneBook.service.ContactService;
import ua.lardi.phoneBook.service.UserService;
import ua.lardi.phoneBook.validator.ContactFormValidator;

import java.security.Principal;

@Controller
@RequestMapping(value = "/contact")
public class ContactController {
    @Autowired
    private ContactService contactService;
    @Autowired
    private UserService userService;
    @Autowired
    private ContactFormValidator contactFormValidator;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Contact getContact(@PathVariable("id") long id) {
        return contactService.findById(id);
    }

    @RequestMapping(value = "/delete/{id}")
    public String deleteContact(@PathVariable("id") long id) {
        contactService.delete(id);

        return "redirect:/home";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String processMyForm(@ModelAttribute("contactForm") Contact contact,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes,
                                Principal principal) {

        contactFormValidator.validate(contact, bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.contactForm", bindingResult);
            redirectAttributes.addFlashAttribute("contactForm", contact);
        } else {
            contact.setUser(userService.findUserByLogin(principal.getName()));
            contactService.save(contact);
        }
        return "redirect:/home";
    }

}
