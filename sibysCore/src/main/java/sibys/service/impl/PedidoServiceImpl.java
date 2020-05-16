package sibys.service.impl;

import java.util.List;
import java.io.Serializable;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import sibys.model.entity.Pedido;
import sibys.model.repository.PedidoRepository;
import sibys.service.PedidoService;

@Named
public class PedidoServiceImpl implements PedidoService, Serializable{
private static final long serialVersionUID = 1L;
	
	@Inject
	private PedidoRepository pedidoRepository;

	@Transactional
	@Override
	public Integer insert(Pedido t) throws Exception {
		return pedidoRepository.insert(t);
	}

	@Transactional
	@Override
	public Integer update(Pedido t) throws Exception {
		return pedidoRepository.update(t);
	}

	@Transactional
	@Override
	public Integer delete(Pedido t) throws Exception {
		return pedidoRepository.delete(t);
	}

	@Override
	public List<Pedido> findAll() throws Exception {
		return pedidoRepository.findAll();
	}

	@Override
	public Pedido findById(Pedido t) throws Exception {
		return pedidoRepository.findByID(t);
	}

	@Override
	public Pedido findByNumero(String numero) throws Exception {
		return pedidoRepository.findByNumero(numero);
	}
}
