/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.beans;

import cl.auxiliares.PedidoRealizados;
import cl.pojos.Cliente;
import cl.pojos.Pedido;
import cl.pojos.PedidoDetalle;
import cl.service.ClienteFacadeLocal;
import cl.service.PedidoDetalleFacadeLocal;
import cl.service.PedidoFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author sistemas
 */
@Named(value = "busquedaBean")
@RequestScoped
public class BusquedaBean {

	@EJB
	private ClienteFacadeLocal clienteFacade;

	@EJB
	private PedidoDetalleFacadeLocal pedidoDetalleFacade;

	@EJB
	private PedidoFacadeLocal pedidoFacade;

	private int rut;

	List<PedidoRealizados> pedidos;


	/**
	 * Creates a new instance of BusquedaBean
	 */
	public BusquedaBean() {
		pedidos = new ArrayList<>();
	}

	public void buscar() {
		// buscamos pedidos
		pedidos = new ArrayList<>();
		Cliente cliente = clienteFacade.find(rut);
		if(cliente == null){
			return;
		}
		List<Pedido> pedidosT = pedidoFacade.findByRut(cliente);
		//Se actualiza la tabla
		for (Pedido p : pedidosT) {
			List<PedidoDetalle> detalles = pedidoDetalleFacade.findByTicket(p);
			String descripcion = "";
			for(PedidoDetalle pd : detalles){
				descripcion += pd.getIdProducto().getDescripcion() + "+";
			}
			descripcion = descripcion.substring(0, descripcion.length()-1);
			PedidoRealizados pr = new PedidoRealizados(descripcion, p.getTotal());
			pedidos.add(pr);
		}
		//Actualiza la tabla
	}

	public int getRut() {
		return rut;
	}

	public void setRut(int rut) {
		this.rut = rut;
	}

	public List<PedidoRealizados> getPedidos() {
		return pedidos;
	}

}
