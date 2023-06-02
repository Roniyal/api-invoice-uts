package com.api.invoice.uts.controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.invoice.uts.models.entities.Cliente;
import com.api.invoice.uts.models.entities.Region;
import com.api.invoice.uts.services.IClienteService;

import jakarta.validation.Valid;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClienteRestController {
	
	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("/clientes")
	public List<Cliente> index(){
		return clienteService.findAll();
	}
	
	@GetMapping("/cliente/{id}")
	public Cliente show(@PathVariable Long id) {
		return clienteService.findById(id);
	}
	
	
	/* CREACION*/
	@PostMapping("/clientes")
	public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result){
		
		Cliente clienteNew=null;
		
		Map<String,Object> response=new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors=result.getFieldErrors()
					.stream()
					.map(err->"El campo"+ err.getField()+""+ err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
		}
		
		try {
			
		clienteService.save(cliente);
	
		
		}catch(DataAccessException e){
			response.put("mensaje", "Error al ingresar los datos en la DB");
			response.put("error", e.getMessage().concat("; ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
		response.put("mensaje","Cliente ha sido creado");
		response.put("cliente", clienteNew);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	/* EDITAR*/
	@PutMapping("/cliente/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente,BindingResult result,@PathVariable Long id){
		
		Cliente currentCliente=this.clienteService.findById(id);
		
		Cliente updateCliente=null;
		
		Map<String, Object> response=new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors=result.getFieldErrors()
					.stream()
					.map(err->"El campo"+ err.getField()+""+ err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
		}
		 if(currentCliente==null) {
			 response.put("mensaje", "Error: No se puede editar, el cliente ID: ".concat(id.toString())
					 .concat(" No existe en la base de datos"));
			 return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		 }
		 
		 try{
			 currentCliente.setNombre(cliente.getNombre());
			 currentCliente.setApellido(cliente.getApellido());
			 currentCliente.setEmail(cliente.getEmail());
			 updateCliente=this.clienteService.save(currentCliente);
		 }catch(DataAccessException e){
				response.put("mensaje", "Error al ingresar los datos en la DB");
				response.put("error", e.getMessage().concat("; ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
				
			response.put("mensaje","Cliente ha sido actualizado con exito");
			response.put("cliente", updateCliente);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		}
	/* BORRAR*/
	@DeleteMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?>delete(@PathVariable Long id){
		Map<String, Object> response=new HashMap<>();
		try {
			Cliente cliente=this.clienteService.findById(id);
			this.clienteService.delete(cliente);
		}catch(DataAccessException e) {
			response.put("mensaje","Error al eliminar el cliente en la base de datos");
			response.put("error", e.getMessage().concat("; ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El cliente eliminado con exito");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}	
	
	@GetMapping("/clientes/regiones")
	public List<Region> listarRegiones(){
		return clienteService.findAllRegiones();
	}
	
}
