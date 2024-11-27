package com.bolsadeideas.springboot.app.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.app.models.dao.IClientesDao;
import com.bolsadeideas.springboot.app.models.entity.Cliente;

import jakarta.transaction.Transactional;

@Service
public class ClienteServicesImp implements IClienteServices {
	
	@Autowired
	private IClientesDao clientesDao;

	@Override
	@Transactional()
	public List<Cliente> findAll() {
		return (List<Cliente>) clientesDao.findAll();
	}

	@Override
	@Transactional()
	public void save(Cliente cliente) {
		clientesDao.save(cliente);
		
	}

	@Override
	@Transactional()
	public Cliente findOne(Long id) {
		return clientesDao.findById(id).orElse(null);
	}

	@Override
	@Transactional()
	public void delete(Long id) {
		clientesDao.deleteById(id);
		
	}

}
