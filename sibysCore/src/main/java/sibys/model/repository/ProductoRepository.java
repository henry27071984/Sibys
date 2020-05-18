package sibys.model.repository;

import java.util.List;

import sibys.model.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto>{
	Producto findByCodigo(String codigo) throws Exception;
	List<Producto> findByNombre(String nombre) throws Exception;
}
