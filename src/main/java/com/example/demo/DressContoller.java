package com.example.demo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins="*")
public class DressContoller {
	@Autowired
	DressRepo dressRepo;
	
	@GetMapping("/dress/find")
	public Dress findById(@RequestParam int id) {
		
		
		 Dress dress =dressRepo.findById(id).get();
		 
		 dress.setImage(decompressBytes(dress.getImage()));
		 
		 return dress;
		
	}
	
	@PostMapping("/dress/add")
	public String addProduct(@RequestParam ("dietFile") MultipartFile myFile,
			String name,
			String brand,
			String price,
			String discount,
			String size,
			String clothtype) {
		
		try {
			Dress prdImage = new Dress(0,name,brand,price,discount,size,clothtype,
					compressBytes(myFile.getBytes()));
			dressRepo.save(prdImage);		
		}catch(Exception e) {
			
		}
		
		
		
		return "Successfully Added New Product";
		
	}
	
	@GetMapping("/dress/delete")
	public List<Dress> deleteDress(@RequestParam int id){
		
		dressRepo.deleteById(id);
		
		return getAllProducts();
	}
	@GetMapping("/dress/all")
	public List<Dress> getAllProducts(){
		
		List<Dress> drList = new ArrayList<Dress>();
		
		List<Dress> resDrList = dressRepo.findAll();
		Dress dress = null;
		for(int i=0;i<resDrList.size();i++) {
			
			dress = resDrList.get(i);
			
			dress.setImage(decompressBytes(dress.getImage()));
			
			drList.add(dress);
			
		}
		
		
		return drList;
	}
	
	// compress the image bytes before storing it in the database
			public static byte[] compressBytes(byte[] data) {
				Deflater deflater = new Deflater();
				deflater.setInput(data);
				deflater.finish();

				ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
				byte[] buffer = new byte[1024];
				while (!deflater.finished()) {
					int count = deflater.deflate(buffer);
					outputStream.write(buffer, 0, count);
				}
				try {
					outputStream.close();
				} catch (IOException e) {
				}
				System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

				return outputStream.toByteArray();
			}

			// uncompress the image bytes before returning it to the angular application
			public static byte[] decompressBytes(byte[] data) {
				Inflater inflater = new Inflater();
				inflater.setInput(data);
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
				byte[] buffer = new byte[1024];
				try {
					while (!inflater.finished()) {
						int count = inflater.inflate(buffer);
						outputStream.write(buffer, 0, count);
					}
					outputStream.close();
				} catch (IOException ioe) {
				} catch (DataFormatException e) {
				}
				return outputStream.toByteArray();
			}


}
