package sibys.model.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedido implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "numero", length = 10, nullable = false, unique = true)
	private String numero;

	@Column(name = "fecha_confeccion", nullable = false)
	private Date fechaConfeccion;	
	
	@ManyToOne
	@JoinColumn(name = "dependencia_id", nullable = false)
	private Dependencia dependenciaId;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = true)
	private Usuario usuarioId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getFechaConfeccion() {
		return fechaConfeccion;
	}

	public void setFechaConfeccion(Date fechaConfeccion) {
		this.fechaConfeccion = fechaConfeccion;
	}

	public Dependencia getDependenciaId() {
		return dependenciaId;
	}

	public void setDependenciaId(Dependencia dependenciaId) {
		this.dependenciaId = dependenciaId;
	}

	public Usuario getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Usuario usuarioId) {
		this.usuarioId = usuarioId;
	}
}
