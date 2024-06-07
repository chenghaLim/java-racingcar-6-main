package Viewer;

import Connector.ConnectionMaker;
import Connector.MySqlConnectionMaker;
import Controller.UserController;
import lombok.Data;
import model.UserDTO;
import util.ScannerUtil;

import java.util.Scanner;

@Data
public class UserViewer {
    private Scanner SCANNER = new Scanner(System.in);
    private UserDTO logIn;
    private ConnectionMaker connectionMaker;

    public UserViewer() {
        connectionMaker = new MySqlConnectionMaker();
    }

    public void indexShow() {
        String message = "1.로그인 2.회원가입 3.종료";
        while (true) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if (userChoice == 1) {
                auth();
                if (logIn != null) {
                    showMenu();
                }
            } else if (userChoice == 2) {
                register();
            } else if (userChoice == 3) {
                System.out.println("종료");
                break;
            }
        }
    }

    private void auth() {
        String message = "아이디: ";
        String username = ScannerUtil.nextLine(SCANNER, message);
        message = "비밀 번호: ";
        String password = ScannerUtil.nextLine(SCANNER, message);

        UserController userController = new UserController(connectionMaker);
        UserDTO temp = userController.auth(username, password);

        if (temp == null) {
            System.out.println("다시 확인해 주세요. 정보가 없습니다.");
        } else {
            logIn = temp;
        }
    }

    private void register() {
        String message = "아이디: ";
        String username = ScannerUtil.nextLine(SCANNER, message);

        message = "비밀 번호:";
        String password = ScannerUtil.nextLine(SCANNER, message);

        message = "닉네임: ";
        String nickname = ScannerUtil.nextLine(SCANNER, message);

        UserDTO attempt = new UserDTO();
        attempt.setUsername(username);
        attempt.setPassword(password);
        attempt.setNickname(nickname);

        UserController userController = new UserController(connectionMaker);
        if(!userController.register(attempt)){
            System.out.println("잘못 입력하셨습니다.");
        } else {
            System.out.println("정상적으로 회원가입이 되었습니다.");
        }
    }

    private void showMenu() {
        String message = "1. 게시판으로 2. 회원정보 수정 3.로그아웃";
        while (logIn != null) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if (userChoice == 1) {
                BoardViewer boardViewer = new BoardViewer(connectionMaker, SCANNER, logIn);
                boardViewer.showMenu();
            } else if (userChoice == 2) {
                printInfo();
            } else if (userChoice == 3) {
                logIn = null;
                System.out.println("성공적으로 로그아웃 되었습니다.");
                break;
            }
        }
    }

    private void printInfo() {
        System.out.println("========================");
        System.out.println(logIn.getNickname() + " 회원님의 정보");
        System.out.println("------------------------");
        System.out.println("회원 번호: " + logIn.getId());
        System.out.println("회원 아이디: " + logIn.getUsername());
        System.out.println("회원 닉네임: " + logIn.getNickname());
        System.out.println("========================");
        String message = "1. 회원 정보 수정 2. 회원 탈퇴 3. 뒤로 가기";
        int userChoice = ScannerUtil.nextInt(SCANNER, message, 1, 3);

        if (userChoice == 1) {
            update();
        } else if (userChoice == 2) {
            delete();
        }
    }

    private void delete() {
        String message = "정말로 탈퇴하시겠습니까? Y/N";
        String answer = ScannerUtil.nextLine(SCANNER, message);
        if (answer.equalsIgnoreCase("Y")) {
            message = "비밀번호를 입력해주세요.";
            String password = ScannerUtil.nextLine(SCANNER, message);

            if (password.equals(logIn.getPassword())) {
                UserController userController = new UserController(connectionMaker);
                userController.delete(logIn);
                logIn = null;
            }
        }
    }

    private void update() {
        String message = "새로운 닉네임을 입력해주세요.";
        String newNickname = ScannerUtil.nextLine(SCANNER, message);

        message = "새로운 비밀번호를 입력해주세요.";
        String newPassword = ScannerUtil.nextLine(SCANNER, message);

        message = "기존 비밀번호를 입력해주세요.";
        String oldPassword = ScannerUtil.nextLine(SCANNER, message);
        if (oldPassword.equals(logIn.getPassword())) {
            logIn.setNickname(newNickname);
            logIn.setPassword(newPassword);

            UserController userController = new UserController(connectionMaker);
            userController.update(logIn);
        } else {
            System.out.println("기존 비밀번호와 틀려서 회원 정보 수정을 할 수 없습니다.");
        }
    }
}