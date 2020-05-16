package sibys.model.repository.impl;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sibys.model.entity.Pedido;
import sibys.model.repository.PedidoRepository;

@Named
public class PedidoRepositoryImpl implements PedidoRepository, Serializable{
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "SibysPU")
	private EntityManager em;

	@Override
	public Integer insert(Pedido t) throws Exception {
		em.persist(t);
		return t.getId();
	}

	@Override
	public Integer update(Pedido t) throws Exception {
		em.merge(t);
		return t.getId();
	}

	@Override
	public Integer delete(Pedido t) throws Exception {
		em.remove(t);
		return 1;
	}
	
	@Override
	public List<Pedido> findAll() throws Exception {
		List<Pedido> pedidos = new ArrayList<>();
		TypedQuery<Pedido> query = em.createQuery("SELECT u FROM Pedido u", Pedido.class);
		pedidos = query.getResultList();
		return pedidos;
	}

	@Override
	public Pedido findByID(Pedido t) throws Exception {
		List<Pedido> pedidos = new ArrayList<>();
		TypedQuery<Pedido> query = em.createQuery("SELECT u FROM Pedido u WHERE u.id = ?", Pedido.class);
		query.setParameter(1, t.getId());
		pedidos = query.getResultList();
		if(pedidos != null && !pedidos.isEmpty()) {
			return pedidos.get(0);
		}else {
			return new Pedido();
		}
	}

	@Override
	public Pedido findByNumero(String numero) throws Exception {
		List<Pedido> pedidos = new ArrayList<>();
		TypedQuery<Pedido> query = em.createQuery("SELECT p FROM Pedido p WHERE p.numero = ?", Pedido.class);
		query.setParameter(1, numero);
		pedidos = query.getResultList();
		if(pedidos != null && !pedidos.isEmpty()) {
			return pedidos.get(0);
		}else {
			return new Pedido();
		}
	}	
}

