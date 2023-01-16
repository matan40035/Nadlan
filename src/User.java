public class User {
    private String userName;
    private String password;
    private String PhoneNumber;
    private boolean broker;

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public boolean isBroker() {
        return broker;
    }

    @Override
    public String toString() {
        String isBroker = (isBroker()) ? "Broker" : "Regular";
        return
                "User name= " + userName + "\n"+
                "Password= " + password +"\n" +
                "Phone Number= " + PhoneNumber +"\n" +
                isBroker +" User";
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public void setBroker(boolean broker) {
        this.broker = broker;
    }

    public User(String userName, String password, String phoneNumber, boolean broker) {
        this.userName = userName;
        this.password = password;
        this.PhoneNumber = phoneNumber;
        this.broker = broker;

    }
    //O(n)
    static boolean checkUserName(String user){
        boolean check = true;
        for (User arrayUser: RealEstate.users) {
            if(arrayUser.getUserName().equals(user)){
                check=false;
            }
        }
    return check;
    }

    //O(n)

    public static boolean strongPassword(String password) {
        boolean checkPass = true;
        int counter = 0;
        if (password.length() < 5) {
            checkPass = false;
        }
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                counter = counter + 1;
            }
        }
        if (counter == 0) {
            checkPass = false;
        }
        int counter1 = 0;
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) == '$') {
                counter1++;
            }
            if (password.charAt(i) == '%') {
                counter1++;
            }
            if (password.charAt(i) == '_') {
                counter1++;
            }

        }
        if (counter1 == 0) {
            checkPass = false;
        }

        return checkPass;
    }

}

