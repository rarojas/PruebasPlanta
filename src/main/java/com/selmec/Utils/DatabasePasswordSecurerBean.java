/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.Utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

/**
 *
 * @author ricardo
 */
public class DatabasePasswordSecurerBean extends JdbcDaoSupport {

    @Autowired
    private ShaPasswordEncoder passwordEncoder;

    public void secureDatabase() {
        getJdbcTemplate().query("select email, password from usuarios",
                new RowCallbackHandler() {
                    @Override
                    public void processRow(ResultSet rs) throws SQLException {
                        String username = rs.getString(1);
                        String password = rs.getString(2);
                        String encodedPassword = passwordEncoder.encodePassword(password, null);
                        getJdbcTemplate().update(
                                "update usuarios set password = ? where email =  ?", encodedPassword, username);
                        logger.debug(
                                "Updating password for username:" + username + " to: " + encodedPassword);
                    }
                });
    }
}
