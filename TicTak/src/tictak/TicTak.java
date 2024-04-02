package tictak;

public class TicTak {
    static int num = 20;

    public static void main(String[] args) {

        Object monitor = new Object();


        ThreadOne thr1 = new ThreadOne(monitor);
        ThreadTwo thr2 = new ThreadTwo(monitor);

        thr1.start();
        thr2.getThread().start();

        try {
            thr1.join();
            thr2.getThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}