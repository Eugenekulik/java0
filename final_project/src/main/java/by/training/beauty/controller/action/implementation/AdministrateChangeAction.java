package by.training.beauty.controller.action.implementation;

import by.training.beauty.controller.action.Action;
import by.training.beauty.domain.Appointment;
import by.training.beauty.domain.Procedure;
import by.training.beauty.domain.User;
import by.training.beauty.service.*;
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
 * @see ScheduleService
 */

public class AdministrateChangeAction implements Action {
    //CONSTANTS
    private static final String DELETE = "delete";
    private static final String UPDATE = "update";

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
                if (request.getParameter(DELETE) != null) {
                    Integer id = null;
                    try {
                        id = Integer.parseInt(request.getParameter(DELETE));
                    } catch (NumberFormatException e) {
                        LOGGER.info(e.getMessage());
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
                if (request.getParameter(UPDATE) != null) {
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
                if(request.getParameter(DELETE) != null) {
                    Integer id = null;
                    try {
                        id = Integer.parseInt(request.getParameter(DELETE));
                    } catch (NumberFormatException e) {
                        LOGGER.info(e.getMessage());
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
                if(request.getParameter(UPDATE) != null) {
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
                if(request.getParameter(DELETE) != null) {
                    Integer id = null;
                    try {
                        id = Integer.parseInt(request.getParameter(DELETE));
                    } catch (NumberFormatException e) {
                        LOGGER.info(e.getMessage());
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
                if(request.getParameter(UPDATE) != null) {
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
                        LOGGER.info(e.getMessage());
                    }
                }
                break;
            case "4":
                if(request.getParameter(DELETE) != null) {
                    Integer id = null;
                    try {
                        id = Integer.parseInt(request.getParameter(DELETE));
                    } catch (NumberFormatException e) {
                        LOGGER.info(e.getMessage());
                    }
                    if (id != null) {
                        try {
                            ScheduleService scheduleService =
                                    ServiceFactory.getInstance().getscheduleService();
                            scheduleService.deleteschedule(id);
                        } catch (ServiceException e) {
                            LOGGER.warn(String.format("it is impossible to delete schedule by id: %d", id));
                        }
                    }
                }
                break;
            default:
                break;
        }
        return "/administrate.html";
    }

    @Override
    public String getMethod() {
        return "POST";
    }
}
