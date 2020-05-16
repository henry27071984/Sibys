package sibys.model.repository.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import sibys.model.entity.UnidadMedida;
import sibys.model.repository.UnidadMedidaRepository;

@Named
public class UnidadMedidaRepositoryImpl implements UnidadMedidaRepository, Serializable{
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "SibysPU")
	private EntityManager em;
	
	@Override
	public Integer insert(UnidadMedida t) throws Exception {
		em.persist(t);
		return t.getId();
	}

	@Override
	public Integer update(UnidadMedida t) throws Exception {
		em.merge(t);
		return t.getId();
	}

	@Override
	public Integer delete(UnidadMedida t) throws Exception {
		em.remove(t);
		return 1;
	}

	@Override
	public List<UnidadMedida> findAll() throws Exception {
		List<UnidadMedida> unidad = new ArrayList<>();
		TypedQuery<UnidadMedida> query = em.createQuery("SELECT p FROM UnidadMedida p", UnidadMedida.class);
		unidad = query.getResultList();
		return unidad;
	}

	@Override
	public UnidadMedida findByID(UnidadMedida t) throws Exception {
		List<UnidadMedida> unidad = new ArrayList<>();
		TypedQuery<UnidadMedida> query = em.createQuery("SELECT p FROM UnidadMedida p WHERE p.id = ?", UnidadMedida.class);
		query.setParameter(1, t.getId());
		unidad = query.getResultList();
		if(unidad != null && !unidad.isEmpty()) {
			return unidad.get(0);
		}else {
			return new UnidadMedida();
		}
	}

	@Override
	public UnidadMedida findByNombre(String nombre) throws Exception {
		List<UnidadMedida> unidad = new ArrayList<>();
		TypedQuery<UnidadMedida> query = em.createQuery("SELECT p FROM UnidadMedida p WHERE p.nombre = ?", UnidadMedida.class);
		query.setParameter(1, nombre);
		unidad = query.getResultList();
		if(unidad != null && !unidad.isEmpty()) {
			return unidad.get(0);
		}else {
			return new UnidadMedida();
		}
	}
	
	@Override
	public UnidadMedida findByIds(Integer id) throws Exception {
		List<UnidadMedida> unidad = new ArrayList<>();
		TypedQuery<UnidadMedida> query = em.createQuery("SELECT p FROM UnidadMedida p WHERE p.id = :id", UnidadMedida.class);
		query.setParameter("id", id);
		unidad = query.getResultList();
		if(unidad != null && !unidad.isEmpty()) {
			return unidad.get(0);
		}else {
			return new UnidadMedida();
		}
	}

}
