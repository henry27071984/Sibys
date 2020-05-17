package sibys.model.repository.impl;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sibys.model.entity.Dependencia;
import sibys.model.repository.DependenciaRepository;

@Named
public class DependenciaRepositoryImpl implements DependenciaRepository, Serializable{
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "SibysPU")
	private EntityManager em;

	@Override
	public Integer insert(Dependencia t) throws Exception {
		em.persist(t);
		return t.getId();
	}

	@Override
	public Integer update(Dependencia t) throws Exception {
		em.merge(t);
		return t.getId();
	}

	@Override
	public Integer delete(Dependencia t) throws Exception {
		em.remove(t);
		return 1;
	}
	
	@Override
	public List<Dependencia> findAll() throws Exception {
		List<Dependencia> dependencias = new ArrayList<>();
		TypedQuery<Dependencia> query = em.createQuery("SELECT u FROM Dependencia u", Dependencia.class);
		dependencias = query.getResultList();
		return dependencias;
	}

	@Override
	public Dependencia findByID(Dependencia t) throws Exception {
		List<Dependencia> dependencias = new ArrayList<>();
		TypedQuery<Dependencia> query = em.createQuery("SELECT u FROM Dependencia u WHERE u.id = ?", Dependencia.class);
		query.setParameter(1, t.getId());
		dependencias = query.getResultList();
		if(dependencias != null && !dependencias.isEmpty()) {
			return dependencias.get(0);
		}else {
			return new Dependencia();
		}
	}

	@Override
	public Dependencia findByNombre(String nombre) throws Exception {
		List<Dependencia> nombre_dependencia = new ArrayList<>();
		TypedQuery<Dependencia> query = em.createQuery("SELECT p FROM Dependencia p WHERE p.nombre = ?", Dependencia.class);
		query.setParameter(1, nombre);
		nombre_dependencia = query.getResultList();
		if(nombre_dependencia != null && !nombre_dependencia.isEmpty()) {
			return nombre_dependencia.get(0);
		}else {
			return new Dependencia();
		}
	}

	@Override
	public Dependencia findByIds(Integer id) throws Exception {
		List<Dependencia> dependencias = new ArrayList<>();
		TypedQuery<Dependencia> query = em.createQuery("SELECT p FROM Dependencia p WHERE p.id = :id", Dependencia.class);
		query.setParameter("id", id);
		dependencias = query.getResultList();
		if(dependencias != null && !dependencias.isEmpty()) {
			return dependencias.get(0);
		}else {
			return new Dependencia();
		}
	}
}

