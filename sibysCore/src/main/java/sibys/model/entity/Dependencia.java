package sibys.model.entity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dependencias")
public class Dependencia implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nombre", length = 100, nullable = false)
	private String nombre;
	
	@Column(name = "nombre_completo", columnDefinition="TEXT", nullable = true)
	private String nombreCompleto;
	
	@Column(name = "agrupacion", length = 100, nullable = false)
	private String agrupacion;
	
	@Column(name = "grado_jefe_subun", nullable = false)
	private Integer gradoJefeSubun;

	@Column(name = "nombre_jefe_subun", length = 200, nullable = false)
	private String nombreJefeSubun;

	@Column(name = "apellido_jefe_subun", length = 200, nullable = false)
	private String apellidoJefeSubun;

	@Column(name = "cargo", length = 100, nullable = false)
	private String cargo;

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

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getAgrupacion() {
		return agrupacion;
	}

	public void setAgrupacion(String agrupacion) {
		this.agrupacion = agrupacion;
	}

	public Integer getGradoJefeSubun() {
		return gradoJefeSubun;
	}

	public void setGradoJefeSubun(Integer gradoJefeSubun) {
		this.gradoJefeSubun = gradoJefeSubun;
	}

	public String getNombreJefeSubun() {
		return nombreJefeSubun;
	}

	public void setNombreJefeSubun(String nombreJefeSubun) {
		this.nombreJefeSubun = nombreJefeSubun;
	}

	public String getApellidoJefeSubun() {
		return apellidoJefeSubun;
	}

	public void setApellidoJefeSubun(String apellidoJefeSubun) {
		this.apellidoJefeSubun = apellidoJefeSubun;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
}
