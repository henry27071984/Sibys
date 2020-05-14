package sibys.model.repository;

import sibys.model.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto>{
	Producto findByCodigo(String codigo) throws Exception;
}
