import java.util.Arrays;
import java.util.Scanner;

public class RealEstate {
    static User[] users = new User[0];

    public static Property[] properties = new Property[0];

    static City[] cities = new City[10];

    static {
        cities[0] = new City("Tel Aviv", "Center", new String[]{"Ben Gurion", "Herzl", "Rothschild"});
        cities[1] = new City("Haifa", "North", new String[]{"Yoseftal", "Hagana", "Herzel"});
        cities[2] = new City("Beer Sheva", "Negev", new String[]{"David Tzameret", "Ha'atzmaut", "Ha'bustan"});
        cities[3] = new City("Ashdod", "South", new String[]{"Ha'aliyah", "Ha'atzmaut", "Ha'or"});
        cities[4] = new City("Rishon LeZion", "Center", new String[]{"Ha'aliyah", "Ha'atzmaut", "Ha'or"});
        cities[5] = new City("Petah Tikva", "Center", new String[]{"Ha'aliyah", "Ha'atzmaut", "Ha'or"});
        cities[6] = new City("Nahariya", "North", new String[]{"Ha'aliyah", "Ha'atzmaut", "Ha'or"});
        cities[7] = new City("Afula", "North", new String[]{"Ha'aliyah", "Ha'atzmaut", "Ha'or"});
        cities[8] = new City("Hadera", "Sharon", new String[]{"Ha'aliyah", "Ha'atzmaut", "Ha'or"});
        cities[9] = new City("Ramat Gan", "Center", new String[]{"Ha'aliyah", "Ha'atzmaut", "Ha'or"});
    }


    private static final int REGULAR_USER_LIMIT = 2;
    private static final int BROKER_USER_LIMIT = 5;
    private static final int PHONE_NUMBER_DIGIT = 10;

    public RealEstate(User[] users, Property[] properties, City[] cities) {
        this.users = users;
        this.properties = properties;
        this.cities = cities;
    }

    public static User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public Property[] getProperties() {
        return properties;
    }

    public void setProperties(Property[] properties) {
        this.properties = properties;
    }

    public static City[] getCities() {
        return cities;
    }

    public void setCities(City[] cities) {
        this.cities = cities;
    }


    public String toString() {
        return "RealEstate{" +
                "users=" + Arrays.toString(users) +
                ", properties=" + Arrays.toString(properties) +
                ", cities=" + Arrays.toString(cities) +
                '}';
    }

    static Scanner scanner = new Scanner(System.in);

    //O(n)

    public static boolean checkPhoneNumber(String phoneNumber) {
        boolean checkPhone = true;
        if (phoneNumber.length() != PHONE_NUMBER_DIGIT) {
            checkPhone = false;
            System.out.println(" Your number is too short, enter 10 digits.");
        }
        if (phoneNumber.charAt(0) == '0' && phoneNumber.charAt(1) == '5') {
        } else {
            checkPhone = false;
            System.out.println(" Your number does not start with: 05");
        }
        for (int j = 0; j < phoneNumber.length(); j++) {
            if (!Character.isDigit(phoneNumber.charAt(j))) {
                checkPhone = false;
                System.out.println(" Make sure to write only numbers");
            }

        }
        return checkPhone;
    }

    //O(n)

    static void createUser() {
        String newUser, password, phoneNumber;
        boolean agentOrRegular;
        System.out.println("Enter user name");
        newUser = scanner.nextLine();
        while (!User.checkUserName(newUser)) {
            System.out.println("Username already in use please choose another username");
            newUser = scanner.nextLine();
        }
        User[] temporaryUsers = new User[users.length + 1];
        for (int i = 0; i < users.length; i++) {
            temporaryUsers[i] = users[i];
        }
        users = temporaryUsers;

        System.out.println("Enter your phone number");
        phoneNumber = scanner.nextLine();
        while (!checkPhoneNumber(phoneNumber)) {
            System.out.println("Please enter valid phone");
            phoneNumber = scanner.nextLine();

        }


        System.out.println("Enter your password");
        password = scanner.nextLine();
        while (!User.strongPassword(password)) {
            System.out.println("Please enter a strong password(most use digit and % or $ or _");
            password = scanner.nextLine();
        }

        String answer = "";
        System.out.println("You are broker?(Y/N)");
        answer = scanner.nextLine();
        while (!answer.equalsIgnoreCase("Y") && !answer.equalsIgnoreCase("N")) {
            System.out.println("Please enter valid input");
            answer = scanner.nextLine();
        }
        agentOrRegular = false;
        if (answer.equalsIgnoreCase("Y")) {
            agentOrRegular = true;
        } else if (answer.equalsIgnoreCase("N")) {
            agentOrRegular = false;
        }
        users[users.length - 1] = new User(newUser, password, phoneNumber, agentOrRegular);
        System.out.println("The user " + newUser + " was created successfully!\nMake sure the details you entered are correct");
        System.out.println(users[users.length - 1].toString());
    }
    //O(n)

