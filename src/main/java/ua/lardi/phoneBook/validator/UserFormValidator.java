package ua.lardi.phoneBook.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.lardi.phoneBook.model.User;
import ua.lardi.phoneBook.service.UserService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserFormValidator implements Validator {
	private static final String LOGIN_PATTERN = "^[A-Za-z0-9]{3,}$";

	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		User user = (User) o;


		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "Required");
		if (user.getLogin().length() < 3 || user.getLogin().length() > 32) {
			errors.rejectValue("login", "Size.userForm.login");
		}
		if (!validate(LOGIN_PATTERN, user.getLogin())) {
			errors.rejectValue("login", "Size.userForm.ValidLogin");
		}
		if (userService.findUserByLogin(user.getLogin()) != null) {
			errors.rejectValue("login", "Duplicate.userForm.login");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Required");
		if (user.getLogin().length() < 5 || user.getLogin().length() > 32) {
			errors.rejectValue("name", "Size.userForm.username");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
		if (user.getPassword().length() < 5 || user.getPassword().length() > 32) {
			errors.rejectValue("password", "Size.userForm.password");
		}

		if (!user.getConfirmPassword().equals(user.getPassword())) {
			errors.rejectValue("confirmPassword", "Different.userForm.password");
		}
	}

	private boolean validate(String pattern, String value) {
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(value);
		return m.matches();
	}
}