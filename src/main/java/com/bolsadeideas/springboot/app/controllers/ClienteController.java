package com.bolsadeideas.springboot.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.services.IClienteServices;

import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class ClienteController {
	
	@Autowired
	@Qualifier("ClientesDaoJPA")
	private IClienteServices clienteServices;
	
	
	@RequestMapping(value="/listar", method=RequestMethod.GET)
	public String listar(Model model) {
		
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes", clienteServices.findAll());
		
		return "listarClientes";
	}
	
	@RequestMapping(value="/form")
	public String crear(Map<String, Object> model) {
		
		Cliente cliente = new Cliente();
		
		model.put("titulo", "Formulario para guardar cliente en DB");
		model.put("cliente", cliente);
		model.put("btnName", "Crear cliente");
		
		return "form";
	}
	
	@RequestMapping(value="/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model) {
		
		Cliente cliente = null;
		
		if(id>0) {
			cliente = clienteServices.findOne(id);
		}else {
			return "redirect:listar";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Formulario de edicion de cliente");
		model.put("btnName", "Editar cliente");
		
		return "form";
	}
	
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario para guardar cliente en DB");
			model.addAttribute("btnName", "Crear Cliente");
			return "form";
		}
		
		try {
			clienteServices.save(cliente);
			System.out.print(cliente.getApellido());
			System.out.print(cliente.getNombre());
		}catch(Exception ex) {
			System.out.print(ex.getMessage());
		}
		
		
		return "redirect:listar";
	}
	
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id, Map<String, Object> model) {
		
		if(id>0) {
			clienteServices.delete(id);
		}else {
			return "redirect:listar";
		}
		
		return "redirect:/listar";
	}
	
	

}
