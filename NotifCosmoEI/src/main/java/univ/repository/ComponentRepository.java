package univ.repository;

import org.springframework.data.repository.CrudRepository;
import univ.domain.Component;


public interface ComponentRepository extends CrudRepository<Component, Long> {
}
