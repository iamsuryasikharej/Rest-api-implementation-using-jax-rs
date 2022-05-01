package com.surya.rest.messenger.services;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.surya.rest.messenger.database.Database;
import com.surya.rest.messenger.model.*;


public class MessageService {
	
	private static Map<Long,Message> messages=Database.getMessages();
	private static  Map<Long,Profile> profiles=Database.getProfiels();
	public MessageService()
	{
		System.out.println("new message service instance created");
		
	}
	static {
		Message m1= new Message(1,"hello world","surya");
		Comment c1=new Comment(1, "me too", new Date(), "Surya");
		Map<Long,Comment> messagesMap=new HashMap<Long, Comment>();
		messagesMap.put(1L, c1);
		m1.setCommentsMap(messagesMap);
		
	messages.put(1l,m1);
	messages.put(2l,new Message(2,"hello world","surya"));
	}
	static MessageService ms;
	public static MessageService getMessageService()
	{
		if(ms==null) {
			ms=new MessageService();
			return ms;
		}
		else
			return ms;
	}
	
public List<Message> getAllMessages()
{
	return new ArrayList<Message>(messages.values());
	
}


public List<Message> getAllMessagesByYear(int year)
{
	ArrayList<Message> messagesByYear=new ArrayList<Message>();
	Calendar c=Calendar.getInstance();
	for(Message m:messages.values())
	{
		c.setTime(m.getCreated());
		if(c.get(Calendar.YEAR)==year)
		{
			messagesByYear.add(m);
		}
		
	}
	return messagesByYear;
	
}

public List<Message> getAllMesssagesPaginated(int start,int size)
{
	ArrayList<Message> al=new ArrayList<Message>(messages.values());
	return al.subList(start, start+size);
}



public Message getMessage(Long id)
{
	return messages.get(id);
	
}
public Message addMessage(Message m)
{
	m.setId(messages.size()+1);
	messages.put(m.getId(), m);
	return m;
	
}
public Message updateMessage(Message m)  //confused
{
	messages.put(m.getId(), m);
	return m;
	
}
public Message removeMessage(Long id)
{
	return messages.remove(id);
}





}
