package by.training.beauty.domain;

import java.util.Objects;

/**
 * This class extends Entity and represents bean.
 * Is represents user and store any information about him.
 * It has fields:
 * name - username;
 * login  - user login;
 * password - user password;
 * phone - user phone;
 * role - user role(client, employee, admin);
 */
public class User extends Entity{
    private String name;
    private String login;
    private String password;
    private String phone;
    private Role role;

    public User(){}


    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public User(int id){
        setId(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role.getActual();
    }
    public void setRole(String role) {
        this.role = Role.valueOf(role);
    }

    public enum Role {
        CLIENT("client"), EMPLOYEE("employee"), ADMIN("admin");
        private String actual;

        Role(String actual) {
            this.actual = actual;
        }

        public String getActual() {
            return actual;
        }

        @Override
        public String toString() {
            return actual;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId() == user.getId() && Objects.equals(login, user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, getId());
    }

    @Override
    public String toString(){
        return "id: " + getId() + " name: " + name +
                " login: " + login + " role: " + role;
    }
}
