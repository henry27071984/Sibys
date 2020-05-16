package sibys.service;
import java.util.List;
import sibys.model.entity.HistorialProducto;

public interface HistorialProductoService extends CrudService<HistorialProducto>{
	List<HistorialProducto> findByProducto(String codigo) throws Exception;
}
