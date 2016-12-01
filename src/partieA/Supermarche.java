package partieA;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

/**
 * Created by Safiah on 29/11/2016.
 */
public class Supermarche {


    public final int RAYON_STOCK_INIT = 10 ;
    public final int RAYON_STOCK_MAX = 5;
    public final Map <String,Integer> TPS = new HashMap<String, Integer>();
    public final int TAILLE_TAPIS = 10;
    public static final int NB_CHARIOTS = 10;
    public static Semaphore chariots;


    public enum Articles{
        SUCRE, FARINE, BEURRE, LAIT
    }



    public Supermarche(){
        createMap();
        this.chariots = new Semaphore(NB_CHARIOTS);

    }



    private void createMap(){
        TPS.put("faire le plein", 500);
        TPS.put("déplacement entre rayon", 200);
        TPS.put("marche du client entre rayon", 300);
        TPS.put("placer element sur tapis", 20);

    }

    public static void main(String [] args){

        //System.out.println("o");
    }
}
