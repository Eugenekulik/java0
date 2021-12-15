package by.training.beauty.service.validator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UserValidatorTest {

    @DataProvider(name = "invalidInputLogin")
    public Object[][] invalidLoginData(){
        return new Object[][]{
                {"asdsfя"}, //only english letters
                {"dfsdf dfsddf"}, // space are not allowed
                {"s;dfjkd"},  //symbols are not allowed
                {"2fdggjfh"}, //english letter should be first at line
                {"rgfjf"}, //too little symbols
                {"fdggjfhfdlgfjgnfgkdhg"} //too much symbols
        };
    }
    @DataProvider(name = "invalidInputPassword")
    public Object[][] invalidPasswordData(){
        return new Object[][]{
                {"/dfgdfdsf"}, //symbols are not allowed
                {"sfsfsff"}, //too little symbols
                {"djfhdjsgfsdjvhfhdgfhshjkdbhsjknjkdbdnjkbdfhkassnvjk"}, //too much symbols
                {"ыаываававы"} //only english letters
        };
    }
    @DataProvider(name = "invalidInputName")
    public Object[][] invalidNameData(){
        return new Object[][]{
                {"nds"}, //too little symbols
                {"dgdgd5"}, // numbers are not allowed
                {"Аровав_ьваол"}, //symbols are not allowed
                {"dfjdkkkjkdfkdhfjdnfj dhfjdfdfdsadfdgddfdf"} //too much symbols
        };
    }
    @DataProvider(name = "invalidInputPhone")
    public Object[][] invalidPhoneData(){
        return new Object[][]{
                {"112"}, //too little symbols
                {"67487648j"}, // letters are not allowed
                {"/39589322"}, //symbols are not allowed
                {"48674+334"}, // plus should be first at line or don't exist
                {"486743344375748754546"}, //too much symbols
        };
    }
    @DataProvider(name = "validInputLogin")
    public Object[][] validLoginData(){
        return new Object[][]{
                {"client"},
                {"fgkkjffdlkfldfsaldfk"},
                {"djgfdk-_."},
                {"df8495nsfjhs"}
        };
    }
    @DataProvider(name = "validInputPassword")
    public Object[][] validPasswordData(){
        return new Object[][]{
                {"75849578"},
                {"dlfjffdkldf"},
                {"fhjgf-fdj.-fdfjk"},
                {"fdffdfkhffjjfdkjgsdjfkldfnlkfnfjnfjfbxjfklsd758454"}
        };
    }
    @DataProvider(name = "validInputName")
    public Object[][] validNameData(){
        return new Object[][]{
                {"dhjd"},
                {"апвоава"},
                {"Afjgkf kgkfjf"},
                {"Gjghfjhgjf Gkdgjkdjd"}
        };
    }
    @DataProvider(name = "validInputPhone")
    public Object[][] validPhoneData(){
        return new Object[][]{
                {"+74384783"},
                {"7678"},
                {"+7678857285748593845"}
        };
    }

    @Test(groups = "validator", dataProvider = "validInputLogin")
    public void testValidLoginValidator(String login) {
        UserValidator userValidator = new UserValidator();
        assertTrue(userValidator.loginValidator(login));
    }

    @Test(groups = "validator", dataProvider = "validInputPassword")
    public void testValidPasswordValidator(String password) {
        UserValidator userValidator = new UserValidator();
        assertTrue(userValidator.passwordValidator(password));
    }

    @Test(groups = "validator", dataProvider = "validInputName")
    public void testValidNameValidator(String name) {
        UserValidator userValidator = new UserValidator();
        assertTrue(userValidator.nameValidator(name));
    }

    @Test(groups = "validator", dataProvider = "validInputPhone")
    public void testValidPhoneValidator(String phone) {
        UserValidator userValidator = new UserValidator();
        assertTrue(userValidator.phoneValidator(phone));
    }
    @Test(groups = "validator", dataProvider = "invalidInputLogin")
    public void testInvalidLoginValidator(String login) {
        UserValidator validator = new UserValidator();
        assertFalse(validator.loginValidator(login));
    }

    @Test(groups = "validator", dataProvider = "invalidInputPassword")
    public void testInvalidPasswordValidator(String password) {
        UserValidator userValidator = new UserValidator();
        assertFalse(userValidator.passwordValidator(password));
    }

    @Test(groups = "validator", dataProvider = "invalidInputName")
    public void testInvalidNameValidator(String name) {
        UserValidator userValidator = new UserValidator();
        assertFalse(userValidator.nameValidator(name));
    }

    @Test(groups = "validator", dataProvider = "invalidInputPhone")
    public void testInvalidPhoneValidator(String phone) {
        UserValidator userValidator = new UserValidator();
        assertFalse(userValidator.phoneValidator(phone));
    }
}