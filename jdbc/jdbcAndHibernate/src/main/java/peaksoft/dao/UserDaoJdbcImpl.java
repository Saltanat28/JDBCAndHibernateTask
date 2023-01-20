package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {
    Connection cn;


    public UserDaoJdbcImpl() {
        this.cn=Util.getConnection();
    }

    public void createUsersTable() {
        String sql = "create table users(id serial primary key, " +
                "name varchar, " +
                "last_name varchar," +
                " age int)";
        try (Statement statement = cn.createStatement()){
            statement.execute(sql);
            System.out.println(" Successfully Create table");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public void dropUsersTable() {
        String sql = "drop table users";
        try(Statement statement= cn.createStatement()){
            statement.executeQuery(sql);
            System.out.println("Table successfully dropped.");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "insert into users(name, last_name, age) values(?,?,?)";
        try(Connection connection = Util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            System.out.println(name+" User Successfully saved!");
        }catch (SQLException e){
            System.out.println("not save");
        }




    }

    public void removeUserById(long id) {
        String sql = "delete  from users where  id =  ? ";
        try( Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1, id);
           preparedStatement.execute();
            System.out.println("User removed by id!");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public List<User> getAllUsers() {
        List<User> users= new ArrayList<>();
        String sql= "select * from users";
        try( Statement statement= cn.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
            users.add(new User(resultSet.getLong("id")
                    ,resultSet.getString("name"),
                    resultSet.getString("last_name"),
                    resultSet.getByte("age")));
            }
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }

        return users;
    }

    public void cleanUsersTable() {
        String sql = "truncate table users;";

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
            System.out.println(" Table cleaned!");
        } catch (SQLException e) {
            System.out.println("Table  cleaned successfully.");
        }
    }
}