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

	private String descripcion;
	private int valor;

	public PedidoRealizados() {
	}

	public PedidoRealizados(String descripcion, int valor) {
		this.descripcion = descripcion;
		this.valor = valor;
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

	
	
}
