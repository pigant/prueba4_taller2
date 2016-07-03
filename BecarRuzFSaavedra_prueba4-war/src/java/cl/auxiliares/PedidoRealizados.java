/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auxiliares;

/**
 *
 * @author sistemas
 */
public class PedidoRealizados {

	private int id;
	private String descripcion;
	private int valor;

	public PedidoRealizados() {
	}

	public PedidoRealizados(String descripcion, int valor, int id) {
		this.descripcion = descripcion;
		this.valor = valor;
		this.id = id;
	}
	

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
