/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.service;

import cl.pojos.PedidoDetalle;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author sistemas
 */
@Stateless
public class PedidoDetalleFacade extends AbstractFacade<PedidoDetalle> implements PedidoDetalleFacadeLocal {

    @PersistenceContext(unitName = "BecarRuzFSaavedra_prueba4-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PedidoDetalleFacade() {
        super(PedidoDetalle.class);
    }

    @Override
    public List<PedidoDetalle> findByTicket(int ticket){
	    Query q = getEntityManager().createNamedQuery("PedidoDetalle.findByIdPedidoDetalle");
	    q.setParameter("idPedidoDetalle", ticket);
	    return q.getResultList();
    }
    
}
