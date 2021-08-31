package by.training.demoThread.ex2runnablePerson;

import java.util.concurrent.TimeUnit;

public class RunnablePerson extends Person implements Runnable{
    public RunnablePerson(String name){
        super(name);
    }
    @Override
    public void run() {
        for(int i=0;i<11;i++) {
            System.out.println(i + ") " + getSurname() + " ,hello");
            try{
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
