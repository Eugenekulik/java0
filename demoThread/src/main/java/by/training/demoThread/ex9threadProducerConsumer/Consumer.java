package by.training.demoThread.ex9threadProducerConsumer;

class Consumer extends Thread {
    Store store;
    int product = 0;
    final int N = 5;

    Consumer(Store store) {
        this.store = store;
    }

    public void run() {
        try {
            while (product < N) {

                product = product + store.get();
                System.out.println("Потребитель купил " + product + " товар(ов)");
                sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("поток потребителя прерван");
        }
    }
}
