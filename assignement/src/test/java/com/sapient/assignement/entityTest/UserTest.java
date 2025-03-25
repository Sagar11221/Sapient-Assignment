package com.sapient.assignement.entityTest;

import com.sapient.assignement.api.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private User user;

    @BeforeEach
    public void setup() {
        user = new User();
    }

    @Test
    public void testSetAndGetId() {
        user.setId(1L);
        assertEquals(1L, user.getId(), "ID should be 1");
    }

    @Test
    public void testSetAndGetFirstName() {
        user.setFirstName("John");
        assertEquals("John", user.getFirstName(), "First name should be 'John'");
    }

    @Test
    public void testSetAndGetLastName() {
        user.setLastName("Doe");
        assertEquals("Doe", user.getLastName(), "Last name should be 'Doe'");
    }

    @Test
    public void testSetAndGetAge() {
        user.setAge(30);
        assertEquals(30, user.getAge(), "Age should be 30");
    }

    @Test
    public void testSetAndGetGender() {
        user.setGender("Male");
        assertEquals("Male", user.getGender(), "Gender should be 'Male'");
    }

    @Test
    public void testSetAndGetEmail() {
        user.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", user.getEmail(), "Email should be 'john.doe@example.com'");
    }

    @Test
    public void testSetAndGetPhone() {
        user.setPhone("1234567890");
        assertEquals("1234567890", user.getPhone(), "Phone number should be '1234567890'");
    }

    @Test
    public void testSetAndGetUsername() {
        user.setUsername("johndoe");
        assertEquals("johndoe", user.getUsername(), "Username should be 'johndoe'");
    }

    @Test
    public void testSetAndGetPassword() {
        user.setPassword("password123");
        assertEquals("password123", user.getPassword(), "Password should be 'password123'");
    }

    @Test
    public void testSetAndGetBirthDate() {
        user.setBirthDate("1990-01-01");
        assertEquals("1990-01-01", user.getBirthDate(), "Birthdate should be '1990-01-01'");
    }

    @Test
    public void testSetAndGetImage() {
        user.setImage("image.jpg");
        assertEquals("image.jpg", user.getImage(), "Image filename should be 'image.jpg'");
    }

    @Test
    public void testSetAndGetBloodGroup() {
        user.setBloodGroup("O+");
        assertEquals("O+", user.getBloodGroup(), "Blood group should be 'O+'");
    }

    @Test
    public void testSetAndGetHeight() {
        user.setHeight(180.5);
        assertEquals(180.5, user.getHeight(), "Height should be 180.5");
    }
}

