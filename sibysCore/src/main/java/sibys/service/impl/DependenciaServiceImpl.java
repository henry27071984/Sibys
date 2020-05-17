package sibys.service.impl;

import java.util.List;
import java.io.Serializable;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import sibys.model.entity.Dependencia;
import sibys.model.repository.DependenciaRepository;
import sibys.service.DependenciaService;

@Named
public class DependenciaServiceImpl implements DependenciaService, Serializable{
private static final long serialVersionUID = 1L;
	
	@Inject
	private DependenciaRepository dependenciaRepository;

	@Transactional
	@Override
	public Integer insert(Dependencia t) throws Exception {
		return dependenciaRepository.insert(t);
	}

	@Transactional
	@Override
	public Integer update(Dependencia t) throws Exception {
		return dependenciaRepository.update(t);
	}

	@Transactional
	@Override
	public Integer delete(Dependencia t) throws Exception {
		return dependenciaRepository.delete(t);
	}

	@Override
	public List<Dependencia> findAll() throws Exception {
		return dependenciaRepository.findAll();
	}

	@Override
	public Dependencia findById(Dependencia t) throws Exception {
		return dependenciaRepository.findByID(t);
	}

	@Override
	public Dependencia findByNombre(String nombre) throws Exception {
		return dependenciaRepository.findByNombre(nombre);
	}

	@Override
	public Dependencia findByIds(Integer id) throws Exception {
		return dependenciaRepository.findByIds(id);
	}
}
