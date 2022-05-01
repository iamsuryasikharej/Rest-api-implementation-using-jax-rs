package com.surya.rest.messenger.resources;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import com.surya.rest.messenger.services.*;
import com.surya.rest.messenger.Beans.MessageBean;
import com.surya.rest.messenger.exceptions.ContentNotFoundException;
import com.surya.rest.messenger.model.*;

import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriInfo;

@Path("messages")// top level url pattern
@Consumes(MediaType.APPLICATION_JSON)
@Produces(value= {MediaType.APPLICATION_JSON})
public class Messages {
	MessageService ms=MessageService.getMessageService();
	
	
	
	
	
	
	
	@GET
	public List<Message> getMessagesInJson(@BeanParam MessageBean b)
	{
		if(b.getYear()>0)
		{
			
			return ms.getAllMessagesByYear(b.getYear());
		}
		else if(b.getStart()>=0 && b.getSize()>=0)
		{
			return ms.getAllMesssagesPaginated(b.getStart(), b.getSize());
		}
		
			return ms.getAllMessages();
	}
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Message> getMessagesInXml(@BeanParam MessageBean b)
	{
		if(b.getYear()>0)
		{
			
			return ms.getAllMessagesByYear(b.getYear());
		}
		else if(b.getStart()>=0 && b.getSize()>=0)
		{
			return ms.getAllMesssagesPaginated(b.getStart(), b.getSize());
		}
		
			return ms.getAllMessages();
	}
	
	
	
	
	
	@GET
	@Path("/{messageId}")// second level url pattern----> messages/1 or messages/2 etc.
	public Message test(@PathParam("messageId") String messageId, @Context UriInfo uriInfo)
	{
		String uriSelf;
		String uriComment;
		String uriProfile;
		
		Message m=ms.getMessage(Long.parseLong(messageId));
		System.out.println(m);
		
		if (m==null)
		{
			
			throw new ContentNotFoundException("Message with messageid"+messageId+"not found please fuck off");
		} else
			uriSelf = getUriForSelf(uriInfo, m);
			uriComment = getUriComments(uriInfo, m);
			uriProfile = getUriForUserProfile(uriInfo, m);
			m.addLinks("self",uriSelf);
		    m.addLinks("comments",uriComment);
		    m.addLinks("profile",uriProfile);
			return m;
		
	}





	private String getUriForUserProfile(UriInfo uriInfo, Message m) {
		String uriProfile;
		uriProfile=uriInfo.getBaseUriBuilder().path(Profiles.class).path(m.getAuthor()).build().toString();
		return uriProfile;
	}





	private String getUriComments(UriInfo uriInfo, Message m) {
		String uriComment;
		uriComment = uriInfo.getBaseUriBuilder().path(Messages.class)
				                                .path(Messages.class, "commentResourceHandler")
				                                .resolveTemplate("messageId", m.getId())
				                                .build().toString();
		return uriComment;
	}









	private String getUriForSelf(UriInfo uriInfo, Message m) {
		String uriSelf;
		uriSelf=uriInfo.getBaseUriBuilder().path(Messages.class).path(String.valueOf(m.getId())).build().toString();
		return uriSelf;
	}
	
	
	
	@POST

	public Response addMessage(@Context UriInfo uriInfo,Message m) throws URISyntaxException
	{
		ms.addMessage(m);
		System.out.println(uriInfo.getAbsolutePath());
		String uri=uriInfo.getAbsolutePath().toString()+String.valueOf(m.getId());

		return Response.status(Status.CREATED).location(new URI(uri)).entity(m).build();
	
	
	}
	
	
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") String messageId,Message m)
	{
		m.setId(Long.parseLong(messageId));
		return ms.updateMessage(m);
	}
	
	
	@DELETE

	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") String messageId)
	{
		
		 ms.removeMessage(Long.parseLong(messageId));
	}
	
	
	@Path("/{messageId}/comments")
	public CommentResource commentResourceHandler()
	{
		return new CommentResource();
	}

}
