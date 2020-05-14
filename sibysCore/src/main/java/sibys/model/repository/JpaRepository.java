package sibys.model.repository;

import java.util.List;

public interface JpaRepository<T> {
	Integer insert(T t) throws Exception;
	Integer update(T t) throws Exception;
	Integer delete(T t) throws Exception;
	List<T> findAll() throws Exception;
	T findByID(T t) throws Exception;
}