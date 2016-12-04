package partieA;

/**
 * Created by Safiah on 29/11/2016.
 */
public class EmployeCaisse extends Thread {

    Caisse caisse = Caisse.getInstance();

    public EmployeCaisse() {

    }

    synchronized public void run() {
        while (true){
            while (caisse.etatCaisse()) {
                System.out.println("La caisse est libre, le caissier attend un client");
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
             }
            try {
                System.out.println("le client passe en caisse, le caissier se réveille");
                caisse.scanner();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
