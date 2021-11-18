package com.empleados.S402.nivel2.model.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.empleados.S402.nivel2.model.DTO.EmployeeDTO;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public void uploadPhoto(EmployeeDTO dto, MultipartFile photo) throws IOException {

			File file = new File("src/main/resources/static/uploads/empPhotos");

			String path = file.getAbsolutePath() + "/" + dto.getPk_ID() + ".jpg";

			FileOutputStream output;
			try {
				output = new FileOutputStream(path);
				output.write(photo.getBytes());
				output.close();
				dto.setPhoto();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	@Override
	public void downloadPhoto(File photo, String folder, String fileName) throws IOException {
		
		File dir = new File("src/main/resources/static/downloads/" + folder.toLowerCase());
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		String pathPhoto = "src/main/resources/static" + photo.getAbsolutePath();
		String pathEndFile = dir.getAbsolutePath()  +"/"+ fileName.toLowerCase() +".jpg";
		
		try {
			InputStream originFile = new FileInputStream(pathPhoto);
			OutputStream endFile = new FileOutputStream(pathEndFile);
			
			byte[] buf = new byte[1024];
			int len;
			
			while((len = originFile.read(buf)) > 0) {
				endFile.write(buf, 0, len);
			}
			
			originFile.close();
			endFile.close();
		
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

}
