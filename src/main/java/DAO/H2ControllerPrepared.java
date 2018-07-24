package DAO;

import DataContainer.Record;
import MainInterface.GuestBookController;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class H2ControllerPrepared implements GuestBookController {

    List<Record> recordList = new ArrayList<>();

    @Override
    public void addRecord(String message){

        try(Connection connection = DriverManager.getConnection("jdbc:h2:mem:mydatabase", "user1", "password1")) {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO POSTS(datePost,postMessage) VALUES (?, ?)");
            Date jdate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String date = sdf.format(jdate);
            Timestamp sqlDate = Timestamp.valueOf(date);

            stmt.setTimestamp(1, sqlDate);
            stmt.setString(2, message);
            stmt.execute();

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    @Override
    public List<Record> getRecords(){
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:mydatabase", "user1", "password1")){
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM POSTS");
            stmt.execute();
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("ID");
                Timestamp datePost = resultSet.getTimestamp("datePost");
                String postMessage = resultSet.getString("postMessage");
                System.out.println(id+":"+datePost+":"+postMessage);
                recordList.add(new Record(id,datePost,postMessage));
            }
            resultSet.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return recordList;
    }
}
