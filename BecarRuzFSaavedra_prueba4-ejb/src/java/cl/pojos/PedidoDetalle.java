/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pojos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sistemas
 */
@Entity
@Table(name = "pedido_detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PedidoDetalle.findAll", query = "SELECT p FROM PedidoDetalle p"),
    @NamedQuery(name = "PedidoDetalle.findByIdPedidoDetalle", query = "SELECT p FROM PedidoDetalle p WHERE p.idPedidoDetalle = :idPedidoDetalle"),
    @NamedQuery(name = "PedidoDetalle.findByTicket", query = "SELECT p FROM PedidoDetalle p WHERE p.ticket = :ticket"),
    @NamedQuery(name = "PedidoDetalle.findByCantidad", query = "SELECT p FROM PedidoDetalle p WHERE p.cantidad = :cantidad")})
public class PedidoDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pedido_detalle")
    private Integer idPedidoDetalle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    @JoinColumn(name = "ticket", referencedColumnName = "ticket")
    @ManyToOne(optional = false)
    private Pedido ticket;
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    @ManyToOne(optional = false)
    private Producto idProducto;

    public PedidoDetalle() {
    }

    public PedidoDetalle(Integer idPedidoDetalle) {
        this.idPedidoDetalle = idPedidoDetalle;
    }

    public PedidoDetalle(Integer idPedidoDetalle, int cantidad) {
        this.idPedidoDetalle = idPedidoDetalle;
        this.cantidad = cantidad;
    }

    public Integer getIdPedidoDetalle() {
        return idPedidoDetalle;
    }

    public void setIdPedidoDetalle(Integer idPedidoDetalle) {
        this.idPedidoDetalle = idPedidoDetalle;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Pedido getTicket() {
        return ticket;
    }

    public void setTicket(Pedido ticket) {
        this.ticket = ticket;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPedidoDetalle != null ? idPedidoDetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PedidoDetalle)) {
            return false;
        }
        PedidoDetalle other = (PedidoDetalle) object;
        if ((this.idPedidoDetalle == null && other.idPedidoDetalle != null) || (this.idPedidoDetalle != null && !this.idPedidoDetalle.equals(other.idPedidoDetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.entity.PedidoDetalle[ idPedidoDetalle=" + idPedidoDetalle + " ]";
    }
    
}
