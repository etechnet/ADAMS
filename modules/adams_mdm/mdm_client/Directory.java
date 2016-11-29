import java.util.HashMap;


public class Directory {
    private String name;
    private boolean isEmpty = true;
    private HashMap contenuto = null;

    public Directory(String name, boolean empty) {
        this.name = name;
        this.isEmpty = empty;
    }

    public Directory(String name, HashMap contenuto) {
        this.name = name;
        this.contenuto = contenuto;
        this.isEmpty = false;
    }

    public String getName() {
        return this.name;
    }

    public boolean isEmpty() {
        return this.isEmpty;
    }
}
