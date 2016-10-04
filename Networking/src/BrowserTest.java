import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Paths;

public class BrowserTest {
	public static void main(String[] args) throws Exception {
		URI oracle = new URI("file:///C:/Users/Alex/Documents/TestWebsite.html");
		URLConnection yc = oracle.toURL().openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null){
			System.out.println(inputLine);

		}
		in.close();
	}
}
