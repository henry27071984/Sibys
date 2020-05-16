package sibys.service.impl;

import java.util.List;
import java.io.Serializable;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import sibys.model.entity.DetallePedido;
import sibys.model.repository.DetallePedidoRepository;
import sibys.service.DetallePedidoService;

@Named
public class DetallePedidoServiceImpl implements DetallePedidoService, Serializable{
private static final long serialVersionUID = 1L;
	
	@Inject
	private DetallePedidoRepository detallePedidoRepository;

	@Transactional
	@Override
	public Integer insert(DetallePedido t) throws Exception {
		return detallePedidoRepository.insert(t);
	}

	@Transactional
	@Override
	public Integer update(DetallePedido t) throws Exception {
		return detallePedidoRepository.update(t);
	}

	@Transactional
	@Override
	public Integer delete(DetallePedido t) throws Exception {
		return detallePedidoRepository.delete(t);
	}

	@Override
	public List<DetallePedido> findAll() throws Exception {
		return detallePedidoRepository.findAll();
	}

	@Override
	public DetallePedido findById(DetallePedido t) throws Exception {
		return detallePedidoRepository.findByID(t);
	}

	@Override
	public List<DetallePedido> findByProducto(String codigo) throws Exception {
		return detallePedidoRepository.findByProducto(codigo);
	}
}
