package sibys.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "usuario", length = 50, nullable = false)
	private String usuario;	
	
	@Column(name = "clave", length = 50, nullable = false)
	private String clave;
	
	@Column(name = "email", length = 200, nullable = false)
	private String email;
	
	@Column(name = "nombre", length = 200, nullable = false)
	private String nombre;
	
	@Column(name = "apellido", length = 200, nullable = false)
	private String apellido;
	
	/*@ManyToOne
	@JoinColumn(name = "grado_id", nullable = true)
	private Grado grado_id;
	
	@ManyToOne
	@JoinColumn(name = "dependencia_id", nullable = true)
	private Dependencia dependencia_id;
	
	@ManyToOne
	@JoinColumn(name = "unidad_id", nullable = true)
	private Unidad unidad_id;*/
	

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/*public Grado getGrado_id() {
		return grado_id;
	}

	public void setGrado_id(Grado grado_id) {
		this.grado_id = grado_id;
	}

	public Dependencia getDependencia_id() {
		return dependencia_id;
	}

	public void setDependencia_id(Dependencia dependencia_id) {
		this.dependencia_id = dependencia_id;
	}

	public Unidad getUnidad_id() {
		return unidad_id;
	}

	public void setUnidad_id(Unidad unidad_id) {
		this.unidad_id = unidad_id;
	}*/

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
