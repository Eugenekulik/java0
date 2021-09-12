package by.training.demoThread.ex8StringBuffer;

public class Main {
    static int counter = 0;
    static StringBuffer s = new StringBuffer();
    public static void main(String args[ ]) {
        new Thread() {
            public void run() {
                synchronized (s) {
                    while (Main.counter++ < 3) {
                        s.append("A");
                        System.out.print("> " + counter + " ");
                        System.out.println(s);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (Main.counter++ < 6) {
            System.out.print("< " + counter + " ");
            s.append("B");
            System.out.println(s);
        }
    }
}
