package sibys.service;

import sibys.model.entity.Dependencia;

public interface DependenciaService extends CrudService<Dependencia>{
	Dependencia findByNombre(String nombre) throws Exception;
}
