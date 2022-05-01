package com.surya.rest.messenger.services;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.surya.rest.messenger.database.Database;
import com.surya.rest.messenger.model.Profile;

public class ProfileService {
	static ProfileService ps;
	private ProfileService()
	{
		
	}
	public static ProfileService getInstance()
	{
		if (ps==null)
		{
			ps=new ProfileService();
			return ps;
		}
		else
		{
			return ps;
		}
	}
	private static Map<Long,Profile> profiles=Database.getProfiels();
	static
	{
		profiles.put(1L, new Profile(1, "i_am_ssr", "Surya Sikha", "Rej"));
		profiles.put(2L, new Profile(2, "dog_lover_lavnya", "Lavnya", "Das"));
	}
	public Profile addProfile(Profile p)
	{
		p.setId(profiles.size()+1l);
		return profiles.put(profiles.size()+1l, p);
	}
	
	public Profile getProfile(long id)
	{
		return profiles.get(id);
	}
	public List<Profile> getAllProfiles()
	{
		
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile updateProfile(Profile p)
	{
		
		return profiles.put(p.getId(),p);
		}
}
