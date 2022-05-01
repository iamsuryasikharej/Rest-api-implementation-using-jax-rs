package com.surya.rest.messenger.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import javax.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Message {
	private long id;
	private String messgae;
	private Date created;
	private String author;
	private Map<Long,Comment> commentsMap=new HashMap<>();
	private List<Links> links=new ArrayList<Links>();
	public Map<Long, Comment> getCommentsMap() {
		return commentsMap;
	}
	public void setCommentsMap(Map<Long, Comment> commentsMap) {
		this.commentsMap = commentsMap;
	}
	public Message()
	{
		
	}
	public List<Links> getLinks() {
		return links;
	}
	public void setLinks(List<Links> links) {
		this.links = links;
	}
	public Message(long id, String messgae, String author) {
		this.id = id;
		this.messgae = messgae;
		this.created = new Date();
		this.author = author;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMessgae() {
		return messgae;
	}
	
	public void setMessgae(String messgae) {
		this.messgae = messgae;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void addLinks(String rel,String url)
	{
		Links l=new Links();
		l.setUrl(url);
		l.setRel(rel);
		links.add(l);
		
		
	}
}
