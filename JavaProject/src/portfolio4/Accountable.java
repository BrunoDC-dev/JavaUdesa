package portfolio4;
import java.util.List;

public interface Accountable {
    public int balance();
    public String report();
    public String  report (String prefix );
    public boolean contains (Accountable anAccountable);
    public List<Accountable> getAccountables();
    public Accountable add(Accountable anAccountable);
    public Accountable add (Account anAccount);
}
