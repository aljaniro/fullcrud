package com.ANieto.api.controller;

import com.ANieto.api.entity.Cliente;
import com.ANieto.api.entity.Region;
import com.ANieto.api.services.ClienteServices;
//import com.ANieto.api.services.UsuarioServices;
//import com.ANieto.api.services.UsuarioServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.ws.rs.core.MultivaluedMap;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@CrossOrigin(origins = {"http://localhost:4200"}, allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class ClienteController {
  //  private Logger logger = LoggerFactory.getLogger(UsuarioServices.class);
    @Autowired
    ClienteServices clienteServices;
 //   @Autowired
 //   UsuarioServices usuarioServices;
    @GetMapping("/cliente")
    public ResponseEntity<?> getClientes() {

      //s  logger.info("Datos del usuario: {} ",auth.getPrincipal());
        List<Cliente> clien = clienteServices.getAll();
        Map<String, Object> response = new HashMap<>();
        if (clien.isEmpty()) {
            //   return ResponseEntity.noContent().build();
            response.put("Mensaje", "Base de datos vacia");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(clien);
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<?> buscar(@PathVariable long id) {
        Cliente cli = null;
        Map<String, Object> response = new HashMap<>();
        try {
            cli = clienteServices.findIdcliente(id);
        } catch (Exception e) {
            response.put("Mensaje: ", "error al realizar la consulta");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }


        if (cli == null) {
            response.put("Mensaje", "El cliente con ID: ".concat(String.valueOf(id).concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(cli);
    }

    @PostMapping("/cliente")
    public ResponseEntity<?> guardar(@Valid @RequestBody Cliente cli, BindingResult result) {
        Cliente clien = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
         /*   List<String> errors = new ArrayList<>();
            for(FieldError err: result.getFieldErrors()){
                errors.add("El campo '"+ err.getField()+"'"+err.getDefaultMessage());
            }*/
            //otra forma
            List<String> errors = result.getFieldErrors().stream().map(err -> "El campo '" + err.getField() + "'" + err.getDefaultMessage()
            ).collect(Collectors.toList());
            response.put("Errores", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {

            clien = clienteServices.saveCliente(cli);
        } catch (Exception e) {

            response.put("Mensaje", "Error al crear el usuario");
            response.put("Error: ", e.getMessage().concat(":").concat(e.getCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("Mensaje", "Se ha creado satisfactoriamente");
        response.put("Usuario", clien);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<?> borrar(@PathVariable("id") long id) {
        Map<String, Object> response = new HashMap<>();
        Cliente cli = null;
        cli = clienteServices.findIdcliente(id);
        if (cli == null) {
            response.put("Mensaje", "El usuario con id: ".concat(String.valueOf(id)).concat(" no existe en la base de datos"));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        String fotoAnterior = cli.getFoto();
        if(fotoAnterior !=null && fotoAnterior.length()>0){
            Path rutaAnterior = Paths.get("uploads").resolve(fotoAnterior).toAbsolutePath();
            File fotoVieja = rutaAnterior.toFile();
            if(fotoVieja.exists() && fotoVieja.canRead()){
                fotoVieja.delete();
            }}
        clienteServices.borrar(id);
        response.put("Mensaje", "Usuario eliminado satisfactoriamente");
        response.put("Adios", cli);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
    }

    @PutMapping("/cliente/{id}")
    public ResponseEntity<?> actualizar(@PathVariable("id") long id, @Valid @RequestBody Cliente cli, BindingResult result) {
        Cliente clie = null;
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
         /*   List<String> errors = new ArrayList<>();
            for(FieldError err: result.getFieldErrors()){
                errors.add("El campo '"+ err.getField()+"'"+err.getDefaultMessage());
            }*/
            //otra forma
            List<String> errors = result.getFieldErrors().stream().map(err -> "El campo '" + err.getField() + "'" + err.getDefaultMessage()
            ).collect(Collectors.toList());
            response.put("Errores", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        clie = clienteServices.findIdcliente(id);
        if (cli == null) {
            response.put("Mensaje", "El cliente con ID: ".concat(String.valueOf(id).concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            clie.setId(id);
            clie.setNombre(cli.getNombre());
            clie.setApellido(cli.getApellido());
            clie.setEmail(cli.getEmail());
            clie.setTelefono(cli.getTelefono());
            clie.setFecha(cli.getFecha());
            clie.setRegion(cli.getRegion());
            clienteServices.saveCliente(clie);
        } catch (Exception e) {
            response.put("Mensaje", "Error al modificar al usuario");
            response.put("Error", e.getMessage().concat(":").concat(e.getCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("Mensaje", "Usuario actualizado con exito");
        response.put("Actualizado", clie);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PostMapping("cliente/imagen")
    public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, long id) {
        Map<String, Object> response = new HashMap<>();
        Cliente cli = clienteServices.findIdcliente(id);
        if (!archivo.isEmpty()) {
            String nombreArchivo = archivo.getOriginalFilename();
            Path Ruta = Paths.get("Upload").resolve(nombreArchivo).toAbsolutePath();

            try {
                Files.copy(archivo.getInputStream(), Ruta);
            } catch (IOException e) {
                response.put("Mensaje", "Error al subir el archivo");
                response.put("Error", e.getMessage().concat(":").concat(e.getCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            String fotoAnterior = cli.getFoto();
            if(fotoAnterior !=null && fotoAnterior.length()>0){
                Path rutaAnterior = Paths.get("uploads").resolve(fotoAnterior).toAbsolutePath();
                File fotoVieja = rutaAnterior.toFile();
                if(fotoVieja.exists() && fotoVieja.canRead()){
                    fotoVieja.delete();
                }
            }
            cli.setFoto(nombreArchivo);
            clienteServices.saveCliente(cli);
            response.put("Cliente", cli);
            response.put("Mensaje", "Ha subido correctamente el archivo");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }


    @GetMapping("/upload/img/{Foto:.+}")
        public ResponseEntity<Resource> verImagen(@PathVariable String Foto){
        Path ruta = Paths.get("upload").resolve(Foto).toAbsolutePath();

       Resource recurso = null;
        try {
            recurso= new UrlResource(ruta.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if(!recurso.exists() && !recurso.isReadable()){
            throw new RuntimeException("No se pudo encontrar la imagen :"+Foto);
        }
        HttpHeaders cabecera = new HttpHeaders();
          cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + recurso.getFilename()+"\"");
        return new ResponseEntity<Resource>( recurso,cabecera,HttpStatus.OK);
    }

    @GetMapping("cliente/region")
    public ResponseEntity<List<Region>> getRegiones(){
        List<Region> regi = clienteServices.getRegion();
        if(regi.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(regi);
    }
/*
@PostMapping("oauth/client_credential/accesstoken")
    public ResponseEntity<Object> login(@RequestBody MultivaluedMap<String,String> paraMap,@RequestParam("grant_type") String grantType){
        return ResponseEntity.ok(usuarioServices.login(paraMap.getFirst("client_id"),paraMap.getFirst("client_secret")));
}*/
}
