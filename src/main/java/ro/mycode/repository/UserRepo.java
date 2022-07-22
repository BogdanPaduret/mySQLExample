package ro.mycode.repository;

import ro.mycode.exceptions.NotEnoughIdentifiersException;
import ro.mycode.model.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserRepo extends Repository{

    public void insertUser(User user) {
        String insertTo = "";

        insertTo += "INSERT INTO user ";
        insertTo += "(firstName, lastName)";
        insertTo += "VALUES(";
        insertTo += String.format("'%s', '%s'", user.getFirstName(), user.getLastName());
        insertTo += ")";

        executeStatement(insertTo);
    }

    public void deleteUser(int id) {
        String string = deleteQuery();
        string += String.format("WHERE id = %d", id);

        executeStatement(string);
    }
    public void deleteUser(User user) {
        String delete = deleteQuery();
        delete += String.format("WHERE id = %d AND firstName = '%s' AND lastName = '%s'", user.getUserId(), user.getFirstName(), user.getLastName());

        executeStatement(delete);
    }
    public void deleteUser(String firstName, String lastName) {
        String string = deleteQuery();
        string += String.format("WHERE firstName = '%s' AND lastName = '%s'", firstName, lastName);

        executeStatement(string);
    }

    public List<User> allUsers() {
        String string = selectQuery();
        executeStatement(string);

        List<User> users = new ArrayList<>();

        try {
            ResultSet set = getStatement().getResultSet();
            while (set.next()) {

                int id = set.getInt(1);
                String firstName = set.getString(2);
                String lastName = set.getString(3);

                users.add(new User(id, firstName, lastName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    public boolean exists(String firstName, String lastName) {
        String string = selectQuery();
        executeStatement(string);

        try {
            ResultSet set = getStatement().getResultSet();
            while (set.next()) {
                String firstName2 = set.getString(2);
                String lastName2 = set.getString(3);
                if (firstName.equals(firstName2) && lastName.equals(lastName2)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void update(User user) {
        String string = updateQuery();
        string += String.format("SET firstName = '%s', lastName = '%s' ", user.getFirstName(), user.getLastName());
        string += String.format("WHERE id = %d", user.getUserId());

        executeStatement(string);
    }

    public User get(String firstName, String lastName) {
        String string = selectQuery();
        string += String.format("WHERE firstName = '%s' AND lastName = '%s'", firstName, lastName);

        executeStatement(string);

        List<User> users = new ArrayList<>();

        try {
            ResultSet set = getStatement().getResultSet();
            while (set.next()) {
                User user = getFromSet(set);
                if (user.getFirstName().equals(firstName) && user.getLastName().equals(lastName)) {
                    users.add(user);
                }
            }
            if (users.size() == 1) {
                return users.get(0);
            } else {
                throw new NotEnoughIdentifiersException("Multiple users or no user found matching the given criteria. Try again using user id.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public User get(int id) {
        String string = selectQuery();
        string += String.format("WHERE id = %d", id);

        executeStatement(string);

        User user = null;

        try {
            ResultSet set = getStatement().getResultSet();
            while (set.next()) {
                User bufferedUser = getFromSet(set);
                if (bufferedUser.getUserId() == id) {
                    user = bufferedUser;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    //helpers
    private User getFromSet(ResultSet set) throws Exception {
        int id = set.getInt(1);
        String firstName = set.getString(2);
        String lastName = set.getString(3);

        return new User(id,firstName,lastName);
    }
    private String selectQuery() {
        String string = "";
        string += "SELECT * ";
        string += "FROM user ";
        return string;
    }
    private String deleteQuery() {
        String string = "";
        string += "DELETE FROM user ";

        return string;
    }
    private String updateQuery() {
        String string = "";
        string += "UPDATE user ";
        return string;
    }

}
