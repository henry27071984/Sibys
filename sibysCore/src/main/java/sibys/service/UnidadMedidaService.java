package sibys.service;

import sibys.model.entity.UnidadMedida;

public interface UnidadMedidaService extends CrudService<UnidadMedida>{
	UnidadMedida findByNombre(String nombre) throws Exception;
	UnidadMedida findByIds(Integer id) throws Exception;
}