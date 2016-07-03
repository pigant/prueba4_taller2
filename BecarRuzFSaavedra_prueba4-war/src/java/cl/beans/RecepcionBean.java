/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.beans;

import cl.pojos.Cliente;
import cl.pojos.Pedido;
import cl.pojos.PedidoDetalle;
import cl.pojos.Producto;
import cl.service.ClienteFacadeLocal;
import cl.service.PedidoDetalleFacadeLocal;
import cl.service.PedidoFacadeLocal;
import cl.service.ProductoFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author sistemas
 */
@Named(value = "recepcionBean")
@ViewScoped
public class RecepcionBean implements Serializable {

	@EJB
	private PedidoDetalleFacadeLocal pedidoDetalleFacade;

	@EJB
	private PedidoFacadeLocal pedidoFacade;

	@EJB
	private ClienteFacadeLocal clienteFacade;

	@EJB
	private ProductoFacadeLocal productoFacade;

	private String nombre;
	private String rut;
	private Boolean agrandar;
	private Boolean llevar;
	private int productoSelecciondo;
	private int idABorrarDeLaTabla;
	private int total;

	/**
	 * Si es falso es tarjeta de debito true efectivo
	 */
	private Boolean medioPago;

	/**
	 * Productos seleccionados para pedido
	 */
	private List<Producto> seleccionados;

	private Map<Producto, Integer> cantidades;

	/**
	 * Creates a new instance of RecepcionBean
	 */
	public RecepcionBean() {
	}

	@PostConstruct
	public void init() {
		seleccionados = new ArrayList<>();
		cantidades = new HashMap<>();
		medioPago = true;
		agrandar = false;
		llevar = false;
	}

	/**
	 * Agrega un producta a la tabla de pedidos
	 */
	public void agregarProducto() {
		final Producto producto = productoFacade.find(productoSelecciondo);
		//Se agrega a la lista
		if (!cantidades.containsKey(producto)) {
			cantidades.put(producto, 1);
			seleccionados.add(producto);
		} else {
			Integer cantidad = cantidades.get(producto) + 1;
			cantidades.put(producto, cantidad);
		}
		//Actualiza total
		total += producto.getValor();
		//Update a la tabla visual
	}

	/**
	 * Elimina un producto de la tabla visual y de la lista.
	 */
	public void eliminarProducto(int idBorrar) {
		idABorrarDeLaTabla = idBorrar;
		final Producto producto = productoFacade.find(idABorrarDeLaTabla);
		//actualizo mi lista
		Integer cantidad = cantidades.get(producto) - 1;
		if (cantidad > 0) {
			cantidades.put(producto, cantidad);
		} else {
			cantidades.remove(producto);
			seleccionados.remove(producto);
		}
		//Actualiza total
		total -= producto.getValor();
		//actualizo la tabla visual
	}

	/**
	 * Manejo del boton pedir
	 *
	 * @return
	 */
	public String enviarPedido() {
		final Integer rut_i = Integer.valueOf(rut);
		//Comprobar si existe el cliente
		Cliente c = clienteFacade.find(rut_i);
		if (c == null) {
			//Creo el cliente
			c = new Cliente(rut_i, nombre);
			clienteFacade.create(c);
		}
		// Se agrega un pedido
		Pedido p = new Pedido();
		p.setRut(c);
		p.setMedioPago(medioPago ? "Efectivo" : "Tarjeta debito/credito");
		p.setAgrandaBebidaPapas(agrandar);
		p.setParaLlevar(llevar);
		p.setTotal(total);
		//Asumiendo lo mejor, el id se rellena al crear
		pedidoFacade.create(p);
		// Relleno de detalles para el pedido
		for (Producto producto : seleccionados) {
			PedidoDetalle pd = new PedidoDetalle();
			pd.setTicket(p);
			pd.setIdProducto(producto);
			pd.setCantidad(cantidades.get(producto));
			pedidoDetalleFacade.create(pd);
		}
		// Redirige a gracias
		String salida = "gracias.xhtml?ticket=" + p.getTicket() + "&faces-redirect=true";
		return salida;
	}

	public List<Producto> productosDB() {
		return productoFacade.findAll();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public Boolean getAgrandar() {
		return agrandar;
	}

	public void setAgrandar(Boolean agrandar) {
		agrandarPapas();
		this.agrandar = agrandar;
	}

	public void agrandarPapas() {
		agrandar = !agrandar;
		if (agrandar) {
			total += 300;
		} else if (total > 300) {
			total -= 300;
		}
	}

	public Boolean getLlevar() {
		return llevar;
	}

	public void setLlevar(Boolean llevar) {
		this.llevar = llevar;
	}

	public int getProductoSelecciondo() {
		return productoSelecciondo;
	}

	public void setProductoSelecciondo(int productoSelecciondo) {
		this.productoSelecciondo = productoSelecciondo;
	}

	public int getIdABorrarDeLaTabla() {
		return idABorrarDeLaTabla;
	}

	public void setIdABorrarDeLaTabla(int idABorrarDeLaTabla) {
		this.idABorrarDeLaTabla = idABorrarDeLaTabla;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Boolean getMedioPago() {
		return medioPago;
	}

	public void setMedioPago(Boolean medioPago) {
		this.medioPago = medioPago;
	}

	public List<Producto> getSeleccionados() {
		return seleccionados;
	}

	public Map<Producto, Integer> getCantidades() {
		return cantidades;
	}

}
