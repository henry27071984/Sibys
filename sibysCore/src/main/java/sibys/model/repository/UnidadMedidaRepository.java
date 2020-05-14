package sibys.model.repository;

import sibys.model.entity.UnidadMedida;

public interface UnidadMedidaRepository extends JpaRepository<UnidadMedida>{
	UnidadMedida findByNombre(String nombre) throws Exception;
}
