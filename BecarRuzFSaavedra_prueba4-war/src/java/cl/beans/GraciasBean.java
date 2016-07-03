/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.beans;

import cl.pojos.Pedido;
import cl.service.PedidoFacadeLocal;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author ignacio
 */
@Named(value = "graciasBean")
@RequestScoped
public class GraciasBean {

	@EJB
	private PedidoFacadeLocal pedidoFacade;
	
	private String nombre;

	private int monto;
	
	private int ticket;

	@PostConstruct
	public void init(){
		Map<String, String> get = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		ticket = Integer.valueOf(get.get("ticket"));
		Pedido p = pedidoFacade.find(ticket);
		nombre = p.getRut().getNombre();
		monto = p.getTotal();
	}

	/**
	 * Creates a new instance of gracias
	 */
	public GraciasBean() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getMonto() {
		return monto;
	}

	public void setMonto(int monto) {
		this.monto = monto;
	}

	public int getTicket() {
		return ticket;
	}

	public void setTicket(int ticket) {
		this.ticket = ticket;
	}
	
}
