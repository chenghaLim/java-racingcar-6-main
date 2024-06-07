package Controller;

import Connector.ConnectionMaker;
import model.BoardDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardController {
    private Connection connection;

    public BoardController(ConnectionMaker connectionMaker) {
        connection = connectionMaker.makeConnection();
    }

    public List<BoardDTO> selectAll() {
        List<BoardDTO> list = new ArrayList<>();
        String query = "SELECT * FROM board INNER JOIN user ON board.writer_id = user.id";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                BoardDTO boardDTO = new BoardDTO();
                boardDTO.setId(resultSet.getInt("board.id"));
                boardDTO.setWriter_id(resultSet.getInt("writer_id"));
                boardDTO.setTitle(resultSet.getString("title"));
                boardDTO.setContent(resultSet.getString("content"));
                boardDTO.setEntry_Date(resultSet.getTimestamp("entry_date"));
                boardDTO.setModify_Date(resultSet.getTimestamp("modify_date"));
                boardDTO.setNickname(resultSet.getString("nickname"));

                list.add(boardDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public BoardDTO selectOne(int id) {
        BoardDTO boardDTO = null;
        String query = "SELECT * FROM board INNER JOIN user ON board.writer_id = user.id WHERE board.id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                boardDTO = new BoardDTO();
                boardDTO.setId(resultSet.getInt("board.id"));
                boardDTO.setWriter_id(resultSet.getInt("writer_id"));
                boardDTO.setTitle(resultSet.getString("title"));
                boardDTO.setContent(resultSet.getString("content"));
                boardDTO.setNickname(resultSet.getString("nickname"));
                boardDTO.setEntry_Date(resultSet.getTimestamp("entry_date"));
                boardDTO.setModify_Date(resultSet.getTimestamp("modify_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return boardDTO;
    }

    public void insert(BoardDTO boardDTO) {
        String query = "INSERT INTO board(title, content, writer_id) VALUES(?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, boardDTO.getTitle());
            preparedStatement.setString(2, boardDTO.getContent());
            preparedStatement.setInt(3,boardDTO.getWriter_id());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(BoardDTO boardDTO) {
        String query = "UPDATE board SET title =?, content=? ,modify_date =NOW() WHERE id =?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, boardDTO.getTitle());
            preparedStatement.setString(2, boardDTO.getContent());
            preparedStatement.setInt(3, boardDTO.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(BoardDTO boardDTO) {
        String query = "DELETE FROM board WHERE id =?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, boardDTO.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean validateInput(int input){
        if(input == 0){
            return true;
        }
        List<BoardDTO> list = new ArrayList<>();
        String query ="SELECT id FROM board";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                BoardDTO boardDTO = new BoardDTO();
                boardDTO.setId(resultSet.getInt("id"));

                list.add(boardDTO);
            }

            for(BoardDTO b : list){
                if(b.getId()==input){
                    return true;
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}