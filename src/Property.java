class Property {
    private String city;
    private String street;
    private int rooms;
    private String price;
    private int type;
    private boolean forRent;
    private int houseNumber;
    private int floorNumber;
    private User user;

    public Property (String city, String street, int rooms, String price, int type, boolean forRent, int houseNumber, int floorNumber, User user) {
        this.city = city;
        this.street = street;
        this.rooms = rooms;
        this.price = price;
        this.type = type;
        this.forRent = forRent;
        this.houseNumber = houseNumber;
        this.floorNumber = floorNumber;
        this.user = user;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isForRent() {
        return forRent;
    }

    public void setForRent(boolean forRent) {
        this.forRent = forRent;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public User getUser() {
        return user;
    }

    public String toString() {
        String forRentString = (forRent) ? "for rent" : "for sale";
        String isBroker = (user.isBroker()) ? "broker" : "regular";
        String typeHouse="";
        if(type==1){
            typeHouse="regular apartment";
        }
        if(type==2){
            typeHouse="penthouse apartment";
        }
        if (type==3){
            typeHouse="private house";
        }
        String contactInfo = user.getUserName() + " " + user.getPhoneNumber() + " " + isBroker;
        return city + " - " + street + " " + houseNumber + ".\n"
                + typeHouse+" " + forRentString + ": " + rooms + " rooms, floor " + floorNumber + ".\n"
                + "Price: " + price + "$\n"
                + "Contact info: " + contactInfo;
    }


}

