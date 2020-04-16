package com.blasanka.helthcare.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.blasanka.helthcare.controllers.UserController;
import com.blasanka.helthcare.models.User;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

	UserController controller = new UserController();
	
	@GET
	public List<User> getUsers() {
		return controller.getUsers();
	}
	
	@GET
	@Path("/{userId}")
	public User getUser(@PathParam("userId") long id) {
		return controller.getUser(id);
	}
	
	@POST
	public User addUser(User user) {
		return controller.addUser(user);
	}

	@PUT
	@Path("/{userId}")
	public User updateUser(@PathParam("userId") long id, User user) {
		user.setUserId(id);
		return controller.updateUser(user);
	}
	
	@DELETE
	@Path("/{userId}")
	public void deleteUser(@PathParam("userId") long id) {
		controller.removeUser(id);
	}
	
}
