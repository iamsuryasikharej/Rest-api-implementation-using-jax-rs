package com.surya.rest.messenger.resources;

import java.util.List;

import com.surya.rest.messenger.model.Profile;
import com.surya.rest.messenger.services.ProfileService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.xml.bind.annotation.XmlRootElement;

@Path("/profiles")

public class Profiles {
	 ProfileService ps=ProfileService.getInstance();
	@GET
	@Produces(value= {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<Profile> getProfiles()
	{
		return ps.getAllProfiles();
	}
	
	@GET
	@Produces(value= {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("/{profileId}")
	public Profile getProfile(@PathParam("profileId") long id)
	{
		return ps.getProfile(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Profile addProfile(Profile p)
	{
		return ps.addProfile(p);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{profileId}")
	public Profile updateProfile(@PathParam("profileId") long id,Profile p)
	{
		p.setId(id);
		return ps.updateProfile(p);
	}
	
	
	
	
	

}
