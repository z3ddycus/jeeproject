package univ.model.repository;

import org.springframework.data.repository.CrudRepository;
import univ.model.repository.entity.Component;


public interface ComponentRepository extends CrudRepository<Component, Long> {
}
