/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lytrieudat.ConnectData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lytri
 */
public class ConnectJDBC {

    String url = "jdbc:mysql://localhost:3306/studentmn";
    String user = "root";
    String password = "123456";

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public List<Student> findAll() {
        List<Student> studentList = new ArrayList();
        String query = "SELECT * FROM student";
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Student st = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getInt("gender"),
                        rs.getString("major"),
                        rs.getFloat("score")
                );
                studentList.add(st);
            }
        } catch (Exception e) {
        }
        return studentList;
    }

    public void insert(Student st) {
        String query = "INSERT INTO studentmn.student(name,age,gender,major,score) VALUES(?,?,?,?,?)";
        try {
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, st.getName());
            pstmt.setInt(2, st.getAge());
            pstmt.setInt(3, st.getGender());
            pstmt.setString(4, st.getMajor());
            pstmt.setFloat(5, st.getScore());
            pstmt.execute();
        } catch (Exception e) {
        }
    }

    public void delete(Student st) {
        String query = "DELETE FROM student WHERE name='" + st.getName() + "'";
        try {
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.execute();
        } catch (Exception e) {
        }
    }

    public void update(Student st) {
        String query = "UPDATE studentmn.student set name=?,age=?,gender=?,major=?,score=? WHERE id='" + st.getId() + "'";
        try {
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, st.getName());
            pstmt.setInt(2, st.getAge());
            pstmt.setInt(3, st.getGender());
            pstmt.setString(4, st.getMajor());
            pstmt.setFloat(5, st.getScore());
            pstmt.executeUpdate();
        } catch (Exception e) {
        }
    }

    public List<Student> findByName(Student s) {
        List<Student> studentList = new ArrayList<>();
        String query = "SELECT * FROM student WHERE name='" + s.getName() + "'";
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Student st = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getInt("gender"),
                        rs.getString("major"),
                        rs.getFloat("score")
                );
                studentList.add(st);
            }
        } catch (Exception e) {
        }
        return studentList;
    }
}
