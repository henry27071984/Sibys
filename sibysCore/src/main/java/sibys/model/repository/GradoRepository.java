package sibys.model.repository;
import sibys.model.entity.Grado;

public interface GradoRepository extends JpaRepository<Grado>{
	Grado findByNombre(String nombre) throws Exception;
}