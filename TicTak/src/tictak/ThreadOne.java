package tictak;

public class ThreadOne extends Thread {

    private Object monitor;

    public ThreadOne(Object monitor) {
        this.monitor = monitor;
    }

    // синхронизация потоков,
    // один поток вызывает notify(), что бы разбудить другой поток,
    // и ждет пока другой поток не вызовет notify()
    public void run() {
        try {
            for (int i = 0; i < TicTak.num; i++) {
                System.out.print(1 + " - ");
                synchronized (monitor) { // synchronized - только один поток может выполнить код внутри этого блока за раз
                    monitor.notify(); // когда поток вызывает notify(), то ожидающий поток будет разблокирован и продолжит выполнение
                    if (i < TicTak.num - 1) {
                        monitor.wait(); // текущий поток вызывает wait(), monitor будет освобожден, текущий поток заблокируется и будет ждать вызова метода notify() на мониторе другим потоком
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
