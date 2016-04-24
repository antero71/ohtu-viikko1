/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.services;

import ohtu.data_access.InMemoryUserDao;
import ohtu.data_access.UserDao;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Antero Oikkonen
 */
public class AuthenticationServiceTest {

    AuthenticationService service;

    public AuthenticationServiceTest() {
    }

    @Before
    public void setUp() {
        UserDao dao = new InMemoryUserDao();
        service = new AuthenticationService(dao);
    }

    @Test
    public void testLogIn() {
    }

    @Test
    public void testCreateUser() {

        assertTrue(service.createUser("maria", "salainen1nen"));
    }

    @Test
    public void testCreateUserShortName() {

        assertFalse(service.createUser("pe", "salainen1nen"));
    }

    @Test
    public void testCreateUserShortPassword() {

        assertFalse(service.createUser("pekaksiko", "sal"));
    }

    @Test
    public void testCreateUserShortPasswordAndSpezialChar() {

        assertFalse(service.createUser("pekaksiko", "sal?"));
    }

    @Test
    public void testCreateUserPasswordOnlyLetters() {

        assertFalse(service.createUser("liisa", "salainen"));
    }

    @Test
    public void testCreateUserValidPassword() {

        assertTrue(service.createUser("kalle", "salai1nen"));
    }

    @Test
    public void testCreateUserValidPasswordSpecialChar() {

        assertTrue(service.createUser("kalle", "salainen_"));
    }

    @Test
    public void testCreateUserInvalidUsername() {

        assertFalse(service.createUser("kalle1", "salai1nen"));
    }
}
