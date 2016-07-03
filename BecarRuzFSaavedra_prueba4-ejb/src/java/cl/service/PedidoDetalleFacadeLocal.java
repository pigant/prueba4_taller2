/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.service;

import cl.pojos.Pedido;
import cl.pojos.PedidoDetalle;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sistemas
 */
@Local
public interface PedidoDetalleFacadeLocal {

    void create(PedidoDetalle pedidoDetalle);

    void edit(PedidoDetalle pedidoDetalle);

    void remove(PedidoDetalle pedidoDetalle);

    PedidoDetalle find(Object id);

    List<PedidoDetalle> findAll();

    List<PedidoDetalle> findRange(int[] range);

    int count();

	public List<PedidoDetalle> findByTicket(Pedido ticket);
    
}
