package com.muthyatechnology.mockito.test;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.muthyatechnology.mockito.main.AuthenticatorApplication;
import com.muthyatechnology.mockito.main.AuthenticatorInterface;

public class AuthenticatorApplicationTest {

    @Test
    public void testAuthenticate() {
        AuthenticatorInterface authenticatorMock;
        AuthenticatorApplication authenticator;
        String username = "JavaCodeGeeks";
        String password = "unsafePassword";
        
        authenticatorMock = Mockito.mock(AuthenticatorInterface.class);
        authenticator = new AuthenticatorApplication(authenticatorMock);
        
        when(authenticatorMock.authenticateUser(username, password))
            .thenReturn(false);
        //verify(authenticatorMock).authenticateUser(username, "not the original password");

        boolean actual = authenticator.authenticate(username, password);
        
        assertFalse(actual);
    }
}