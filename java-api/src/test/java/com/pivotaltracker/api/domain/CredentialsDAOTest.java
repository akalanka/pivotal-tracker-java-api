/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pivotaltracker.api.domain;

import com.pivotaltracker.api.exceptions.TrackerException;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 *
 * @author eivar
 */
public class CredentialsDAOTest {

    @Test()
    @Parameters({"username", "password"})
    public void getUserTokenTest(String username, String passwod) throws TrackerException {
        String token = new CredentialsDAO().getUserToken(username, passwod);
        Assert.assertNotSame(token, " ");
    }
}
