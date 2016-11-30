package partieA;

/**
 * Created by Safiah on 29/11/2016.
 */
public class Rayon {

    private Supermarche.Articles articles;
    private int Nbexemplaire;
    private int taille;

    public Rayon(){
    }

    public int getNbexemplaire(){
        return this.Nbexemplaire;
    }

    public int getTaille(){
        return this.taille;
    }
     public void prendreArticle(){
         taille --;
     }



    public Supermarche.Articles getArticles(){
        return this.articles;
    }
}
