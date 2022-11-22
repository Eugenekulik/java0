package by.training.beauty.service;


import java.time.LocalTime;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class EmployeeWorkTimeScheduler {


    ScheduledExecutorService scheduler;


    public EmployeeWorkTimeScheduler(){
       scheduler =  new ScheduledThreadPoolExecutor(1);
    }

    public void init(){
        LocalTime time = LocalTime.now();


        scheduler.scheduleAtFixedRate(()->execute(),86400 - time.getSecond(), 86400, TimeUnit.SECONDS);
    }

    private void execute(){
        ScheduleService scheduleService = ServiceFactory.getInstance().getScheduleService();
        AppointmentService appointmentService = ServiceFactory.getInstance().getAppointmentService();
        scheduleService.archive();
        appointmentService.archive();
    }
}
