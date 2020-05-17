package sibys.service.impl;

import java.util.List;
import java.io.Serializable;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import sibys.model.entity.Unidad;
import sibys.model.repository.UnidadRepository;
import sibys.service.UnidadService;

@Named
public class UnidadServiceImpl implements UnidadService, Serializable{
private static final long serialVersionUID = 1L;
	
	@Inject
	private UnidadRepository unidadRepository;

	@Transactional
	@Override
	public Integer insert(Unidad t) throws Exception {
		return unidadRepository.insert(t);
	}

	@Transactional
	@Override
	public Integer update(Unidad t) throws Exception {
		return unidadRepository.update(t);
	}

	@Transactional
	@Override
	public Integer delete(Unidad t) throws Exception {
		return unidadRepository.delete(t);
	}

	@Override
	public List<Unidad> findAll() throws Exception {
		return unidadRepository.findAll();
	}

	@Override
	public Unidad findById(Unidad t) throws Exception {
		return unidadRepository.findByID(t);
	}

	@Override
	public Unidad findByNombre(String nombre) throws Exception {
		return unidadRepository.findByNombre(nombre);
	}

	@Override
	public Unidad findByIds(Integer id) throws Exception {
		return unidadRepository.findByIds(id);
	}
}
