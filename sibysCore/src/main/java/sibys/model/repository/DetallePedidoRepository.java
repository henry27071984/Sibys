package sibys.model.repository;
import sibys.model.entity.DetallePedido;
import java.util.List;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido>{
	List<DetallePedido> findByProducto(String codigo) throws Exception;
}