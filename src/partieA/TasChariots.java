package partieA;
import java.util.concurrent.Semaphore;

/**
 * Created by Safiah on 30/11/2016.
 */
public class TasChariots extends Semaphore {

    private static final int MAX_CHARIOTS = Supermarche.NB_CHARIOTS;
    private static TasChariots INSTANCE = new TasChariots();

    private TasChariots(){
        super(MAX_CHARIOTS);
    }

    public TasChariots getInstance(){
        return this.INSTANCE;
    }





}
