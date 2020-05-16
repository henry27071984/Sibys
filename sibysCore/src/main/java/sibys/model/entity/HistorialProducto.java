package sibys.model.entity;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "historialproducto")
public class HistorialProducto implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "iso_fecha_alta", columnDefinition= "TIMESTAMP WITH TIME ZONE", nullable = true)
	private ZonedDateTime isoFechaAlta;
	
	@Column(name = "fecha_alta", nullable = true)
	private LocalDateTime fechaAlta;

	@Column(name = "iso_fecha_baja", columnDefinition= "TIMESTAMP WITH TIME ZONE", nullable = true)
	private ZonedDateTime isoFechaBaja;
	
	@Column(name = "fecha_baja", nullable = true)
	private LocalDateTime fechaBaja;

	@ManyToOne
	@JoinColumn(name = "producto_id", nullable = true)
	private Producto productoId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ZonedDateTime getIsoFechaAlta() {
		return isoFechaAlta;
	}

	public void setIsoFechaAlta(ZonedDateTime isoFechaAlta) {
		this.isoFechaAlta = isoFechaAlta;
	}

	public LocalDateTime getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDateTime fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public ZonedDateTime getIsoFechaBaja() {
		return isoFechaBaja;
	}

	public void setIsoFechaBaja(ZonedDateTime isoFechaBaja) {
		this.isoFechaBaja = isoFechaBaja;
	}

	public LocalDateTime getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(LocalDateTime fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public Producto getProductoId() {
		return productoId;
	}

	public void setProductoId(Producto productoId) {
		this.productoId = productoId;
	}
}
