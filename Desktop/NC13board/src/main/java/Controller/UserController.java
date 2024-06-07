package Controller;

import Connector.ConnectionMaker;
import com.sun.source.tree.WhileLoopTree;
import model.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserController {
    private Connection connection;

    public UserController(ConnectionMaker connectionMaker) {
        connection = connectionMaker.makeConnection();
    }

    public List<UserDTO> selectAll(){
        List<UserDTO> list = new ArrayList<>();
        String query = "SELECT * FROM user";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                UserDTO userDTO = new UserDTO();
                userDTO.setId(resultSet.getInt("id"));
                userDTO.setUsername(resultSet.getString("username"));
                userDTO.setPassword(resultSet.getString("password"));
                userDTO.setNickname(resultSet.getString("nickname"));

                list.add(userDTO);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    public UserDTO auth(String username, String password) {
        String query = "SELECT * FROM user WHERE username = ? AND password =?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                UserDTO userDTO = new UserDTO();
                userDTO.setId(resultSet.getInt("id"));
                userDTO.setUsername(resultSet.getNString("username"));
                userDTO.setUsername(resultSet.getNString("password"));
                userDTO.setUsername(resultSet.getNString("nickname"));

                return userDTO;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean register(UserDTO userDTO) {
        String query = "INSERT INTO user(username, password, nickname) VALUES(?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,userDTO.getUsername());
            preparedStatement.setString(2,userDTO.getPassword());
            preparedStatement.setString(3,userDTO.getNickname());

            preparedStatement.executeUpdate();

            return true; // -> username 중복일 수 있으니
        } catch (SQLException e) {
            return false;
        }
    }

    public void update(UserDTO userDTO){
        String query = "UPDATE user SET passsword =?, nickname=? WHERE id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,userDTO.getPassword());
            preparedStatement.setString(2, userDTO.getNickname());
            preparedStatement.setInt(3,userDTO.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(UserDTO userDTO){
        String query ="DELETE FROM user WHERE id =?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,userDTO.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean validateUsername(String username) {
        List<UserDTO> list = selectAll();
        for (UserDTO u : list) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                return false;
            }
        }
        return true;
    }

}
