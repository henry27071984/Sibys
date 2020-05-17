package sibys.model.repository;
import sibys.model.entity.Dependencia;

public interface DependenciaRepository extends JpaRepository<Dependencia>{
	Dependencia findByNombre(String nombre) throws Exception;
	Dependencia findByIds(Integer id) throws Exception;
}