package Viewer;

import Connector.ConnectionMaker;
import Controller.BoardController;
import lombok.Data;
import model.BoardDTO;
import model.UserDTO;
import util.ScannerUtil;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class BoardViewer {
    private ConnectionMaker connectionMaker;
    private final Scanner SCANNER;
    private UserDTO logIn;

    public BoardViewer(ConnectionMaker connectionMaker, Scanner scanner, UserDTO logIn) {
        this.connectionMaker = connectionMaker;
        SCANNER = scanner;
        this.logIn = logIn;
    }

    public void showMenu() {
        String message = "1. 글 입력 2. 글 목록 3. 뒤로가기";
        while (true) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if (userChoice == 1) {
                insert();
            } else if (userChoice == 2) {
                printList();
            } else if (userChoice == 3) {
                break;
            }
        }
    }

    private void insert() {
        String message = "제목: ";
        String title = ScannerUtil.nextLine(SCANNER, message);

        message = "글 내용: ";
        String content = ScannerUtil.nextLine(SCANNER,message);

        BoardDTO temp = new BoardDTO();
        temp.setTitle(title);
        temp.setContent(content);

        BoardController boardController = new BoardController(connectionMaker);
        boardController.insert(temp);
    }

    private void printList(){
        BoardController boardController = new BoardController(connectionMaker);
        List<BoardDTO> list = boardController.selectAll();
        if(list.isEmpty()){
            System.out.println("작성된 글이 없습니다.");
        }else{
            for(BoardDTO b : list){
                System.out.printf("%d - %s\n",b.getId(),b.getNickname());
            }
            String message= "자세히 보실 글의 번호나 뒤로 가기 0을 누르시오.";
            int userChoice = ScannerUtil.nextInt(SCANNER,message);

            while(!boardController.validateInput(userChoice)){
                System.out.println("잘못된 입력입니다. 다시 입력하세요");
                userChoice = ScannerUtil.nextInt(SCANNER,message);
            }

            if(userChoice != 0){
                printOne(userChoice);
            }
        }
    }
    private void printOne(int id){
        BoardController boardController = new BoardController(connectionMaker);
        BoardDTO boardDTO = boardController.selectOne(id);
        System.out.println("=====================================");
        System.out.println("제목: "+boardDTO.getTitle());
        System.out.println("글 번호: "+boardDTO.getId());
        System.out.println("글 작성자: "+boardDTO.getNickname());
        System.out.println("======================================");
        System.out.println(boardDTO.getContent());
        System.out.println("=======================================");

        if(logIn.getId()==boardDTO.getWriter_id()){
            String message = "1.수정  2.삭제 3.댓글 보기 4.뒤로";
            int userChoice = ScannerUtil.nextInt(SCANNER,message,1,4);
            if(userChoice==1){
                update(id);
            } else if (userChoice==2) {
                delete(id);
            }else if(userChoice==3){
                ReplyViewer replyViewer = new ReplyViewer(connectionMaker,SCANNER,logIn);
                replyViewer.printList(id);
            } else if (userChoice==4) {
                printList();
            }
        }else{
            String message = "1.뒤로 2.댓글";
            int userChoice = ScannerUtil.nextInt(SCANNER,message,1,2);
            if(userChoice==1){
                printList();
            } else if (userChoice==2) {
                ReplyViewer replyViewer = new ReplyViewer(connectionMaker,SCANNER,logIn);
                replyViewer.printList(id);
            }
        }
    }

    public void update(int id){
        String message = "제목: ";
        String title = ScannerUtil.nextLine(SCANNER, message);

        message = "글 내용: ";
        String content = ScannerUtil.nextLine(SCANNER,message);

        BoardDTO temp = new BoardDTO();
        temp.setId(id);
        temp.setTitle(title);
        temp.setContent(content);

        BoardController boardController = new BoardController(connectionMaker);
        boardController.update(temp);
    }

    public void delete(int id){
        String answer = ScannerUtil.nextLine(SCANNER,"정말로 삭제 y/n");
        if(answer.equalsIgnoreCase("y")){
            BoardController boardController = new BoardController(connectionMaker);
            BoardDTO temp = boardController.selectOne(id);
            boardController.delete(temp);
            printList();
        }else{
            printOne(id);
        }
    }
}