    static User login() {
        System.out.println("Enter user name");
        String userName = scanner.nextLine();
        System.out.println("Enter password");
        String password = scanner.nextLine();
        for (int i = 0; i < users.length; i++) {
            if (users[i].getUserName().equals(userName) && users[i].getPassword().equals(password)) {
                System.out.println("You have successfully connected");
                return users[i];
            }
        }
        return null;
    }
    //O(n)


    static boolean postNewProperty(User user) {
        boolean check = true;
        int count = 0;
        for (Property property : properties) {
            if (user.getUserName().equals(property.getUser().getUserName())) {
                count++;
            }
        }
        if (!user.isBroker() && count > REGULAR_USER_LIMIT) {
            System.out.println("Sorry, you have reached the maximum number of ads that a regular user can post. (" + count + " ads)");
            check = false;
        } else if (user.isBroker() && count > BROKER_USER_LIMIT) {
            System.out.println("Sorry, you have reached the maximum number of ads that a broker user can post. (" + count + " ads)");
            check = false;
        } else {
            check = true;
        }

        if (check) {
            System.out.println("You can post another ad. You have posted " + count + " ads in total.");
        }
        return check;
    }

    //O(n)

    static void createProperty(User user) {
        Scanner scanner = new Scanner(System.in);
        Property[] temporaryProperty = new Property[properties.length + 1];
        for (int i = 0; i < properties.length; i++) {
            temporaryProperty[i] = properties[i];
        }
        properties = temporaryProperty;
        String cityToPost;
        String streetToPost;
        String answer;
        int floorNumber = 0;
        int rooms;
        int type;
        int houseNumber;
        String price;
        boolean forRent;
        System.out.println("Choose city to the list");
        for (City city : cities) {
            System.out.print(city.getName() + "\t");
        }
        System.out.println();
        cityToPost = scanner.nextLine();
        while (!checkCityExists(cityToPost)) {
            System.out.println("Please select a city that exists in the database");
            cityToPost = scanner.nextLine();
        }
        streetToPost = scanner.nextLine();
        while (!checkStreetExists(cityToPost, streetToPost)) {
            System.out.println("please select street to the list");
            streetToPost = scanner.nextLine();
        }
        System.out.println("What is the type of property (1 – for a regular apartment in an apartment building, 2 – for a penthouse apartment in an apartment building, 3 – for a private house)");
        type = scanner.nextInt();
        while (!Character.isDigit(type) && type < 0 && type > 3) {
            System.out.println("Please enter valid input");
            type = scanner.nextInt();
        }
        if (type == 1 || type == 2) {
            System.out.println("What floor is the property on?");
            floorNumber = scanner.nextInt();
        }
        System.out.println("How many rooms are there in the property?");
        rooms = scanner.nextInt();
        System.out.println("What the house number");
        houseNumber = scanner.nextInt();
        System.out.println("The property for Rent(Y) or for Sale (N)?");
        answer = scanner.nextLine();
        answer = scanner.nextLine();//יש כפילות של scanner כי אם שמים רק אחד הוא מדלג עליו.
        while (!answer.equalsIgnoreCase("Y") && !answer.equalsIgnoreCase("N")) {
            System.out.println("Please enter valid input");
            answer = scanner.nextLine();
        }
        forRent = false;
        if (answer.equalsIgnoreCase("Y")) {
            forRent = true;
        } else if (answer.equalsIgnoreCase("N")) {
            forRent = false;
        }
        System.out.println("What is the price of the property");
        price = scanner.nextLine();
        properties[properties.length - 1] = new Property(cityToPost, streetToPost, rooms, price, type, forRent, houseNumber, floorNumber, user);
        System.out.println("The properties was created successfully!");
        System.out.println(properties[properties.length - 1].toString());
    }
    //O(n^2)

