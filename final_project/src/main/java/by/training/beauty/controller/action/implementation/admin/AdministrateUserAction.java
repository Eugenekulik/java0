package by.training.beauty.controller.action.implementation.admin;

import by.training.beauty.controller.action.Action;
import by.training.beauty.domain.Role;
import by.training.beauty.domain.User;
import by.training.beauty.service.ServiceException;
import by.training.beauty.service.ServiceFactory;
import by.training.beauty.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;


public class AdministrateUserAction implements Action {
    private static final Logger LOGGER = LogManager.getLogger(AdministrateUserAction.class);


    @Override
    public boolean isRedirect() {
        return true;
    }

    @Override
    public boolean isAllowed(HttpServletRequest request) {
        List<Role> roles = (List<Role>) request.getSession().getAttribute("roles");
        if(roles == null) return false;
        if(roles.contains(new Role("admin")) && request.getMethod().equals("POST")) return true;
        return false;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String method = request.getParameter("method");
        switch (method){
            case "create":
                create(request);
                break;
            case "update":
                update(request);
                break;
            case "delete":
                delete(request);
                break;
        }
        return "/administrate.html";
    }


    private boolean create(HttpServletRequest request){
        return false;
    }

    /**
     * This method allows you to make user data changes.
     * @param request HttpRequest which is used to get request data
     * @return true if changes take effects.
     */
    private boolean update(HttpServletRequest request){
        User user = new User();
        try {
            user.setId(Integer.parseInt(request.getParameter("userId")));
            List<User> userList = (List<User>) request.getSession().getAttribute("users");
            for (User u: userList) {
                if(user.getId() == u.getId()) {
                    user = u;
                    break;
                }
            }
            List<Role> roles = (List<Role>) request.getSession().getAttribute("allRoles");
            List<String> checked = List.of(request.getParameterValues("checkedRoles"));
            user.removeAllRoles();
            roles.stream().filter(role -> {
                return checked.contains(role.getName());
            }).forEach(user::addRole);
            if(user.getRoles().isEmpty()){
                user.addRole(new Role("client"));
            }
            user.setName(request.getParameter("name"));
            user.setPhone(request.getParameter("phone"));
            UserService userService =
                    ServiceFactory.getInstance().getUserService();
            userService.updateUser(user);
            return true;
        } catch (ServiceException e) {
            LOGGER.warn(String.format("it is impossible to update user with id: %d", user.getId()));
        } catch (NumberFormatException e) {
            LOGGER.warn("invalid user id");
        }
        return false;
    }



    private boolean delete(HttpServletRequest request){
        Integer id = null;
        try {
            id = Integer.parseInt(request.getParameter("userId"));
        } catch (NumberFormatException e) {
            LOGGER.info(e.getMessage());
        }
        if (id != null) {
            try {
                UserService userService =
                        ServiceFactory.getInstance().getUserService();
                userService.deleteUser(id);
                return true;
            } catch (ServiceException e) {
                LOGGER.warn(String.format("it is impossible to delete user by id: %d", id));
            }
        }
        return false;
    }


}
