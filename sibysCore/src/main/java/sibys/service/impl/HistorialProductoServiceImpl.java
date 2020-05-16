package sibys.service.impl;

import java.util.List;
import java.io.Serializable;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import sibys.model.entity.HistorialProducto;
import sibys.model.repository.HistorialProductoRepository;
import sibys.service.HistorialProductoService;

@Named
public class HistorialProductoServiceImpl implements HistorialProductoService, Serializable{
private static final long serialVersionUID = 1L;
	
	@Inject
	private HistorialProductoRepository historialRepository;

	@Transactional
	@Override
	public Integer insert(HistorialProducto t) throws Exception {
		return historialRepository.insert(t);
	}

	@Transactional
	@Override
	public Integer update(HistorialProducto t) throws Exception {
		return historialRepository.update(t);
	}

	@Transactional
	@Override
	public Integer delete(HistorialProducto t) throws Exception {
		return historialRepository.delete(t);
	}

	@Override
	public List<HistorialProducto> findAll() throws Exception {
		return historialRepository.findAll();
	}

	@Override
	public HistorialProducto findById(HistorialProducto t) throws Exception {
		return historialRepository.findByID(t);
	}

	@Override
	public List<HistorialProducto> findByProducto(String codigo) throws Exception {
		return historialRepository.findByProducto(codigo);
	}
}
