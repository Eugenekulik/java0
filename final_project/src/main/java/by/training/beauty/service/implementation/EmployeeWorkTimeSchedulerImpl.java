package by.training.beauty.service.implementation;


import by.training.beauty.service.ServiceFactory;
import by.training.beauty.service.spec.AppointmentService;
import by.training.beauty.service.spec.EmployeeWorkTimeScheduler;
import by.training.beauty.service.spec.ScheduleService;

import java.time.LocalTime;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class EmployeeWorkTimeSchedulerImpl implements EmployeeWorkTimeScheduler {

    private  ScheduledExecutorService scheduler;

    @Override public void init(){
        LocalTime time = LocalTime.now();

        scheduler =  new ScheduledThreadPoolExecutor(1);
        scheduler.scheduleAtFixedRate(this::execute,86400L - time.getSecond(), 86400, TimeUnit.SECONDS);
    }

    public void execute() {
        ScheduleService scheduleService = ServiceFactory.getInstance().getScheduleService();
        AppointmentService appointmentService = ServiceFactory.getInstance().getAppointmentService();
        scheduleService.archive();
        appointmentService.archive();
    }
}
