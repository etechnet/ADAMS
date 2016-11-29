import java.net.MalformedURLException;
import java.net.URL;


public class FileHtml {
    private String name;
    private URL url;

    public FileHtml(String name, String url) throws MalformedURLException {
        this.name = name;
        this.url = new URL(url);
    }

    public String getName() {
        return this.name;
    }

    public URL getUrl() {
        return this.url;
    }
}
