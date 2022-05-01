package com.surya.rest.messenger.resources;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.surya.rest.messenger.model.Comment;
import com.surya.rest.messenger.model.ErrorMessage;
import com.surya.rest.messenger.model.Message;
import com.surya.rest.messenger.services.MessageService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.xml.bind.annotation.XmlRootElement;
@Path("/")
@XmlRootElement
public class CommentResource {
	MessageService ms=MessageService.getMessageService();

	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Comment> getComments(@PathParam("messageId") long id)
	{
		Message m1=ms.getMessage(id);
		if(m1==null)
		{
			throw new WebApplicationException(Status.BAD_GATEWAY);
		}
		Map<Long,Comment> comments=m1.getCommentsMap();
		if(comments.size()==0)
		{
			ErrorMessage er=new ErrorMessage("Not found",401,"https://www.github.com");
			throw new NotFoundException(Response.status(404).entity(er).build());
			
		}
		return new ArrayList<Comment>(comments.values());
	}
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Comment addComment(@PathParam("messageId") long id,Comment comment)
	{
		comment.setCreated(new Date());
		
		Message m1=ms.getMessage(id);
		comment.setId(m1.getCommentsMap().size()+1);
//		Map<Long,Comment> comments=m1.getCommentsMap();
//		comments.put(Long(comments.size()+1), comment);
		m1.getCommentsMap().put(Long.valueOf(m1.getCommentsMap().size()+1), comment);
		System.out.println(new Date());
		return comment;
		
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{commentId}")
	public Comment getCommentsByCommentNumber(@PathParam("commentId") long cId,@PathParam("messageId") long mId)
	{
		Message m1=ms.getMessage(mId);
		Map<Long,Comment> comments=m1.getCommentsMap();
		Comment comment=comments.get(cId);
		return comment;
		
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{commentId}")
	public Comment updateComment(@PathParam("commentId") long cId,@PathParam("messageId") long mId,Comment comment)
	{
		Message m1=ms.getMessage(mId);
		Map<Long,Comment> comments=m1.getCommentsMap();
		comments.put(cId, comment);
		return comment;
		
	}
	
}