    public static boolean checkCityExists(String cityName) {
        for (City city : cities) {
            if (city.getName().equalsIgnoreCase(cityName)) {
                System.out.println("Choose street to the list");
                for (String street : city.getStreets()) {
                    System.out.println(street);
                }
                return true;
            }
        }
        return false;
    }
    //O(n^2)

    public static boolean checkStreetExists(String cityName, String streetName) {
        for (City city : cities) {
            if (city.getName().equalsIgnoreCase(cityName)) {
                for (String street : city.getStreets()) {
                    if (streetName.equalsIgnoreCase(street)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    //O(n)

    static void removeProperty(User user) {
        int count = 0;
        for (int i = 0; i < properties.length; i++) {
            if (user.getUserName().equals(properties[i].getUser().getUserName())) {
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No property to remove");
        }
        if (count != 0) {
            int index = 1;
            int indexToRemove = 0;
            for (int i = 0; i < properties.length; i++) {
                if (user.getUserName().equals(properties[i].getUser().getUserName())) {
                    System.out.println("This property number " + index);
                    System.out.println(properties[i].toString());
                    index++;
                }

            }
            System.out.println("Choose number property to remove");
            indexToRemove = scanner.nextInt();
            index = 0;
            for (int i = 0; i < properties.length; i++) {
                if (user.getUserName().equals(properties[i].getUser().getUserName())) {
                    index++;
                    if (index == indexToRemove) {
                        properties[i] = null;
                        Property temp[] = new Property[properties.length - 1];
                        for (int j = 0; j < temp.length; j++) {
                            if (properties[j] != null) {
                                temp[j] = properties[j];
                            }
                            properties = temp;
                            System.out.println("The property remove!");
                        }
                    }
                }
            }
        }
    }
    //O(n)

    static void printAllProperties () {
            for (Property post : properties) {
                System.out.println(post.toString());
                System.out.println();
            }
        }
    //O(n)

    static void printPropertiesByUser (User user){
            for (Property post : properties) {
                if (user.getUserName().equals(post.getUser().getUserName())) {
                    System.out.println(post.toString());
                    System.out.println();
                }
            }
        }

    //O(n)

    public static Property[] searchProperties () {
            Scanner scanner = new Scanner(System.in);
            int numOfProperties = 0;
            System.out.println("Is it for rent or sale? (rent/sale)");
            String forRentOrSale = scanner.nextLine();
            boolean isForRent;
            if (forRentOrSale.equals("rent")) {
                isForRent = true;
            } else {
                isForRent = false;
            }
            System.out.println("What type of property do you want? (1-apartment regular/2-penthouse apartment/3-private house) (-999 for any) ");
            int propertyType = scanner.nextInt();
            System.out.println("What is the desired number of rooms? (-999 for any)");
            int desiredRooms = scanner.nextInt();
            System.out.println("What is the desired minimum price? (-999 for any)");
            int minPrice = scanner.nextInt();
            System.out.println("What is the desired maximum price? (-999 for any)");
            int maxPrice = scanner.nextInt();

            Property[] searchResults = new Property[properties.length];
            int k = 0;
            for (int i = 0; i < properties.length; i++) {
                boolean isValid = true;
                if (desiredRooms != -999 && properties[i].getRooms() != desiredRooms) {
                    isValid = false;
                }
                if (minPrice != -999 && Integer.parseInt(properties[i].getPrice()) < minPrice) {
                    isValid = false;
                }
                if (maxPrice != -999 && Integer.parseInt(properties[i].getPrice()) > maxPrice) {
                    isValid = false;
                }
                if (!forRentOrSale.equals("-999") && properties[i].isForRent() != isForRent) {
                    isValid = false;
                }
                if (propertyType != -999 && properties[i].getType() != propertyType) {
                    isValid = false;
                }
                if (isValid) {
                    searchResults[k++] = properties[i];
                    numOfProperties++;
                }
            }
            Property[] finalResults = new Property[numOfProperties];
            int j = 0;
            for (int i = 0; i < searchResults.length; i++) {
                if (searchResults[i] != null) {
                    finalResults[j++] = searchResults[i];
                }
            }
            return finalResults;
        }

    }


