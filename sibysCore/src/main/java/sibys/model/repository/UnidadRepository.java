package sibys.model.repository;
import sibys.model.entity.Unidad;

public interface UnidadRepository extends JpaRepository<Unidad>{
	Unidad findByNombre(String nombre) throws Exception;
}