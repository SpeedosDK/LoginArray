import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
public class Main {
    // Laver en scanner som kan blive brugt overalt så man undgår at skabe flere scannere undervejs hvilket kan skabe problemer
    public static final Scanner scanner = new Scanner(System.in);

    // Laver en ArrayList til usernames og en til passwords
    private static final String[] usernames = new String[10];
    private static final String[] passwords = new String[10];
    private static final int MAX_TRIES = 3;
    public static void main(String[] args) {

        LocalTime tid = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);

        usernames[0] = ("admin");
        usernames[1] = ("user1");
        usernames[2] = ("user2");
        passwords[0] = ("password123");
        passwords[1] = ("letmein");
        passwords[2] = ("qwerty");


        // Printer velkomstbesked og brugerens valgmuligheder
        System.out.println("Velkommen. Klokken er " + tid + ". Vælg en mulighed.");
        System.out.println("1. Lav ny bruger.");
        System.out.println("2. Login.");
        System.out.println("3. Exit.");

        boolean repeat;
        // Laver en do while løkke som kører igennem, sådan hvis man skriver noget forkert, så lukker programmet ikke ned, men kører videre.
        do {
            repeat = false;
            String choice = scanner.nextLine();

            // Her bliver der lavet en simpel switch case hvor bruger vælger om de vil logge ind eller lave en bruger
            switch (choice) {
                case "1":
                    accountCreate();
                    loginAccount();
                    break;
                case "2":
                    loginAccount();
                    break;
                case "3":
                    System.out.println("System lukker ned.");
                    return;
                default:
                    System.out.println("Ugyldigt valg. Prøv igen.");
                    repeat = true;
            }
        } while (repeat);
    }

    public static void accountCreate() {

        // Bruger opretter et brugernavn og et kodeord

        String username = getUserInput("Indtast nyt brugernavn");
        String password = getUserInput("Indtast nyt password");
        // Her bliver brugerens indtastede username gemt i ArrayListen usernames
        int i = 0;
        while (i < usernames.length)
        {
            usernames[i] = username;
            i++;
        }
        i = 0;
        // Her bliver brugerens indtaste password gemt i ArrayListen passwords
        while(i < passwords.length)
        {
            passwords[i] = password;
            i++;
        }

    }

    public static void loginAccount()
    {
        LocalTime tid = LocalTime.now().truncatedTo(ChronoUnit.SECONDS); // gør så tiden bliver vist i sekunder
        LocalDate dato = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // retter format fra mm-dd-yyyy til dd-mm-yyyy



        for (int tries = 0; tries < MAX_TRIES; tries++)
        {
            String username = getUserInput("Indtast brugernavn: ");
            String password = getUserInput("Indtast password: ");

            boolean userFound = false;

            for (int i = 0; i < usernames.length; i++)
            {
                if (username.equals(usernames[i]))
                {
                    if (password.equals(passwords[i]))
                    {
                        System.out.println("Login succesfuldt. Du blev logget ind klokken " + tid + " den " + dato.format(formatter) + ".");
                        return;
                    }else
                    {
                        System.out.println("Forkert password. " + (2-tries) + " forsøg tilbage.");
                        break;
                    }
                }
                if (!userFound)
                {
                    System.out.println("Brugeren findes ikke");
                }
            }

        }
        System.out.println("Du har brugt alle forsøg. Programmet lukker");
    }
    public static String getUserInput(String prompt)
    {
        System.out.println(prompt);
        return scanner.nextLine();
    }
}