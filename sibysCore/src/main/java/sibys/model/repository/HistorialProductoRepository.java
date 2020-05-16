package sibys.model.repository;
import java.util.List;
import sibys.model.entity.HistorialProducto;

public interface HistorialProductoRepository extends JpaRepository<HistorialProducto>{
	List<HistorialProducto> findByProducto(String codigo) throws Exception;
}