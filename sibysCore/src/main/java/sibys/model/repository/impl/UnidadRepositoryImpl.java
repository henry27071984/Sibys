package sibys.model.repository.impl;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sibys.model.entity.Unidad;
import sibys.model.repository.UnidadRepository;

@Named
public class UnidadRepositoryImpl implements UnidadRepository, Serializable{
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "SibysPU")
	private EntityManager em;

	@Override
	public Integer insert(Unidad t) throws Exception {
		em.persist(t);
		return t.getId();
	}

	@Override
	public Integer update(Unidad t) throws Exception {
		em.merge(t);
		return t.getId();
	}

	@Override
	public Integer delete(Unidad t) throws Exception {
		em.remove(t);
		return 1;
	}
	
	@Override
	public List<Unidad> findAll() throws Exception {
		List<Unidad> unidades = new ArrayList<>();
		TypedQuery<Unidad> query = em.createQuery("SELECT u FROM Unidad u", Unidad.class);
		unidades = query.getResultList();
		return unidades;
	}

	@Override
	public Unidad findByID(Unidad t) throws Exception {
		List<Unidad> unidades = new ArrayList<>();
		TypedQuery<Unidad> query = em.createQuery("SELECT u FROM Unidad u WHERE u.id = ?", Unidad.class);
		query.setParameter(1, t.getId());
		unidades = query.getResultList();
		if(unidades != null && !unidades.isEmpty()) {
			return unidades.get(0);
		}else {
			return new Unidad();
		}
	}

	@Override
	public Unidad findByNombre(String nombre) throws Exception {
		List<Unidad> nombre_unidad = new ArrayList<>();
		TypedQuery<Unidad> query = em.createQuery("SELECT p FROM Unidad p WHERE p.nombre = ?", Unidad.class);
		query.setParameter(1, nombre);
		nombre_unidad = query.getResultList();
		if(nombre_unidad != null && !nombre_unidad.isEmpty()) {
			return nombre_unidad.get(0);
		}else {
			return new Unidad();
		}
	}

	@Override
	public Unidad findByIds(Integer id) throws Exception {
		List<Unidad> unidades = new ArrayList<>();
		TypedQuery<Unidad> query = em.createQuery("SELECT p FROM Unidad p WHERE p.id = :id", Unidad.class);
		query.setParameter("id", id);
		unidades = query.getResultList();
		if(unidades != null && !unidades.isEmpty()) {
			return unidades.get(0);
		}else {
			return new Unidad();
		}
	}
}

