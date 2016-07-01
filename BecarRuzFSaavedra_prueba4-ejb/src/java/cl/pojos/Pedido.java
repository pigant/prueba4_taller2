/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pojos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sistemas
 */
@Entity
@Table(name = "pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p"),
    @NamedQuery(name = "Pedido.findByTicket", query = "SELECT p FROM Pedido p WHERE p.ticket = :ticket"),
    @NamedQuery(name = "Pedido.findByMedioPago", query = "SELECT p FROM Pedido p WHERE p.medioPago = :medioPago"),
    @NamedQuery(name = "Pedido.findByAgrandaBebidaPapas", query = "SELECT p FROM Pedido p WHERE p.agrandaBebidaPapas = :agrandaBebidaPapas"),
    @NamedQuery(name = "Pedido.findByParaLlevar", query = "SELECT p FROM Pedido p WHERE p.paraLlevar = :paraLlevar"),
    @NamedQuery(name = "Pedido.findByTotal", query = "SELECT p FROM Pedido p WHERE p.total = :total")})
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ticket")
    private Integer ticket;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "medio_pago")
    private String medioPago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "agranda_bebida_papas")
    private boolean agrandaBebidaPapas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "para_llevar")
    private boolean paraLlevar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total")
    private int total;
    @JoinColumn(name = "rut", referencedColumnName = "rut")
    @ManyToOne(optional = false)
    private Cliente rut;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ticket")
    private List<PedidoDetalle> pedidoDetalleList;

    public Pedido() {
    }

    public Pedido(Integer ticket) {
        this.ticket = ticket;
    }

    public Pedido(Integer ticket, String medioPago, boolean agrandaBebidaPapas, boolean paraLlevar, int total) {
        this.ticket = ticket;
        this.medioPago = medioPago;
        this.agrandaBebidaPapas = agrandaBebidaPapas;
        this.paraLlevar = paraLlevar;
        this.total = total;
    }

    public Integer getTicket() {
        return ticket;
    }

    public void setTicket(Integer ticket) {
        this.ticket = ticket;
    }

    public String getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }

    public boolean getAgrandaBebidaPapas() {
        return agrandaBebidaPapas;
    }

    public void setAgrandaBebidaPapas(boolean agrandaBebidaPapas) {
        this.agrandaBebidaPapas = agrandaBebidaPapas;
    }

    public boolean getParaLlevar() {
        return paraLlevar;
    }

    public void setParaLlevar(boolean paraLlevar) {
        this.paraLlevar = paraLlevar;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Cliente getRut() {
        return rut;
    }

    public void setRut(Cliente rut) {
        this.rut = rut;
    }

    @XmlTransient
    public List<PedidoDetalle> getPedidoDetalleList() {
        return pedidoDetalleList;
    }

    public void setPedidoDetalleList(List<PedidoDetalle> pedidoDetalleList) {
        this.pedidoDetalleList = pedidoDetalleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ticket != null ? ticket.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.ticket == null && other.ticket != null) || (this.ticket != null && !this.ticket.equals(other.ticket))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.entity.Pedido[ ticket=" + ticket + " ]";
    }
    
}
