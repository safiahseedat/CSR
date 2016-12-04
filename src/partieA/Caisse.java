package partieA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    private static boolean finscan;
    public int articlesTapis;


    private Caisse(){
        caisselibre= true;
       // tapis = new ArrayList<Boolean>(Supermarche.TAILLE_TAPIS);
        tapis = Supermarche.TAILLE_TAPIS;
    }

    public static Caisse getInstance(){
        return INSTANCE;
    }

   synchronized public void prendreCaisse(){
       caisselibre = false;
       notifyAll();
    }

    synchronized public boolean etatCaisse(){
        return caisselibre;
    }

    public void libererCaisse(){
        caisselibre=true;
        notifyAll();
    }



    synchronized public void scanner() throws InterruptedException {
        finscan = false;
        while(!clientsuivant){
            articlesTapis --;
            System.out.println("Le caissier a scanné un article");
            Thread.sleep(30);
            notifyAll();
            /*for(int i=0; i<tapis.size();i++){
                tapis.set(i,false);
                notifyAll();
                sleep(30);
            }*/
        }
        finscan= true;
        notifyAll();
    }

    synchronized public void passerArticles(Clients client) throws InterruptedException {
        System.out.println(client.getName() + " J'ai " + client.chariot + " articles dans mon chariot");
        articlesTapis = 0;
        clientsuivant= false;
        //Collections.fill(tapis, Boolean.FALSE);
        while(!clientsuivant) {
            //for (int i = 0; i < tapis.size(); i++) {
                while (articlesTapis >= tapis) {
                   System.out.println(client.getName() + " Le tapis est plein, j'attend");
                    client.wait();
                }
                if (client.chariot == 0) {
                    clientsuivant = true;
                    notifyAll();
                   System.out.println(client.getName() + " J'ai déposé tous mes articles");

                    while(!finscan){
                        client.wait();
                    }
                    client.payer();


                } else {
                    client.chariot--;
                    System.out.println(client.getName() + " J'ai " + client.chariot + " articles dans mon chariot");
                    articlesTapis ++;
                    System.out.println("il y a " + articlesTapis + " articles sur le tapis");
                    sleep(20);
                }

           // }
        }

    }







}
