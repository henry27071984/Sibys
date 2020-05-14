package sibys.service;
import sibys.model.entity.Usuario;

public interface UsuarioService extends CrudService<Usuario>{
	Usuario loginUser(String usuario, String clave) throws Exception;
}
