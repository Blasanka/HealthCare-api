package com.blasanka.helthcare.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.blasanka.helthcare.controllers.AppointmentController;
import com.blasanka.helthcare.models.Appointment;
import com.blasanka.helthcare.resources.beans.AppointmentFilterBean;

@Path("/appointments")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AppointmentResource {

	AppointmentController controller = new AppointmentController();
	
	@GET
	public List<Appointment> getAppointments(@BeanParam AppointmentFilterBean bean) {
		if (bean.getUsername() != null && bean.getUsername().equals("BLA")) {
			if (bean.getSortBy() != null) return controller.getSortedAppointments(bean.getSortBy());
			if (bean.getStart() >= 0 && bean.getSize() > 0) 
				return controller.getAppointmentsPaginated(bean.getStart(), bean.getSize());

			if (bean.getType() != null) return controller.getAppointments(bean.getType(), bean.getId());
			else return controller.getAppointments("appointment", bean.getId());
		} else {
			return new ArrayList<Appointment>();
		}
	}
	
	@GET
	@Path("/{appointId}")
	public Appointment getAppointment(@PathParam("appointId") long id) {
		return controller.getAppointment(id);
	}
	
	@POST
	public Response addAppointment(Appointment appointment, @Context UriInfo info) {
		int count = controller.addAppointment(appointment);
		URI uri = info.getAbsolutePathBuilder().path(String.valueOf(count)).build();
		return Response.created(uri).entity(count).build();
	}

	@PUT
	@Path("/{appointId}")
	public int updateAppointment(@PathParam("appointId") long id, Appointment appointment) {
		appointment.setAppointId(id);
		return controller.updateAppointment(appointment);
	}
	
	@DELETE
	@Path("/{appointId}")
	public int deleteAppointment(@PathParam("appointId") long id) {
		return controller.removeAppointment(id);
	}
	
}
