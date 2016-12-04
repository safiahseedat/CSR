package partieA;

import java.util.*;
import java.util.concurrent.Semaphore;

/**
 * Created by Safiah on 29/11/2016.
 */
public class Supermarche {


    public static final int RAYON_STOCK_INIT = 10 ;
    public static final int RAYON_STOCK_MAX = 10;
    public final Map <String,Integer> TPS = new HashMap<String, Integer>();
    public static final int TAILLE_TAPIS = 10;
    public static final int NB_CHARIOTS = 1;
    public static Semaphore Taschariots = new Semaphore(NB_CHARIOTS);
    public static List<Rayon> listRayon;



    public static List<Clients> clients = new ArrayList<Clients>();

    public enum Articles{
        SUCRE, FARINE, BEURRE, LAIT;
    }


    /*private void createMap(){
        TPS.put("faire le plein", 500);
        TPS.put("déplacement entre rayon", 200);
        TPS.put("marche du client entre rayon", 300);
        TPS.put("placer element sur tapis", 20);

    }*/

    private static void initRayon(){
        Rayon r;
        listRayon= new ArrayList<Rayon>(4);
        int i = 0;
        for(Articles e : Articles.values()) {
            //System.out.println(e);
            r = new Rayon(e,RAYON_STOCK_INIT, RAYON_STOCK_MAX);
            listRayon.add(i,r);
            i++;
            //System.out.println(r.getArticles());
        }
    }

    public static void main(String [] args){
        initRayon();
        Clients un = new Clients(1);
        Clients deux = new Clients(2);
        clients.add(un);
        clients.add(deux);
        un.start();
        deux.start();
        Caisse caisse= Caisse.getInstance();
        //ChefRayon chef = new ChefRayon();
        //chef.start();
        EmployeCaisse em = new EmployeCaisse();
        em.start();
    }
}
