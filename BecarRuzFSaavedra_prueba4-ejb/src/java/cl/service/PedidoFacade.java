/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.service;

import cl.pojos.Cliente;
import cl.pojos.Pedido;
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
public class PedidoFacade extends AbstractFacade<Pedido> implements PedidoFacadeLocal {

    @PersistenceContext(unitName = "BecarRuzFSaavedra_prueba4-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PedidoFacade() {
        super(Pedido.class);
    }

    @Override
    public List<Pedido> findByRut(Cliente rut){
	    Query q = getEntityManager().createNamedQuery("Pedido.findByRut");
	    q.setParameter("rut", rut);
	    return q.getResultList();
    }
    
}
