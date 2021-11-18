package com.empleados.S402.nivel2.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.empleados.S402.nivel2.model.DTO.EmployeeDTO;
import com.empleados.S402.nivel2.model.services.EmployeeService;
import com.empleados.S402.nivel2.model.services.FileService;

@Controller
public class EmployeeController {
	private static final String ID_FORM_DTO = "EmployeeDTOForm";
	private static final String ID_LIST_DTO = "EmployeeDTOList";
	private static final String ID_INFO_DTO = "EmployeeDTOInfo";
	private static final String ID_FILTER_DTO = "EmployeeDTOFilter";

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private FileService fileService;

//LISTADO EMPLEADOS:
	// Responderá a una petición GET y MUESTRA la lista de empleados con sus opciones y el filtro por tipo de trabajo
	@GetMapping("/Empleados")
	public ModelAndView listEmployees() {
		ModelAndView modelAndView = new ModelAndView();
		List<EmployeeDTO> employees = employeeService.getEmployees();
		modelAndView.addObject(ID_LIST_DTO, employees);
		modelAndView.addObject(ID_FILTER_DTO, employees);
		modelAndView.setStatus(HttpStatus.OK);
		modelAndView.setViewName("ListEmployees");
		return modelAndView;
	}
//INFO DE UN EMPLEADO:
	// Responderá a un GET por id y MUESTRA INFO empleado
	@GetMapping("/Empleados/{id}")
	public ModelAndView infoEmployee(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		EmployeeDTO dto = employeeService.getEmployeeById(id);
		if(dto.getPk_ID()!=null) {
			modelAndView.addObject(ID_INFO_DTO, dto);
			modelAndView.setStatus(HttpStatus.OK);
			modelAndView.setViewName("InfoEmployee");
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe ningún empleado con este ID");
		}
		
		return modelAndView;
	}

// CREAR EMPLEADOS:
	// Responderá a un GET  y MUESTRA la vista para crear/Editar empleado
	// desde esa vista, se envia un POST a /empleados/update con los datos para guardar los cambios
	@GetMapping("/Empleados/Nuevos")
	public ModelAndView createEmployee() {
		ModelAndView modelAndView = new ModelAndView();
		EmployeeDTO dto = new EmployeeDTO();
		modelAndView.addObject(ID_FORM_DTO, dto);
		modelAndView.setStatus(HttpStatus.OK);
		modelAndView.setViewName("CreateEmployee");
		return modelAndView;
	}
// EDITAR EMPLEADOS:
	// Responderá a un GET por id y MUESTRA FORMULARIO para cambios
	// esos cambios se envían en un POST a /Empleados/Updates para guardarlos
	@GetMapping("/Empleados/Edicion/{id}")
	public ModelAndView editEmployee(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		EmployeeDTO dto = employeeService.getEmployeeById(id);
		if(dto.getPk_ID()!=null) {
			dto.setPhoto();
			modelAndView.addObject(ID_FORM_DTO, dto);
			modelAndView.setStatus(HttpStatus.OK);
			modelAndView.setViewName("EditEmployee");
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe ningún empleado con este ID");
		}
		return modelAndView;
	}

// GUARDAR CAMBIOS (UPDATES):
	// Responderá a un POST que lleva un dto (recogido en el formulario desde el que se lanza el post)
	// NO MUESTRA NADA, redirecciona a /Empleados después de GUARDAR los cambios
	@PostMapping("/Empleados/Updates")
	public ModelAndView saveEmployee(@ModelAttribute (ID_FORM_DTO) EmployeeDTO dto) {
		ModelAndView modelAndView = null;
		if(dto.getName().equals("")) {
			modelAndView = new ModelAndView();
			modelAndView.setViewName("errorEmptyName");
			modelAndView.setStatus(HttpStatus.BAD_REQUEST);
		}else {
			dto.setPhoto();
			employeeService.saveEmployee(dto);
			modelAndView = new ModelAndView("redirect:/Empleados");
			modelAndView.setStatus(HttpStatus.CREATED);
		}
		return modelAndView;
	}
	
// ELIMINAR EMPLEADOS:
	// Responderá a un GET por id y BORRA al empleado 
	// NO MUESTRA NADA,redirecciona al listado
	@GetMapping("/Empleados/Bajas/{id}")
	public ModelAndView deleteEmployee(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = null;
		EmployeeDTO dto = employeeService.getEmployeeById(id);
		if(dto.getPk_ID()!=null) {
			employeeService.deleteById(id);
			modelAndView = new ModelAndView("redirect:/Empleados");
			modelAndView.setStatus(HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe ningún empleado con este ID");
		}

		return modelAndView;
	}

// FILTRAR LISTADO:
	// Responderá a un POST que lleva un filtro (seleccionado en la vista desde la que se envía el post)
	// MUESTRA la lista de empleados según filtro seleccionado (que recibe por parámetro)
	@PostMapping("/Empleados/Filtros")
	public ModelAndView filterEmployees(@RequestParam("filtroJob") String filtroJob) {
		ModelAndView modelAndView = new ModelAndView();
		List<EmployeeDTO> dtos = null;
		if (filtroJob.equals("ALL"))
			dtos = employeeService.getEmployees();
		else
			dtos = employeeService.getEmployeesByFilter(filtroJob);
		modelAndView.addObject(ID_LIST_DTO, dtos);
		modelAndView.addObject(ID_FILTER_DTO);
		modelAndView.setStatus(HttpStatus.OK);
		modelAndView.setViewName("ListEmployees");
		return modelAndView;
	}

//SUBIR FOTO:
	// Responderá a un GET y MUESTRA explorador para seleccionar el archivo que se quiera subir
	@GetMapping("/Empleados/Edicion/{id}/Uploads")
	public ModelAndView showUPloadForm(@PathVariable ("id") Integer id) {
		EmployeeDTO dto = employeeService.getEmployeeById(id);
		dto.setPhoto();
		ModelAndView mav = new ModelAndView();
		mav.addObject(ID_FORM_DTO, dto);
		mav.setViewName("Uploads");
		mav.setStatus(HttpStatus.OK);
		return mav;
	}



//GUARDAR FOTOS SUBIDAS DE UN EMPLEADO:
	// Responderá a un POST que lleva el archivo seleccionado para subir (el parametro es el multifile que nos llega de la vista, la variable del parametro en la vista se llama photo
	// NO MUESTRA NADA, guarda el archivo pasado por parámetro y se queda en la misma ruta que estaba
	@PostMapping("/Empleados/Fotos")
	public ModelAndView uploadPhoto(@ModelAttribute (ID_FORM_DTO) EmployeeDTO dto, @RequestParam("file") MultipartFile photo) {
		ModelAndView mav = null;
		if(!photo.isEmpty() && (photo.getContentType().equals("image/jpeg"))) {
			try {	
				fileService.uploadPhoto(dto, photo);
				mav = new ModelAndView("redirect:/Empleados/Edicion/"+dto.getPk_ID());
				mav.setStatus(HttpStatus.OK);
			}catch (IOException e) {
				mav = new ModelAndView();
				mav.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
				e.printStackTrace();
			}
		}else {
			mav = new ModelAndView();
			mav.addObject(ID_FORM_DTO, dto);
			mav.setViewName("errorFormat");
			mav.setStatus(HttpStatus.BAD_REQUEST);
		}
		return mav;
		
	}	

////DESCARGAR FOTOS:
	//Responde a un GET que pide la vista de formulario de descargas para seleccionar archivo destino foto
	//MUESTRA el formulario de Downloads
	@GetMapping("/Empleados/{id}/Downloads")
	public ModelAndView showDownloadForm(@PathVariable ("id") Integer id) {
		EmployeeDTO dto = employeeService.getEmployeeById(id);
		ModelAndView mav = new ModelAndView();
		mav.addObject(ID_INFO_DTO, dto);
		mav.setViewName("Downloads");
		mav.setStatus(HttpStatus.OK);
		return mav;
	}
//GUARDAR FOTOS DESCARGADAS EN CARPETA PERSONAL:
	// Responderá a un POST que lleva la foto, el directorio y el nombre donde guardarla
	// NO MUESTRA NADA, guarda la foto pasado por parámetro en downloads y se queda en la misma ruta que estaba	(vista: infoempleados)
	@PostMapping("/Empleados/Downloads")
	public ModelAndView savePhotoToDisk(@ModelAttribute (ID_INFO_DTO) EmployeeDTO dto, @RequestParam("photo") File photo, @RequestParam("folder") String pathFolder, @RequestParam("fileName") String fileName) {
		
		ModelAndView mav = null;
		
		if(photo!=null) {
			try {
				fileService.downloadPhoto(photo, pathFolder, fileName);
				mav = new ModelAndView("redirect:/Empleados/"+dto.getPk_ID());
				mav.setStatus(HttpStatus.OK);
			}catch (IOException e) {
				mav = new ModelAndView();
				mav.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
				e.printStackTrace();
			}
		}else {
			mav = new ModelAndView();
			mav.setViewName("errorFormat");
			mav.setStatus(HttpStatus.BAD_REQUEST);
		}
			
		return mav;
	}
}
