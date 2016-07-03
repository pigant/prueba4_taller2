/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.service;

import cl.pojos.Cliente;
import cl.pojos.Pedido;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sistemas
 */
@Local
public interface PedidoFacadeLocal {

    void create(Pedido pedido);

    void edit(Pedido pedido);

    void remove(Pedido pedido);

    Pedido find(Object id);

    List<Pedido> findAll();

    List<Pedido> findRange(int[] range);

    int count();

	public List<Pedido> findByRut(Cliente rut);
    
}
