package partieA;

/**
 * Created by Safiah on 29/11/2016.
 */
public class EmployeCaisse extends Thread {

    Caisse caisse = Caisse.getInstance();

    public EmployeCaisse() {

    }

    synchronized public void run() {
        while (true) {
            try {
                caisse.scanner();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

