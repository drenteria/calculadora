package com.drenteria.calculadora.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.drenteria.calculadora.audit.AuditoriaManager;
import com.drenteria.calculadora.core.Calculadora;
import com.drenteria.calculadora.exceptions.OperacionException;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CalcService {

	private CalcManager calcManager;
	
	private AuditoriaManager auditoria;

	public CalcService() {
		calcManager = CalcManager.getInstance();
		auditoria = new AuditoriaManager();
	}

	@GET
	@Path("/iniciarSesion")
	public Response iniciarSesion() {
		Calculadora laCalculadora = calcManager.nuevaCalculadora();
		auditoria.registrarAuditoriaCalc(laCalculadora.getIdSesion(), "iniciarSesion", "");
		return Response.ok(laCalculadora, MediaType.APPLICATION_JSON).build();
	}

	@POST
	@Path("/adicionar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response adicionarOperando(Operando operando) {
		Calculadora calc = calcManager.buscarCalculadora(operando.getIdSesion());
		if (calc != null) {
			calc.adicionarOperando(operando.getValor());
			auditoria.registrarAuditoriaCalc(operando.getIdSesion(), "adicionar", operando.getValor());
			return Response.ok(calc, MediaType.APPLICATION_JSON).build();
		} else {
			return Response.status(Status.BAD_REQUEST).entity("Calculadora no encontrada").build();
		}
	}
	
	@POST
	@Path("/ejecutar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ejecutarOperacion(Operacion operacion) {
		Calculadora calc = calcManager.buscarCalculadora(operacion.getIdSesion());
		if (calc != null) {
			try {
				Double resultado = calc.ejecutarOperacion(operacion.getOperacion());
				auditoria.registrarAuditoriaCalc(calc.getIdSesion(), operacion.getOperacion(), resultado.toString());
			} catch (OperacionException e) {
				return Response.status(Status.BAD_REQUEST).entity("Operacion no permitida").build();
			}
			return Response.ok(calc, MediaType.APPLICATION_JSON).build();
		} else {
			return Response.status(Status.BAD_REQUEST).entity("Calculadora no encontrada").build();
		}
	}
	
	@DELETE
	@Path("/finalizar/{id}")
	public Response eliminarCalculadora(@PathParam("id") String idSesion) {
		if(calcManager.removerCalculadora(idSesion)) {
			auditoria.registrarAuditoriaCalc(idSesion, "finalizar", "");
			return Response.ok("OK", MediaType.APPLICATION_JSON).build();
		}
		else
			return Response.ok("No encontrada", MediaType.APPLICATION_JSON).build();
	}

}
