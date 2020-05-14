package sibys.model.repository;
import sibys.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario>{
	Usuario loginUser(String usuario, String clave) throws Exception;
}
