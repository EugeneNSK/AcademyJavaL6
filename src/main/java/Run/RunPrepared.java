package Run;

import DAO.H2BaseConnectPrepared;
import DAO.H2ControllerPrepared;
import MainInterface.GuestBookController;

import java.sql.*;
import java.util.Scanner;

public class RunPrepared {


    public static void main(String[] args) throws ClassNotFoundException {

        Class.forName("org.h2.Driver");
        
        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:mem:mydatabase", "user1", "password1");
            PreparedStatement stmt = connection.prepareStatement("CREATE TABLE POSTS (ID IDENTITY, datePost TIMESTAMP, postMessage VARCHAR)");
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        GuestBookController gbc = new H2ControllerPrepared();

        System.out.println("add - добавить сообщение, list - распечатать все сообщения, exit - выход из программы");
        Scanner sc = new Scanner(System.in);

        while (true){
            System.out.println("Insert new command");
            String cmd = sc.next();

            if("ADD".equals(cmd.toUpperCase())) {
                gbc.addRecord(sc.next());
            } else if ("LIST".equals(cmd.toUpperCase())) {
                System.out.println(gbc.getRecords());
            } else if ("EXIT".equals(cmd.toUpperCase())) {
                System.out.println("Exit program");
                break;
            } else{
                System.out.println("Unknown command. Try again");
                }
        }
    }
}


