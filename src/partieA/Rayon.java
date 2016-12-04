package partieA;

/**
 * Created by Safiah on 29/11/2016.
 */
public class Rayon {

    private Supermarche.Articles articles;
    private static int Nbexemplaire;
    private int taille;

    public Rayon(Supermarche.Articles a, int n, int t){
        this.articles =a;
        this.Nbexemplaire=n;
        this.taille =t;
    }

    public int getNbexemplaire(){
        return this.Nbexemplaire;
    }

    synchronized public static void prendreArticle(Clients client) throws InterruptedException {
        while(Nbexemplaire == 0){
            client.wait();
        }
        System.out.println("Client "+client.getName()+" : Je prends un article");
        Nbexemplaire--;
     }

     public void deposerArticles(int i){
         Nbexemplaire+=i;
     }



    public  Supermarche.Articles getArticles(){
        return articles;
    }


}
