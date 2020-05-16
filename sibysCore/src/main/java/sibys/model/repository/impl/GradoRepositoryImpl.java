package sibys.model.repository.impl;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sibys.model.entity.Grado;
import sibys.model.repository.GradoRepository;

@Named
public class GradoRepositoryImpl implements GradoRepository, Serializable{
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "SibysPU")
	private EntityManager em;

	@Override
	public Integer insert(Grado t) throws Exception {
		em.persist(t);
		return t.getId();
	}

	@Override
	public Integer update(Grado t) throws Exception {
		em.merge(t);
		return t.getId();
	}

	@Override
	public Integer delete(Grado t) throws Exception {
		em.remove(t);
		return 1;
	}
	
	@Override
	public List<Grado> findAll() throws Exception {
		List<Grado> grados = new ArrayList<>();
		TypedQuery<Grado> query = em.createQuery("SELECT u FROM Grado u", Grado.class);
		grados = query.getResultList();
		return grados;
	}

	@Override
	public Grado findByID(Grado t) throws Exception {
		List<Grado> grados = new ArrayList<>();
		TypedQuery<Grado> query = em.createQuery("SELECT u FROM Grado u WHERE u.id = ?", Grado.class);
		query.setParameter(1, t.getId());
		grados = query.getResultList();
		if(grados != null && !grados.isEmpty()) {
			return grados.get(0);
		}else {
			return new Grado();
		}
	}

	@Override
	public Grado findByNombre(String nombre) throws Exception {
		List<Grado> nombre_grado = new ArrayList<>();
		TypedQuery<Grado> query = em.createQuery("SELECT p FROM Grado p WHERE p.nombre = ?", Grado.class);
		query.setParameter(1, nombre);
		nombre_grado = query.getResultList();
		if(nombre_grado != null && !nombre_grado.isEmpty()) {
			return nombre_grado.get(0);
		}else {
			return new Grado();
		}
	}	
}

