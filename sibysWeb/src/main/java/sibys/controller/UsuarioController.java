package sibys.controller;

import java.util.List;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import sibys.model.entity.Usuario;
import sibys.model.entity.Dependencia;
import sibys.model.entity.Grado;
import sibys.model.entity.Unidad;
import sibys.service.UsuarioService;
import sibys.service.DependenciaService;
import sibys.service.GradoService;
import sibys.service.UnidadService;
import sibys.util.Message;
import javax.annotation.PostConstruct;
import org.primefaces.event.SelectEvent;

@Named
@ViewScoped
public class UsuarioController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioService usuarioService;
	@Inject
	private DependenciaService dependenciaService;
	@Inject
	private GradoService gradoService;
	@Inject
	private UnidadService unidadService;

	private Usuario usuario;

	private Dependencia dependencia;
	private List<Dependencia> dependencias;
	private Integer dependenciaSeleccionado;

	private Grado grado;
	private List<Grado> grados;
	private Integer gradoSeleccionado;

	private Unidad unidad;
	private List<Unidad> unidades;
	private Integer unidadSeleccionado;

	private String username;
	private String clave;
	private String nombre;
	private String apellido;
	private String email;

	public void loadDependencia() {
		try {
			this.dependencias = dependenciaService.findAll();
		} catch (Exception e) {
			Message.messageError("Error: " + e.getMessage());
		}
	}

	public void loadGrado() {
		try {
			this.grados = gradoService.findAll();
		} catch (Exception e) {
			Message.messageError("Error: " + e.getMessage());
		}
	}

	public void loadUnidad() {
		try {
			this.unidades = unidadService.findAll();
		} catch (Exception e) {
			Message.messageError("Error: " + e.getMessage());
		}
	}

	public void selGrado(SelectEvent e){
	    this.grado = (Grado)e.getObject();
	}

	public Grado cargarGrados() throws Exception {
		grado = gradoService.findByIds(this.gradoSeleccionado);
		return grado;
	}

	public void selDependencia(SelectEvent e){
	    this.dependencia = (Dependencia)e.getObject();
	}

	public Dependencia cargarDependencias() throws Exception {
		dependencia = dependenciaService.findByIds(this.dependenciaSeleccionado);
		return dependencia;
	}

	public void selUnidad(SelectEvent e){
	    this.unidad = (Unidad)e.getObject();
	}

	public Unidad cargarUnidades() throws Exception {
		unidad = unidadService.findByIds(this.unidadSeleccionado);
		return unidad;
	}

	@PostConstruct
	public void init() {
		this.usuario = new Usuario();
		this.loadGrado();
		this.loadDependencia();		
		this.loadUnidad();
	}
	
	public void login() {
		try {
			this.usuario = usuarioService.loginUser(username, clave);
			
			if (Objects.nonNull(usuario) && usuario.getUsuario().equals(username) && usuario.getClave().equals(clave)) {
				redireccionarPagina("/producto.xhtml");
			} else {
				agregarMensajeError("Error:", "Usuario o contraseña incorrectos");
			}
		} catch (Exception e) {
			System.out.println(e);
			Message.messageError("Error: " + e.getMessage());
		}
	}

	public void register() {
		try {
			if (this.usuario.getId() != null) {
				this.usuario.setGradoId(this.grado);
				this.usuario.setDependenciaId(this.dependencia);
				this.usuario.setUnidadId(this.unidad);
				this.usuarioService.update(this.usuario);
				Message.messageInfo("DATOS DE USUARIOS ACTUALIZADOS CORRECTAMENTE");
			} else {
				this.usuario.setGradoId(this.grado);
				this.usuario.setDependenciaId(this.dependencia);
				this.usuario.setUnidadId(this.unidad);
				this.usuarioService.insert(this.usuario);
				Message.messageInfo("USUARIO REGISTRADO CORRECTAMENTE");
				redireccionarPagina("/producto.xhtml");
			}			
		} catch (Exception e) {
			Message.messageError("ERROR EN REGISTRAR USUARIO: " + e.getMessage());
		}
	}

	protected void redireccionarPagina(String pagina) {
		try {
			getExternalContext().redirect(getHttpRequest().getContextPath().concat(pagina));
		} catch (IOException e) {
			agregarMensajeError("No se puede redireccionar a " + pagina, e.getLocalizedMessage());
		}
	}

	protected ExternalContext getExternalContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}

	public HttpServletRequest getHttpRequest() {
		return (HttpServletRequest) getExternalContext().getRequest();
	}

	protected void agregarMensajeError(String resumen, String detalle) {
		FacesMessage errorMessage = new FacesMessage();
		errorMessage.setSummary(resumen);
		errorMessage.setDetail(detalle == null ? detalle : detalle);
		errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(null, errorMessage);
		FacesContext.getCurrentInstance().validationFailed();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Dependencia getDependencia() {
		return dependencia;
	}

	public void setDependencia(Dependencia dependencia) {
		this.dependencia = dependencia;
	}

	public List<Dependencia> getDependencias() {
		return dependencias;
	}

	public void setDependencias(List<Dependencia> dependencias) {
		this.dependencias = dependencias;
	}

	public Integer getDependenciaSeleccionado() {
		return dependenciaSeleccionado;
	}

	public void setDependenciaSeleccionado(Integer dependenciaSeleccionado) {
		this.dependenciaSeleccionado = dependenciaSeleccionado;
	}

	public Grado getGrado() {
		return grado;
	}

	public void setGrado(Grado grado) {
		this.grado = grado;
	}

	public List<Grado> getGrados() {
		return grados;
	}

	public void setGrados(List<Grado> grados) {
		this.grados = grados;
	}

	public Integer getGradoSeleccionado() {
		return gradoSeleccionado;
	}

	public void setGradoSeleccionado(Integer gradoSeleccionado) {
		this.gradoSeleccionado = gradoSeleccionado;
	}

	public Unidad getUnidad() {
		return unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

	public List<Unidad> getUnidades() {
		return unidades;
	}

	public void setUnidades(List<Unidad> unidades) {
		this.unidades = unidades;
	}

	public Integer getUnidadSeleccionado() {
		return unidadSeleccionado;
	}

	public void setUnidadSeleccionado(Integer unidadSeleccionado) {
		this.unidadSeleccionado = unidadSeleccionado;
	}
}
