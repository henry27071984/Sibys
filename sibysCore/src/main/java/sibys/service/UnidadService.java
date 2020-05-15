package sibys.service;

import sibys.model.entity.Unidad;

public interface UnidadService extends CrudService<Unidad>{
	Unidad findByNombre(String nombre) throws Exception;
}
