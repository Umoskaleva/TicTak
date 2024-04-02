package tictak;

public class ThreadTwo implements Runnable {
    private Thread thread;
    private Object monitor;

    public Thread getThread() {
        return thread;
    }

    public ThreadTwo(Object monitor) {
        thread = new Thread(this);
        this.monitor = monitor;
    }

    public void run() {
        try {
            Thread.sleep(1);
            for (int i = 0; i < TicTak.num; i++) {
                System.out.println(2);
                synchronized (monitor) { // только один поток может выполнить код внутри блока synchronized за раз
                    monitor.notify(); // monitor вызывает notify() для блокировки текущего потока и продолжения выполнения работы другого потока
                    if (i < TicTak.num - 1) {
                        monitor.wait(); // monitor будет освобожден и текущий поток будет ждать пока другой поток не вызовет метод notify() на обьекте monitor
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
