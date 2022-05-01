package com.surya.rest.messenger.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.surya.rest.messenger.database.Database;
import com.surya.rest.messenger.model.Comment;
import com.surya.rest.messenger.model.Message;
import com.surya.rest.messenger.resources.Messages;

public class CommentService {
	private Map<Long, Message> messages=Database.getMessages();
	
	public List<Comment> getAllComments(long messageId)
	{
		Map<Long,Comment> comments=messages.get(messageId).getCommentsMap();
		return new ArrayList<Comment>(comments.values());
	}
}
