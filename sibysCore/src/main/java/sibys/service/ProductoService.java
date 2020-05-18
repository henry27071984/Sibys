package sibys.service;

import java.util.List;

import sibys.model.entity.Producto;

public interface ProductoService extends CrudService<Producto>{
	Producto findByCodigo(String codigo) throws Exception;
	List<Producto> findByNombre(String nombre) throws Exception;
}
