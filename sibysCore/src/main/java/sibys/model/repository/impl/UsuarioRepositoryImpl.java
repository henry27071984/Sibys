package sibys.model.repository.impl;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sibys.model.entity.Usuario;
import sibys.model.repository.UsuarioRepository;

@Named
public class UsuarioRepositoryImpl implements UsuarioRepository, Serializable{
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "SibysPU")
	private EntityManager em;

	@Override
	public Integer insert(Usuario t) throws Exception {
		em.persist(t);
		return t.getId();
	}

	@Override
	public Integer update(Usuario t) throws Exception {
		em.merge(t);
		return t.getId();
	}

	@Override
	public Integer delete(Usuario t) throws Exception {
		em.remove(t);
		return 1;
	}
	
	@Override
	public List<Usuario> findAll() throws Exception {
		List<Usuario> usuarios = new ArrayList<>();
		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
		usuarios = query.getResultList();
		return usuarios;
	}

	@Override
	public Usuario findByID(Usuario t) throws Exception {
		List<Usuario> usuarios = new ArrayList<>();
		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.id = ?", Usuario.class);
		query.setParameter(1, t.getId());
		usuarios = query.getResultList();
		if(usuarios != null && !usuarios.isEmpty()) {
			return usuarios.get(0);
		}else {
			return new Usuario();
		}
	}
	
	@Override
	public Usuario loginUser(String usuario, String clave) throws Exception {
		List<Usuario> usuarios = new ArrayList<>();
		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.usuario = ?0 AND u.clave = ?1", Usuario.class);
		query.setParameter(0, usuario);
		query.setParameter(1, clave);
		usuarios = query.getResultList();
		if(usuarios != null && !usuarios.isEmpty()) {
			return usuarios.get(0);
		}else {
			return null;
		}
	}
}

