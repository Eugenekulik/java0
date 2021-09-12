package by.training.demoThread.ex9threadProducerConsumer;

class Producer extends Thread {
    Store store;
    int product = 5;

    Producer(Store store) {
        this.store = store;
    }

    public void run() {
        try {
            while (product > 0) {
                product = product - store.put();
                System.out.println("производителю осталось произвести " + product + " товар(ов)");
                sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("поток производителя прерван");
        }
    }
}

