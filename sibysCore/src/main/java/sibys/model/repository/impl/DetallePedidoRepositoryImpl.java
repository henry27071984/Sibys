package sibys.model.repository.impl;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sibys.model.entity.DetallePedido;
import sibys.model.repository.DetallePedidoRepository;

@Named
public class DetallePedidoRepositoryImpl implements DetallePedidoRepository, Serializable{
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "SibysPU")
	private EntityManager em;

	@Override
	public Integer insert(DetallePedido t) throws Exception {
		em.persist(t);
		return t.getId();
	}

	@Override
	public Integer update(DetallePedido t) throws Exception {
		em.merge(t);
		return t.getId();
	}

	@Override
	public Integer delete(DetallePedido t) throws Exception {
		em.remove(t);
		return 1;
	}
	
	@Override
	public List<DetallePedido> findAll() throws Exception {
		List<DetallePedido> detalles = new ArrayList<>();
		TypedQuery<DetallePedido> query = em.createQuery("SELECT u FROM DetallePedido u", DetallePedido.class);
		detalles = query.getResultList();
		return detalles;
	}

	@Override
	public DetallePedido findByID(DetallePedido t) throws Exception {
		List<DetallePedido> detalles = new ArrayList<>();
		TypedQuery<DetallePedido> query = em.createQuery("SELECT u FROM DetallePedido u WHERE u.id = ?", DetallePedido.class);
		query.setParameter(1, t.getId());
		detalles = query.getResultList();
		if(detalles != null && !detalles.isEmpty()) {
			return detalles.get(0);
		}else {
			return new DetallePedido();
		}
	}

	@Override
	public List<DetallePedido> findByProducto(String codigo) throws Exception {
		List<DetallePedido> detalles = new ArrayList<>();
		TypedQuery<DetallePedido> query = em.createQuery("SELECT h FROM DetallePedido h WHERE h.producto_id in (SELECT p FROM Producto p WHERE p.codigo LIKE '%?%')", DetallePedido.class);
		query.setParameter(1, codigo);
		detalles = query.getResultList();
		return detalles;
	}	
}

