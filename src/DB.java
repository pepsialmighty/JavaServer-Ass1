import java.util.*;

public class DB {

	private static Vector<Message> messageList = new Vector();

	public static Vector<Message> getMessageDatabase() {
		return messageList;
	}

	public static void setMessageDatabase(Message messageObject) {
		messageList.addElement(messageObject);
	}
}
