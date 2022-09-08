/*
package com.ANieto.api.services;

import com.ANieto.api.dao.UsuarioDao;
import com.ANieto.api.entity.JwtResponse;
import com.ANieto.api.entity.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import java.util.stream.Collectors;
@Service

public class UsuarioServices implements UserDetailsService  {
   private Logger logger = LoggerFactory.getLogger(UsuarioServices.class);
    @Autowired
    UsuarioDao usuarioDao;
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Usuario user = usuarioDao.finByUsername2(username);
        if(user == null){
            logger.error("Error en el login: No existe el usuario  '"+username+"'en el sistema");
            throw new UsernameNotFoundException("Error en el logion: No existe el usuario  '"+username+"'en el sistema");
        }
        logger.info("Bienvenido  '"+username+"'al sistema");
        List<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
                .peek(authority -> logger.info("Role: "+authority.getAuthority()))
                .collect(Collectors.toList());

        return new User(user.getUsername(),user.getPassword(), user.isEnabled(),true,true,true,authorities);
    }


    public JwtResponse login (String clientid,String clientSecret){
        JwtResponse jwt = JwtResponse.builder().tokenType("bearer")
                .accessToken("asdasdsacccqwac")
                .issuedAt(System.currentTimeMillis()+"")
                .clientid(clientid)
                .expiresin(3600)
                .build();
        return jwt;
    }
}
*/