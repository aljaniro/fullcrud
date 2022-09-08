package com.ANieto.api.dao;

import com.ANieto.api.entity.Cliente;
import com.ANieto.api.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteDao extends JpaRepository<Cliente,Long> {
 /*  @Query("select nombre from region")
   List<Region> ListRegion();  */
}
