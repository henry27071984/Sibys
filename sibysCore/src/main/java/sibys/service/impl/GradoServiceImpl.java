package sibys.service.impl;

import java.util.List;
import java.io.Serializable;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import sibys.model.entity.Grado;
import sibys.model.repository.GradoRepository;
import sibys.service.GradoService;

@Named
public class GradoServiceImpl implements GradoService, Serializable{
private static final long serialVersionUID = 1L;
	
	@Inject
	private GradoRepository gradoRepository;

	@Transactional
	@Override
	public Integer insert(Grado t) throws Exception {
		return gradoRepository.insert(t);
	}

	@Transactional
	@Override
	public Integer update(Grado t) throws Exception {
		return gradoRepository.update(t);
	}

	@Transactional
	@Override
	public Integer delete(Grado t) throws Exception {
		return gradoRepository.delete(t);
	}

	@Override
	public List<Grado> findAll() throws Exception {
		return gradoRepository.findAll();
	}

	@Override
	public Grado findById(Grado t) throws Exception {
		return gradoRepository.findByID(t);
	}

	@Override
	public Grado findByNombre(String nombre) throws Exception {
		return gradoRepository.findByNombre(nombre);
	}
}
