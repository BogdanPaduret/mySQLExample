package ro.mycode.model;

public class User implements Comparable<User>{

    //instance variables
    private int userId;
    private String firstName;
    private String lastName;


    //constructor
    public User(int userId, String firstName, String lastName) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    //getter
    public int getUserId() {
        return userId;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }


    //setter
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    //override methods
    @Override
    public boolean equals(Object o) {
        return (o instanceof User user) &&
                user.userId == this.userId &&
                user.firstName.equals(this.firstName) &&
                user.lastName.equals(this.lastName);
    }

    @Override
    public String toString() {
        String string = "";

        string += "User ID: " + userId;
        string += "\nFirst Name: " + firstName;
        string += "\nLast Name: " + lastName;

        return string;
    }

    @Override
    public int compareTo(User o) {
        Integer thisId = this.userId;
        Integer oId = o.userId;
        return thisId.compareTo(oId);
    }
}
