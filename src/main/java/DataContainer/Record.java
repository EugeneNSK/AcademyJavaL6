package DataContainer;

import java.sql.Timestamp;

public class Record {
    int id;
    Timestamp Date;
    String message;

    public Record(int id, Timestamp Date, String message) {
        this.id = id;
        this.Date = Date;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", Date='" + Date + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}



