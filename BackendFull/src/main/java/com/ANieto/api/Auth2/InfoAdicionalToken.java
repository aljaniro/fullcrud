/*
package com.ANieto.api.Auth2;

import com.ANieto.api.dao.UsuarioDao;
import com.ANieto.api.entity.Usuario;
import com.ANieto.api.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class InfoAdicionalToken implements TokenEnhancer {
    @Autowired
    UsuarioServices usuarioServices ;
    @Autowired
    UsuarioDao usuarioDao;


    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Usuario usuario = usuarioDao.finByUsername2(authentication.getName());
        Map<String, Object> info = new HashMap<>();
        info.put("info_adicional", "Hola que tal!: ".concat(authentication.getName()));

        info.put("nombre", usuario.getUsername());


        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);

        return accessToken;
    }
}

*/