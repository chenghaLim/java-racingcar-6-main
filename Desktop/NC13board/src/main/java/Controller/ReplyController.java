package Controller;

import Connector.ConnectionMaker;
import model.BoardDTO;
import model.ReplyDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReplyController {
    private Connection connection;

    public ReplyController(ConnectionMaker connectionMaker){
        connection = connectionMaker.makeConnection();
    }

    public List<ReplyDTO> selectAll(int board_id){
        List<ReplyDTO> list =new ArrayList<>();
        String query = "SELECT * FROM reply INNER JOIN user ON reply.writer_id = user.id WHERE reply.board_id =?";
        try{
            PreparedStatement preparedStatement= connection.prepareStatement(query);
            preparedStatement.setInt(1,board_id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ReplyDTO replyDTO = new ReplyDTO();
                replyDTO.setId(resultSet.getInt("id"));
                replyDTO.setWriter_id(resultSet.getInt("writer_id"));
                replyDTO.setBoard_id(resultSet.getInt("board_id"));
                replyDTO.setContent(resultSet.getString("content"));
                replyDTO.setNickname(resultSet.getString("user.nickname"));
                replyDTO.setEntry_Date(resultSet.getTimestamp("entry_date"));
                replyDTO.setModify_Date(resultSet.getTimestamp("modify_date"));

                list.add(replyDTO);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public void insert(ReplyDTO replyDTO){
        String query = "INSERT INTO reply(content,writer_id,board_id) VALUES(?,?,?)";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,replyDTO.getContent());
            preparedStatement.setInt(2,replyDTO.getWriter_id());
            preparedStatement.setInt(3,replyDTO.getBoard_id());

            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void update(ReplyDTO replyDTO){
        String query ="UPDATE reply SET content =?, modify_date = NOW() WHERE writer_id =? ";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,replyDTO.getContent());
            preparedStatement.setInt(2,replyDTO.getWriter_id());

            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(ReplyDTO replyDTO){
        String query ="DELETE FROM reply WHERE writer_id=?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,replyDTO.getWriter_id());

            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ReplyDTO selectOne(int writer_id){
        ReplyDTO replyDTO = null;
        String query = "SELECT * FROM reply INNER JOIN user ON reply.writer_id = user.id WHERE writer_id =?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,writer_id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                replyDTO=new ReplyDTO();
                replyDTO.setId(resultSet.getInt("id"));
                replyDTO.setWriter_id(resultSet.getInt("writer_id"));
                replyDTO.setBoard_id(resultSet.getInt("board_id"));
                replyDTO.setContent(resultSet.getString("content"));
                replyDTO.setNickname(resultSet.getString("nickname"));
                replyDTO.setEntry_Date(resultSet.getTimestamp("entry_date"));
                replyDTO.setModify_Date(resultSet.getTimestamp("modify_date"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return replyDTO;
    }

    public boolean validateInput(int input){
        if(input == 0){
            return true;
        }
        List<ReplyDTO> list = new ArrayList<>();
        String query ="SELECT id FROM reply";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                ReplyDTO replyDTO = new ReplyDTO();
                replyDTO.setId(resultSet.getInt("id"));

                list.add(replyDTO);
            }

            for(ReplyDTO r : list){
                if(r.getId()==input){
                    return true;
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
