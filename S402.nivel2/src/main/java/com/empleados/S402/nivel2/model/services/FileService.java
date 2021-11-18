package com.empleados.S402.nivel2.model.services;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.empleados.S402.nivel2.model.DTO.EmployeeDTO;




public interface FileService {

	void uploadPhoto(EmployeeDTO dto, MultipartFile photo) throws IOException;
		
	void downloadPhoto(File photo, String folder, String fileName) throws IOException;

}
