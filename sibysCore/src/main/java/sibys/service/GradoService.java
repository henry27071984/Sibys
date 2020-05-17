package sibys.service;

import sibys.model.entity.Grado;

public interface GradoService extends CrudService<Grado>{
	Grado findByNombre(String nombre) throws Exception;
	Grado findByIds(Integer id) throws Exception;
}
