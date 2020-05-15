package sibys.model.repository.impl;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sibys.model.entity.HistorialProducto;
import sibys.model.repository.HistorialProductoRepository;

@Named
public class HistorialProductoRepositoryImpl implements HistorialProductoRepository, Serializable{
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "SibysPU")
	private EntityManager em;

	@Override
	public Integer insert(HistorialProducto t) throws Exception {
		em.persist(t);
		return t.getId();
	}

	@Override
	public Integer update(HistorialProducto t) throws Exception {
		em.merge(t);
		return t.getId();
	}

	@Override
	public Integer delete(HistorialProducto t) throws Exception {
		em.remove(t);
		return 1;
	}
	
	@Override
	public List<HistorialProducto> findAll() throws Exception {
		List<HistorialProducto> historial = new ArrayList<>();
		TypedQuery<HistorialProducto> query = em.createQuery("SELECT u FROM HistorialProducto u", HistorialProducto.class);
		historial = query.getResultList();
		return historial;
	}

	@Override
	public HistorialProducto findByID(HistorialProducto t) throws Exception {
		List<HistorialProducto> historial = new ArrayList<>();
		TypedQuery<HistorialProducto> query = em.createQuery("SELECT u FROM HistorialProducto u WHERE u.id = ?", HistorialProducto.class);
		query.setParameter(1, t.getId());
		historial = query.getResultList();
		if(historial != null && !historial.isEmpty()) {
			return historial.get(0);
		}else {
			return new HistorialProducto();
		}
	}

	@Override
	public List<HistorialProducto> findByProducto(String codigo) throws Exception {
		List<HistorialProducto> historial = new ArrayList<>();
		TypedQuery<HistorialProducto> query = em.createQuery("SELECT h FROM HistorialProducto h WHERE h.producto_id in (SELECT p FROM Producto p WHERE p.codigo LIKE '%?%')", HistorialProducto.class);
		query.setParameter(1, codigo);
		historial = query.getResultList();
		return historial;
	}	
}

