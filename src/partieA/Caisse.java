package partieA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static java.lang.Thread.sleep;

/**
 * Created by Safiah on 01/12/2016.
 */
public class Caisse {
    private static boolean caisselibre;
    //public static List<Boolean> tapis;
    public static int tapis;
    private static Caisse INSTANCE = new Caisse();
    private static boolean clientsuivant;
    private static boolean finscan = false;
    public int articlesTapis = 0;


    private Caisse() {
        caisselibre = true;
        //tapis = new ArrayList<Boolean>(Supermarche.TAILLE_TAPIS);
        tapis = Supermarche.TAILLE_TAPIS;
    }

    public static Caisse getInstance() {
        return INSTANCE;
    }

    synchronized public void prendreCaisse() {
        caisselibre = false;
        notifyAll();
    }

    synchronized public boolean etatCaisse() {
        return caisselibre;
    }

    synchronized public void libererCaisse() {
        caisselibre = true;
        notifyAll();
    }


    synchronized public void scanner() throws InterruptedException {
        finscan = false;
        if (articlesTapis > 0) {
            System.out.println("il y a " + articlesTapis + " sur le tapis");
            Thread.sleep(30);
            articlesTapis--;
            System.out.println("Le caissier a scanné un article");
            notifyAll();
        } else if (clientsuivant) {
            finscan = true;
            notifyAll();
        }
    }

    synchronized public void passerArticles(Clients client) throws InterruptedException {
        System.out.println(etatCaisse());
        System.out.println(client.getName() + " J'ai " + client.chariot + " articles dans mon chariot");
        clientsuivant = false;
        //Collections.fill(tapis, Boolean.FALSE);
        while (!clientsuivant) {
            while (articlesTapis >= tapis) {
                System.out.println(client.getName() + " Le tapis est plein, j'attend");
                this.wait();
            }
            if (client.chariot == 0) {
                System.out.println(client.getName() + " J'ai déposé tous mes articles");
                while (articlesTapis > 0) {
                    this.wait();
                }
                clientsuivant = true;
                notifyAll();
            } else {
                client.chariot--;
                System.out.println(client.getName() + " J'ai " + client.chariot + " articles dans mon chariot");
                articlesTapis++;
                System.out.println("il y a " + articlesTapis + " articles sur le tapis moi");
                notifyAll();
                sleep(20);
            }

            // }
        }

    }


}
