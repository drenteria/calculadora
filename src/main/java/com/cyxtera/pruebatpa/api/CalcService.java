package com.cyxtera.pruebatpa.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.cyxtera.pruebatpa.core.Calculadora;
import com.cyxtera.pruebatpa.core.Operando;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CalcService {

	private CalcManager calcManager;

	public CalcService() {
		calcManager = CalcManager.getInstance();
	}

	@GET
	@Path("/iniciarSesion")
	public Response iniciarSesion() {
		return Response.ok(calcManager.nuevaCalculadora(), MediaType.APPLICATION_JSON).build();
	}

	@POST
	@Path("/adicionar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response adicionarOperando(Operando operando) {
		
		Calculadora calc = calcManager.buscarCalculadora(operando.getIdSesion());
		if (calc != null) {
			calc.adicionarOperando(operando.getValor());
			return Response.ok(calc, MediaType.APPLICATION_JSON).build();
		}
		else {
			return Response.status(Status.BAD_REQUEST).entity("Calculadora no encontrada").build();
		}
		
		
	}

}
