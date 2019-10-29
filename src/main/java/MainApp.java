import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

import org.json.JSONObject;

public class MainApp implements Runnable {

    private static void parseJson(String json) {
        double temp;
        double temp_max;
        int humidity;
        int pressure;
        int clouds;
        double wiatr_speed;
        double wiatr_kierunek;

        JSONObject rootObject = new JSONObject(json);
        if (rootObject.getInt("cod") == 200) {
            System.out.println(json);
            JSONObject mainObject = rootObject.getJSONObject("main");

            DecimalFormat df = new DecimalFormat("#.##");
            temp = mainObject.getDouble("temp");
            temp_max = mainObject.getDouble("temp_max");

            JSONObject windObject = rootObject.getJSONObject("wind");
            wiatr_speed = windObject.getDouble("speed");
            wiatr_kierunek = windObject.getDouble("deg");


            humidity = mainObject.getInt("humidity");
            pressure = mainObject.getInt("pressure");
            JSONObject cloudsObject = rootObject.getJSONObject("clouds");
            clouds = cloudsObject.getInt("all");

            System.out.println("Temperatura: " + df.format(temp) + " \u00b0C");
            System.out.println("Temperatura maksymalna: " + df.format(temp_max) + " \u00b0C");
            System.out.println("Wilgotność: " + humidity + " %");
            System.out.println("Zachmurzenie: " + clouds + "%");
            System.out.println("Ciśnienie: " + pressure + " hPa");
            System.out.println("Prędkość wiatru: " + wiatr_speed + " m/s");
            System.out.println("Kierunek wiatru: " + wiatr_kierunek + "  \u00b0");


        } else {
            System.out.println("Error");
        }
    }

    @Override
    public void run() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj nazwę miasta: ");

        String cityName = scanner.nextLine();

        try {
            String response = new HttpService().connect(Config.APP_URL + "?q=" + cityName + ",pl" + "&units=metric" +"&appid=" + Config.APP_ID );
            parseJson(response);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void run2() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj kod pocztowy miasta: ");

        String cityCode = scanner.nextLine();

        try {
            String response = new HttpService().connect(Config.APP_URL + "?zip=" + cityCode + ",pl" + "&units=metric" + "&appid=" + Config.APP_ID);
            parseJson(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
