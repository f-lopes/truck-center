package truckcenter.com.truckcenter.model;

import java.util.Date;

/**
 * Created by martin on 26/03/15.
 */
public class TruckMessage {
    String sender;
    String message;
    Date date;

    public TruckMessage(String sender, String message, Date date) {
        this.sender = sender;
        this.message = message;
        this.date = date;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
