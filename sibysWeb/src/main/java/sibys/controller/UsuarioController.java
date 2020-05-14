package sibys.controller;
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
import sibys.service.UsuarioService;
import sibys.util.Message;
import javax.annotation.PostConstruct;

@Named
@ViewScoped
public class UsuarioController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioService usuarioService;

	private Usuario usuario;
	private String username;
	private String clave;
	private String nombre;
	private String apellido;
	private String email;
	// private String grado_id;

	@PostConstruct
	public void init() {
		this.usuario = new Usuario();
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
				this.usuarioService.update(this.usuario);
				Message.messageInfo("DATOS DE USUARIOS ACTUALIZADOS CORRECTAMENTE");
			} else {
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

}
