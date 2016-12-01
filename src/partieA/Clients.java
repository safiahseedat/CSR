package partieA;

import java.util.*;

/**
 * Created by Safiah on 29/11/2016.
 */
public class Clients {

    public List<Supermarche.Articles> listArticles;

    public Clients(){

    }


    public void prendreArticle(Supermarche.Articles a){

    }

    public void prendreChariot() throws InterruptedException {
        Supermarche.chariots.acquire();
    }



}
