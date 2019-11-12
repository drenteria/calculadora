package com.cyxtera.pruebatpa.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cyxtera.pruebatpa.core.Calculadora;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CalcService {

	private Calculadora calculadora;
	
	public CalcService() {
		calculadora = new Calculadora();
	}
	
	@GET
	@Path("/iniciarSesion")
	public Response iniciarSesion() {
		return Response.ok(calculadora.getIdSesion(), MediaType.APPLICATION_JSON).build();
	}
	
}
