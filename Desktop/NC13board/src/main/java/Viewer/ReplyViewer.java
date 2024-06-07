package Viewer;

import Connector.ConnectionMaker;
import Controller.ReplyController;
import model.ReplyDTO;
import model.UserDTO;
import util.ScannerUtil;

import java.util.List;
import java.util.Scanner;

public class ReplyViewer {
    private ConnectionMaker connectionMaker;
    private final Scanner SCANNER;
    private UserDTO logIn;

    public ReplyViewer(ConnectionMaker connectionMaker, Scanner scanner, UserDTO logIn) {
        this.connectionMaker = connectionMaker;
        SCANNER = scanner;
        this.logIn = logIn;
    }

    public void printList(int board_id) {
        ReplyController replyController = new ReplyController(connectionMaker);
        List<ReplyDTO> list = replyController.selectAll(board_id);
        if (list.isEmpty()) {
            System.out.println("아직 작성된 댓글이 없습니다.");
            String message = "댓글을 작성하시겠습니까? y/n";
            String answer = ScannerUtil.nextLine(SCANNER, message);
            if (answer.equalsIgnoreCase("y")) {
                insert(board_id);
            }
        } else {
            for (ReplyDTO r : list) {
                System.out.println("==========================================");
                System.out.println(r.getId() +". - " + r.getNickname());
                System.out.println("------------------------------------------");
                System.out.println(r.getContent());
                System.out.println("==========================================");
            }

            ReplyDTO replyDTO = replyController.selectOne(logIn.getId());
            if (logIn.getId() == replyDTO.getWriter_id()) {
                String message = "1, 수정 2. 삭제 3. 뒤로가기";
                int userChoice = ScannerUtil.nextInt(SCANNER, message,1,3);
                if (userChoice == 1) {
                    update(logIn.getId());
                } else if (userChoice == 2) {
                    delete(board_id);
                }
            } else {
                String message = "1.뒤로가기";
                int userChoice = ScannerUtil.nextInt(SCANNER, message, 1, 1);
            }
        }
    }

    private void insert(int board_id) {
        String message = "댓글 :";
        String content = ScannerUtil.nextLine(SCANNER, message);

        ReplyDTO replyDTO = new ReplyDTO();
        replyDTO.setContent(content);
        replyDTO.setNickname(logIn.getNickname());
        replyDTO.setWriter_id(logIn.getId());
        replyDTO.setBoard_id(board_id);

        ReplyController replyController = new ReplyController(connectionMaker);
        replyController.insert(replyDTO);
    }

    private void update(int writer_id) {
        ReplyController replyController = new ReplyController(connectionMaker);
        ReplyDTO replyDTO = replyController.selectOne(writer_id);

        String message = "댓글 :";
        String content = ScannerUtil.nextLine(SCANNER, message);

        replyDTO.setContent(content);
        replyController.update(replyDTO);
    }

    private void delete(int board_id) {
        String answer = ScannerUtil.nextLine(SCANNER, "정말로 삭제 y/n");
        if (answer.equalsIgnoreCase("y")) {
            ReplyController replyController = new ReplyController(connectionMaker);
            ReplyDTO replyDTO = replyController.selectOne(logIn.getId());
            replyController.delete(replyDTO);
            printList(board_id);
        } else {
            printList(board_id);
        }
    }
}
