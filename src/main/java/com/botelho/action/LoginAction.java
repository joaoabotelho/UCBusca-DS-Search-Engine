package com.botelho.action;

import com.opensymphony.xwork2.ActionSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginAction extends ActionSupport {
    private Integer id;
    private String username;
    private String password;
    private static Logger logger = LoggerFactory.getLogger(LoginAction.class);

    public String execute() {
        logger.info("Info log message");
        String ret = ERROR;
        Connection conn = null;

        try {
            Class.forName ("org.h2.Driver");
     //       logger.info("Trying to connect to database");
            conn = DriverManager.getConnection ("jdbc:h2:~/test", "","");
      //      logger.info("Connected to database");
            String sql = "SELECT id FROM login WHERE";
            sql+=" username = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                id = rs.getInt(1);
                ret = SUCCESS;
            }
        } catch (Exception e) {
            ret = ERROR;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                }
            }
        }
        return ret;
    }

    public String getUser() {
        return username;
    }

    public void setUser(String user) {
        this.username = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
