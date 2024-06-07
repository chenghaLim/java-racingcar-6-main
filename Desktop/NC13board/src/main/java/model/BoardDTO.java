package model;

import lombok.Data;

import java.util.Date;

@Data
public class BoardDTO {
    private int id;
    private int writer_id;
    private String title;
    private String content;
    private Date entry_Date;
    private Date modify_Date;
    private String nickname;
}
