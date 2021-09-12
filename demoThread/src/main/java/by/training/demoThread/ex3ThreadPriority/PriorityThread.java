package by.training.demoThread.ex3ThreadPriority;

public class PriorityThread extends Thread{
    public PriorityThread(String name){
        super(name);
    }

    @Override
    public void run() {
        for(int i = 0;i<11; i++){
            System.out.println(i + ")" + getName());
        }
    }
}
