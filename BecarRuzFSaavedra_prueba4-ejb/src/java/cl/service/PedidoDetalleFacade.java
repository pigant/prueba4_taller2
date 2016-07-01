/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.service;

import cl.pojos.PedidoDetalle;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
}
