package sibys.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import sibys.model.entity.Producto;
import sibys.model.repository.ProductoRepository;
import sibys.service.ProductoService;

@Named
public class ProductoServiceImpl implements ProductoService, Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProductoRepository productoRepository;

	@Transactional
	@Override
	public Integer insert(Producto t) throws Exception {
		return productoRepository.insert(t);
	}

	@Transactional
	@Override
	public Integer update(Producto t) throws Exception {
		return productoRepository.update(t);
	}

	@Transactional
	@Override
	public Integer delete(Producto t) throws Exception {
		return productoRepository.delete(t);
	}

	@Override
	public List<Producto> findAll() throws Exception {
		return productoRepository.findAll();
	}

	@Override
	public Producto findById(Producto t) throws Exception {
		return productoRepository.findByID(t);
	}

	@Override
	public Producto findByCodigo(String codigo) throws Exception {
		return productoRepository.findByCodigo(codigo);
	}

}
