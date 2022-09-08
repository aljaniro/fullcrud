package com.ANieto.api.dao;

import com.ANieto.api.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario,Long> {
   // Usuario finByUsername2(String username);
  @Query("select u from Usuario u where u.username=?1")
   Usuario finByUsername2(String username);
}
