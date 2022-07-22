package ro.mycode.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.mycode.model.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserRepoTest {

    private UserRepo userRepo;

    @BeforeEach
    void setUp() {
        userRepo = new UserRepo();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void insertUser() {
        userRepo.insertUser(new User("Alin", "Bosinceanu"));
    }

    @Test
    void deleteUserById() {
        userRepo.deleteUser(4);
    }

    @Test
    void deleteUserByName() {
        userRepo.deleteUser("Andrei", "Andreiescu");
    }

    @Test
    void deleteUserByUser() {
        User user = new User(6, "Alin", "Bosinceanu");
        userRepo.deleteUser(user);
    }

    @Test
    void allUsersTest() {
        List<User> users = userRepo.allUsers();
        assertEquals(3,users.size());
    }

    @Test
    void existsTest() {
        assertTrue(userRepo.exists("Marin", "Preda"));
        assertFalse(userRepo.exists("Alin", "Bosinceanu"));
    }

    @Test
    void updateTest() {
        userRepo.update(new User(3,"Alina","Bosinceanca"));
    }
}