package sibys.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "usuario", length = 50, nullable = false, unique = true)
	private String usuario;	
	
	@Column(name = "clave", length = 50, nullable = false)
	private String clave;
	
	@Column(name = "email", length = 200, nullable = false, unique = true)
	private String email;
	
	@Column(name = "nombre", length = 200, nullable = false)
	private String nombre;
	
	@Column(name = "apellido", length = 200, nullable = false)
	private String apellido;
	
	@ManyToOne
	@JoinColumn(name = "grado_id", nullable = true)
	private Grado gradoId;
	
	@ManyToOne
	@JoinColumn(name = "dependencia_id", nullable = true)
	private Dependencia dependenciaId;
	
	@ManyToOne
	@JoinColumn(name = "unidad_id", nullable = true)
	private Unidad unidadId;
	
	
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

	public Grado getGradoId() {
		return gradoId;
	}

	public void setGradoId(Grado gradoId) {
		this.gradoId = gradoId;
	}

	public Dependencia getDependenciaId() {
		return dependenciaId;
	}

	public void setDependenciaId(Dependencia dependenciaId) {
		this.dependenciaId = dependenciaId;
	}

	public Unidad getUnidadId() {
		return unidadId;
	}

	public void setUnidadId(Unidad unidadId) {
		this.unidadId = unidadId;
	}	
}
