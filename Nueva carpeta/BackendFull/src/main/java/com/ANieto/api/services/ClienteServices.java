package com.ANieto.api.services;

import com.ANieto.api.dao.ClienteDao;
import com.ANieto.api.dao.RegionDao;
import com.ANieto.api.entity.Cliente;
import com.ANieto.api.entity.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ClienteServices{
    @Autowired
    ClienteDao clienteDao;
    @Autowired
    RegionDao regionDao;

    @Transactional(readOnly = true)
    public List<Cliente> getAll(){
        List<Cliente> cli = clienteDao.findAll();
        return cli;
    }

    public Cliente findIdcliente(Long id){
        Cliente clien = clienteDao.findById(id).orElse(null);
        return clien;
    }

    public Cliente saveCliente(Cliente cli){
        Cliente client = clienteDao.save(cli);
        return client;
    }

    public void borrar(long id){
        Cliente clien = clienteDao.findById(id).orElse(null);
        clienteDao.delete(clien);
    }

    public Cliente modificar(long id,Cliente cli){
        Cliente clien = clienteDao.findById(id).orElse(null);
        clien.setNombre(cli.getNombre());
        clien.setApellido(cli.getApellido());
        clien.setTelefono(cli.getTelefono());
        clien.setEmail(cli.getEmail());
        clien.setFecha(cli.getFecha());
        clien.setId(id);
        return clienteDao.save(clien);
    }

    public List<Region> getRegion(){
        List<Region> regi = regionDao.findAll();
        return regi;
    }

}
