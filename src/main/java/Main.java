import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MainApp apka = new MainApp();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj liczbę" +"\n" +
                "1 dla szukania miasta po nazwie" +
                "\n" + "2 dla szukania miasta po kodzie zip" +
                "\n" + "0 do wyłączenia aplikacji");
       int runType = scanner.nextInt();
       switch (runType){
           case 1:
               apka.run();
               break;
           case 2:
               apka.run2();
               break;
           case 0:
               break;
          default:
              System.out.println("Invalid number please try again");
       }


    }
}
