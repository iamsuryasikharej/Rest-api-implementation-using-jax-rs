package com.surya.rest.messenger.database;
import java.util.*;
import com.surya.rest.messenger.model.*;
// This class is mocking a the persistence layer 
public class Database {
 private static Map<Long,Message> messages=new HashMap<>();
 private static Map<Long,Profile> profiles=new HashMap<>();
 {
	 System.out.println("new DataBase instance created");
 }
 
 public static Map<Long,Message> getMessages()
 {
	 return messages;
 }
 public static Map<Long,Profile> getProfiels()
 {
	 return profiles;
 }
}
