package partieA;

import java.util.HashMap;
import java.util.Map;

import static partieA.Supermarche.RAYON_STOCK_INIT;
import static partieA.Supermarche.RAYON_STOCK_MAX;
import static partieA.Supermarche.listRayon;

/**
 * Created by Safiah on 29/11/2016.
 */
public class ChefRayon extends Thread {

    private final int MAX = 5;
    private Map<Supermarche.Articles,Integer > produits = new HashMap<Supermarche.Articles, Integer>();

    public ChefRayon(){
        for (Supermarche.Articles a: Supermarche.Articles.values()) {
            produits.put(a,0);
        }
    }

    public void run(){
        while(true){
            try {
                chercherProduit();
                remplirRayon();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void chercherProduit() throws InterruptedException {
        if(!articlesMax()) {
            sleep(500);
            System.out.println("Le chef de Rayon va chercher des articles");
            for (Supermarche.Articles a : Supermarche.Articles.values()) {
                produits.put(a, MAX);
            }
        }
    }

   synchronized public void remplirRayon() throws InterruptedException {
        Rayon r;
        int diff;
        int nb;
      for(int i = 0; i< listRayon.size(); i++){
          sleep(200);
          r=listRayon.get(i);
          System.out.println(r.toString());
          nb=r.getNbexemplaire();
          System.out.println(nb);
          diff = RAYON_STOCK_MAX - nb;
          if(nb<RAYON_STOCK_MAX){
              if(diff>=MAX){
                  r.deposerArticles(5);
                  produits.put(r.getArticles(),0);
                  System.out.println("Le rayon " + r.getArticles().name() + " a été rempli");
              }else{
                  r.deposerArticles(diff);
                  produits.put(r.getArticles(),MAX-diff);
                  System.out.println("Le rayon " + r.getArticles().name() + " a été rempli");
              }
              notifyAll();
          }
      }
    }

    public boolean articlesMax(){
        int i;
        boolean b = true;
        for(Supermarche.Articles a : Supermarche.Articles.values()){
            i = produits.get(a);
            if(i<5){b = false;}
        }
        return b;
    }
}
