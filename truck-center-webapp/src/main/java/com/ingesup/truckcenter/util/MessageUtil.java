package com.ingesup.truckcenter.util;


import com.ingesup.truckcenter.message.Message;
import com.ingesup.truckcenter.message.MessageType;

/**
 * Created by lopes_f on 3/23/2015.
 * <florian.lopes@outlook.com>
 */
public class MessageUtil {

	private static Message message;

	public static Message returnSuccess(String code) {
		return getMessage().setMessageType(MessageType.SUCCESS).setText(code);
	}

	public static Message returnWarning(String code) {
		return getMessage().setMessageType(MessageType.WARNING).setText(code);
	}

	public static Message returnDanger(String code) {
		return getMessage().setMessageType(MessageType.DANGER).setText(code);
	}

	private static Message getMessage() {
		if (message == null) {
			message = new Message();
		}
		return message;
	}
}
