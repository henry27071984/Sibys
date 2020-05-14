package sibys.model.repository.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import sibys.model.entity.Producto;
import sibys.model.repository.ProductoRepository;

@Named
public class ProductoRepositoryImpl implements ProductoRepository, Serializable{
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "SibysPU")
	private EntityManager em;

	@Override
	public Integer insert(Producto t) throws Exception {
		em.persist(t);
		return t.getId();
	}

	@Override
	public Integer update(Producto t) throws Exception {
		em.merge(t);
		return t.getId();
	}

	@Override
	public Integer delete(Producto t) throws Exception {
		em.remove(t);
		return 1;
	}

	@Override
	public List<Producto> findAll() throws Exception {
		List<Producto> productos = new ArrayList<>();
		TypedQuery<Producto> query = em.createQuery("SELECT p FROM Producto p", Producto.class);
		productos = query.getResultList();
		return productos;
	}

	@Override
	public Producto findByID(Producto t) throws Exception {
		List<Producto> productos = new ArrayList<>();
		TypedQuery<Producto> query = em.createQuery("SELECT p FROM Producto p WHERE p.id = ?", Producto.class);
		query.setParameter(1, t.getId());
		productos = query.getResultList();
		if(productos != null && !productos.isEmpty()) {
			return productos.get(0);
		}else {
			return new Producto();
		}
	}

	@Override
	public Producto findByCodigo(String codigo) throws Exception {
		List<Producto> productos = new ArrayList<>();
		TypedQuery<Producto> query = em.createQuery("SELECT p FROM Producto p WHERE p.codigo = ?", Producto.class);
		query.setParameter(1, codigo);
		productos = query.getResultList();
		if(productos != null && !productos.isEmpty()) {
			return productos.get(0);
		}else {
			return new Producto();
		}
	}
}