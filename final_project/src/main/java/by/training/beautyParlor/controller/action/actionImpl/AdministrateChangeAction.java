package by.training.beautyParlor.controller.action.actionImpl;

import by.training.beautyParlor.controller.action.Action;
import by.training.beautyParlor.domain.Appointment;
import by.training.beautyParlor.domain.Procedure;
import by.training.beautyParlor.domain.User;
import by.training.beautyParlor.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * This class implement interface Action
 * which handles administrator requests to change data.
 *
 * @see Action
 * @see UserService
 * @see AppointmentService
 * @see ProcedureService
 * @see GraphicService
 */

public class AdministrateChangeAction implements Action {
    private static final Logger LOGGER = LogManager.getLogger(AdministrateChangeAction.class);

    @Override
    public boolean isRedirect() {
        return true;
    }

    @Override
    public Set<String> getRoles() {
        return Set.of("admin");
    }

    @Override
    public String execute(HttpServletRequest request) {
        switch ((String) request.getSession().getAttribute("activeTab")) {
            case "1":
                if (request.getParameter("delete") != null) {
                    Integer id = null;
                    try {
                        id = Integer.parseInt(request.getParameter("delete"));
                    } catch (NumberFormatException e) {
                    }
                    if (id != null) {
                        try {
                            UserService userService =
                                    ServiceFactory.getInstance().getUserService();
                            userService.deleteUser(id);
                        } catch (ServiceException e) {
                            LOGGER.warn(String.format("it is impossible to delete user by id: %d", id));
                        }
                    }
                }
                if (request.getParameter("update") != null) {
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
                        user.setName(request.getParameter("name"));
                        user.setPhone(request.getParameter("phone"));
                        user.setRole(request.getParameter("selectRole"));
                        UserService userService =
                                ServiceFactory.getInstance().getUserService();
                        userService.updateUser(user);
                    } catch (ServiceException e) {
                        LOGGER.warn(String.format("it is impossible to update user with id: %d", user.getId()));
                    } catch (NumberFormatException e) {
                        LOGGER.warn("invalid user id");
                    }
                }
                break;
            case "2":
                if(request.getParameter("delete") != null) {
                    Integer id = null;
                    try {
                        id = Integer.parseInt(request.getParameter("delete"));
                    } catch (NumberFormatException e) {
                    }
                    if (id != null) {
                        try {
                            AppointmentService appointmentService =
                                    ServiceFactory.getInstance().getAppointmentService();
                            appointmentService.deleteAppointment(id);
                        } catch (ServiceException e) {
                            LOGGER.warn(String.format("it is impossible to delete user by id: %d", id));
                        }
                    }
                }
                if(request.getParameter("update") != null) {
                    Appointment appointment = new Appointment();
                    try {
                        appointment.setId(Integer.parseInt(request.getParameter("appointmentId")));
                        List<Appointment> appointments = (List<Appointment>) request.getSession().getAttribute("appointments");
                        for (Appointment a: appointments) {
                            if(appointment.getId() == a.getId()) {
                                appointment = a;
                                break;
                            }
                        }
                        appointment.setPrice(Double.valueOf(request.getParameter("appointmentPrice")));
                        appointment.setStatus(Integer.parseInt(request.getParameter("selectStatus")));
                        AppointmentService appointmentService =
                                ServiceFactory.getInstance().getAppointmentService();
                        appointmentService.updateAppointment(appointment);
                    } catch (ServiceException e) {
                        LOGGER.warn(String.format("it is impossible to update appointment with id: %d", appointment.getId()));
                    } catch (NumberFormatException e) {
                        LOGGER.warn(e);
                    }
                }
                break;
            case "3":
                if(request.getParameter("delete") != null) {
                    Integer id = null;
                    try {
                        id = Integer.parseInt(request.getParameter("delete"));
                    } catch (NumberFormatException e) {
                    }
                    if (id != null) {
                        try {
                            ProcedureService procedureService =
                                    ServiceFactory.getInstance().getProcedureService();
                            procedureService.deleteProcedure(id);
                        } catch (ServiceException e) {
                            LOGGER.warn(String.format("it is impossible to delete procedure by id: %d", id));
                        }
                    }
                }
                if(request.getParameter("update") != null) {
                    Procedure procedure = new Procedure();
                    try {
                        procedure.setId(Integer.parseInt(request.getParameter("procedureId")));
                        List<Procedure> procedures = (List<Procedure>) request.getSession().getAttribute("procedures");
                        for (Procedure p: procedures) {
                            if(procedure.getId() == p.getId()) {
                                procedure = p;
                                break;
                            }
                        }
                        procedure.setElapsedTime(Integer
                                .parseInt(request.getParameter("procedureElapsedTime")));
                        procedure.setName(request.getParameter("procedureName"));
                        procedure.setDescription(request.getParameter("description"));
                        ProcedureService procedureService =
                                ServiceFactory.getInstance().getProcedureService();
                        procedureService.updateProcedure(procedure);
                    } catch (ServiceException e) {
                        LOGGER.warn(String.format("it is impossible to update appointment with id: %d", procedure.getId()));
                    } catch (NumberFormatException e) {
                        LOGGER.warn(e);
                    }
                }
                break;
            case "4":
                if(request.getParameter("delete") != null) {
                    Integer id = null;
                    try {
                        id = Integer.parseInt(request.getParameter("delete"));
                    } catch (NumberFormatException e) {
                    }
                    if (id != null) {
                        try {
                            GraphicService graphicService =
                                    ServiceFactory.getInstance().getGraphicService();
                            graphicService.deleteGraphic(id);
                        } catch (ServiceException e) {
                            LOGGER.warn(String.format("it is impossible to delete graphic by id: %d", id));
                        }
                    }
                }
                break;
        }
        return "/administrate.html";
    }

    @Override
    public String getMethod() {
        return "POST";
    }
}
