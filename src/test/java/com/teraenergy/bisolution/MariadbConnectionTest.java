package com.teraenergy.bisolution;

import java.sql.*;

import org.junit.jupiter.api.Test;

public class MariadbConnectionTest {
    // MySQL Connector 의 클래스. DB 연결 드라이버 정의
    private static final String DRIVER = "org.mariadb.jdbc.Driver";
    // DB 경로
    private static final String URL = "jdbc:mariadb://14.49.44.142:3308/bi_solution?characterEncoding=UTF-8&serverTimezone=UTC";
    private static final String USER = "tera_bi";
    private static final String PASSWORD = "tera202206";

    @Test
    public void testConnection() throws Exception {
        // DBMS에게 DB 연결 드라이버의 위치를 알려주기 위한 메소드
        Class.forName(DRIVER);
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
