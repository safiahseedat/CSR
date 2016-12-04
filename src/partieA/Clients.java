package partieA;

import java.util.*;


import static partieA.Supermarche.listRayon;

/**
 * Created by Safiah on 29/11/2016.
 */
public class Clients extends Thread{

    public Map<Supermarche.Articles, Integer> listArticles;
    public int chariot;
    private Caisse caisse = Caisse.getInstance();
    private int max= 3;

    public Clients(int i){
        createlist();
    }

    private void createlist(){
        listArticles = new HashMap<Supermarche.Articles, Integer>();
        listArticles.put(Supermarche.Articles.BEURRE,getRandom(max));
        listArticles.put(Supermarche.Articles.FARINE,getRandom(max));
        listArticles.put(Supermarche.Articles.LAIT,getRandom(max));
        listArticles.put(Supermarche.Articles.SUCRE,getRandom(max));

    }

    synchronized public void faireCourses() throws InterruptedException {
        int i;
        Rayon r;
        for(int j=0; j<4; j++){
            r= listRayon.get(j);
            i = listArticles.get(r.getArticles());
            System.out.println(this.getName() + " J'ai besoin de "+ i + " " + r.getArticles().toString());
            while(i>0){
                r.prendreArticle(this);
                chariot++;
                i--;
            }
            Thread.sleep(300);
        }

    }

   synchronized public void passerEnCaisse() throws InterruptedException {
        while(!caisse.etatCaisse()) {
            System.out.println(this.getName() +" : La caisse est prise, j'attends");
            this.wait();
        }
       System.out.println(this.getName() +" : La caisse est libre, je passe en caisse");
       caisse.prendreCaisse();
       notifyAll();
       caisse.passerArticles(this);

    }

    public void prendreChariot() throws InterruptedException {
        Supermarche.Taschariots.acquire();
        chariot = 0;
    }

    public void rendreChariot(){
        Supermarche.Taschariots.release();
        System.out.println(this.getName() + " Je dépose mon chariot");
    }

    public int getRandom(int max){

        return (int)(Math.random()*max);
    }

    private int getNbList(){
        int l = 0;
        for(Integer i: listArticles.values() ){
            l += i;
        }
        return l;
    }

    public void payer() throws InterruptedException {
        sleep(40);
        System.out.println(this.getName() +" : Je paie mes articles");
        caisse.libererCaisse();
        System.out.println(this.getName()+" : Je libère la caisse");
        notifyAll();
        rendreChariot();
    }

    public void run(){
        try {
            prendreChariot();
            System.out.println(this.getName() +" : J'ai pris mon chariot");
            faireCourses();
            System.out.println("fin des courses");
            passerEnCaisse();
            System.out.println("test2");
            payer();
            System.out.println("test3");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }
}
