import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

import org.json.JSONObject;

public class MainApp implements Runnable {

    private void parseJson(String json) {
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
        run();

    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj liczbę" + "\n" +
                "1 dla szukania miasta po nazwie" +
                "\n" + "2 dla szukania miasta po kodzie zip" +
                "\n" + "3 dla szukania miasta po ID" +
                "\n" + "4 dla szukania miasta po dlugosci i szerokisci geograficznej" +
                "\n" + "0 do wyłączenia aplikacji");
        int runType = scanner.nextInt();
        switch (runType) {
            case 1:
                nameCity();
                break;
            case 2:
                zipCity();
                break;
            case 3:
                idCity();
                break;
            case 4:
                latilonCity();
                break;
            case 0:
                break;
            default:
                System.out.println("Invalid number please try again");
                run();

        }
    }

    private void latilonCity() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj lat i lon miasta: ");
        String lat = scanner.next();
        String lon = scanner.next();
        try {
            String response = new HttpService().connect(Config.APP_URL + "?lat=" + lat + "&" + "?lon=" + lon + "&units=metric" + "&appid=" + Config.APP_ID);
            parseJson(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void idCity() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj kod ID miasta: ");
        String cityID = scanner.next();
        try {
            String response = new HttpService().connect(Config.APP_URL + "?id=" + cityID + "&units=metric" + "&appid=" + Config.APP_ID);
            parseJson(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void zipCity() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj kod pocztowy miasta: ");
        String cityCode = scanner.next();
        try {
            String response = new HttpService().connect(Config.APP_URL + "?zip=" + cityCode + ",pl" + "&units=metric" + "&appid=" + Config.APP_ID);
            parseJson(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void nameCity() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj nazwę miasta: ");
        String cityName = scanner.next();
        try {

            String response = new HttpService().connect(Config.APP_URL + "?q=" + cityName + ",pl" + "&units=metric" + "&appid=" + Config.APP_ID);
            parseJson(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
