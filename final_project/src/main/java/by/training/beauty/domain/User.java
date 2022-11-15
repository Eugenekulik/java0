package by.training.beauty.domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * This class extends Entity and represents bean.
 * Is represents user and store any information about him.
 * It has fields:
 * name - username;
 * login  - user login;
 * password - user password;
 * phone - user phone;
 * role - user role represented as a Role class ;
 */
public class User extends Entity{
    private String name;
    private String login;
    private String password;
    private String phone;



    private List<Role> roles = new ArrayList<>();
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
    public List<Role> getRoles() {
        return roles;
    }

    public boolean addRole(Role role) {
        return roles.add(role);
    }
    public boolean removeRole(Role role){
        return roles.remove(role);
    }

    public void removeAllRoles(){
        roles.clear();
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
                " login: " + login;
    }
}
