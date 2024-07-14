package kz.smartrecs.authorization.repository;

import kz.smartrecs.authorization.model.Authority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Integer> {

}
