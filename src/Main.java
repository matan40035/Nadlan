// שמות המגישים
// קארינה עומר מתן קרן יוחאי ואיילי ועידן
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final int CREATE_USER = 1;
        final int LOGIN = 2;
        final int EXIT = 3;
        final int PUBLISH_PROPERTY = 1;
        final int REMOVE_PROPERTY = 2;
        final int DISPLAY_ALL_PROPERTIES = 3;
        final int DISPLAY_USER_PROPERTIES = 4;
        final int SEARCH_PROPERTIES = 5;
        final int LOGOUT = 6;
        int chooseUser;
        User connectUser = null;
        Scanner scanner = new Scanner(System.in);

        //O(n)

        do {
            System.out.println("Welcome to Real Estate\n" +
                    "1 - Create user\n" +
                    "2 - Login\n" +
                    "3 - Exit");
            chooseUser = scanner.nextInt();
            if (chooseUser == CREATE_USER) {
                RealEstate.createUser();
            } else if (chooseUser == LOGIN) {
                connectUser = RealEstate.login();
                if (connectUser != null) {
                    int chooseUserLogin = 0;
                    do {
                        System.out.println("Welcome " + connectUser.getUserName() + "\n" +
                                "1 - Publish a new property\n" +
                                "2 - Remove advertising on a property\n" +
                                "3 - Display all assets in the system\n" +
                                "4 - Show all properties published by the user\n" +
                                "5 - Search for a property by parameters\n" +
                                "6 - Log out and return to the main menu");
                        chooseUserLogin = scanner.nextInt();
                        switch (chooseUserLogin) {
                            case PUBLISH_PROPERTY:
                                boolean check = RealEstate.postNewProperty(connectUser);
                                if (check) {
                                    RealEstate.createProperty(connectUser);
                                }
                                break;
                            case REMOVE_PROPERTY:
                                RealEstate.removeProperty(connectUser);
                                break;
                            case DISPLAY_ALL_PROPERTIES:
                                RealEstate.printAllProperties();
                                break;
                            case DISPLAY_USER_PROPERTIES:
                                RealEstate.printPropertiesByUser(connectUser);
                                break;
                            case SEARCH_PROPERTIES:
                                Property [] PropertySearchToPrint;
                                PropertySearchToPrint=RealEstate.searchProperties();
                                for (Property post:PropertySearchToPrint) {
                                    System.out.println(post.toString());
                                }
                                break;
                            case LOGOUT:
                                break;
                            default:
                                System.out.println("Please enter a value between 1 and 6");
                                break;
                        }
                    } while (chooseUserLogin != LOGOUT);
                } else {
                    System.out.println("Incorrect username or password, try again");
                }
            }
        } while (chooseUser != EXIT);
    }
}