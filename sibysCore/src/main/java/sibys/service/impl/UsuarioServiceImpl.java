package sibys.service.impl;

import java.util.List;
import java.io.Serializable;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import sibys.model.entity.Usuario;
import sibys.model.repository.UsuarioRepository;
import sibys.service.UsuarioService;

@Named
public class UsuarioServiceImpl implements UsuarioService, Serializable{
private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioRepository usuarioRepository;

	@Transactional
	@Override
	public Integer insert(Usuario t) throws Exception {
		return usuarioRepository.insert(t);
	}

	@Transactional
	@Override
	public Integer update(Usuario t) throws Exception {
		return usuarioRepository.update(t);
	}

	@Transactional
	@Override
	public Integer delete(Usuario t) throws Exception {
		return usuarioRepository.delete(t);
	}

	@Override
	public List<Usuario> findAll() throws Exception {
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario findById(Usuario t) throws Exception {
		return usuarioRepository.findByID(t);
	}

	@Override
	public Usuario loginUser(String usuario, String clave) throws Exception {
		return usuarioRepository.loginUser(usuario, clave);
	}
}
