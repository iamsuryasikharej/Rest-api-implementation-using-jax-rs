package com.surya.rest.messenger.Beans;

import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.QueryParam;

public class MessageBean {
	@QueryParam("year") int year;
	@QueryParam("start") @DefaultValue("-1") int start;
	@QueryParam("size") @DefaultValue("-1") int size;
	{
		System.out.println("message bean instance created");
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}

}
