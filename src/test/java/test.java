import org.json.JSONObject;
import org.junit.Assert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Test {
@org.junit.Test

public void tescikPolaczenia() throws IOException {
HttpService service = new HttpService();
    HttpURLConnection connection = (HttpURLConnection) new URL(Config.APP_URL + "?q=" + "Toruń" + ",pl" + "&units=metric" + "&appid=" + Config.APP_ID).openConnection();
    Assert.assertEquals(200,connection.getResponseCode());
}
@org.junit.Test
public void tescikJsona() throws IOException {
    MainApp mainik = new MainApp();
    String response = new HttpService().connect(Config.APP_URL + "?q=" + "Toruń" + ",pl" + "&units=metric" + "&appid=" + Config.APP_ID);

}
}
// polaczenie z serwerem
// czy app id jest ok
// zrzucic odpowiedz do json i spradzic czy jest jsonem