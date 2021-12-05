package by.training.beautyParlor.service.validator;

import java.util.regex.Pattern;

public class UserValidator {
    public boolean loginValidator(String login) {
        if (login.length() < 6 || login.length() > 20) {
            return false;
        }
        return Pattern.matches("([A-Za-z0-9_.-]*)", login);
    }

    public boolean passwordValidator(String password) {
        if (password.length() < 8 || password.length() > 50) {
            return false;
        }
        return Pattern.matches("([A-Za-z0-9-_.]*)", password);
    }

    public boolean nameValidator(String name) {
        if (name.length() < 4 || name.length() > 40) {
            return false;
        }
        return Pattern.matches("([A-ZА-ЯЁa-zа-яё ]*)", name);
    }

    public boolean phoneValidator(String phone) {
        if (phone.length() < 4 || phone.length() > 20) {
            return false;
        }
        return Pattern.matches("([+0-9][0-9]*)", phone);
    }
}
