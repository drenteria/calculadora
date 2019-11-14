package com.cyxtera.pruebatpa.audit.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cyxtera.pruebatpa.audit.AuditoriaManager;

@Path("/audit")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuditService {

	private AuditoriaManager mgr;
	
	public AuditService() {
		mgr = new AuditoriaManager();
	}
	
	@GET
	@Path("/{id}")
	public Response obtenerLogAuditoria(@PathParam("id") String idSesion) {
		return Response.ok(mgr.obtenerAuditoriaParaSesion(idSesion), MediaType.APPLICATION_JSON).build();
	}

}
