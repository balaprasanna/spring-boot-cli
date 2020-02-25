package io.bala.springbootstarter.topics;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer> {	
	
	@Query("SELECT t FROM Topic t where t.name = :name")
	List<Topic> findByName(@Param("name") String name);
}

//@Repository
//public interface TopicRepository extends JpaRepository<Topic, Integer> {}
