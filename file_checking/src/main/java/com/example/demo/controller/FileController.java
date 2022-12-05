package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.services.FileServices;

@RestController
@RequestMapping("/api")
public class FileController {
	@Autowired
	FileServices fileServices;

	@PostMapping(value = "/checks")
	public String FileChecks(@RequestParam("path") String path, @RequestParam("name") String name) throws IOException {
		File file = new File(path);
		String[] contents = file.list();
		for (int i = 0; i < contents.length; i++) {
			String filename = contents[i];
			String finalPath = path + "\\" + filename;
			File log = new File(finalPath);
			FileReader fr = new FileReader(log);
			String s;
			try (BufferedReader br = new BufferedReader(fr)) {

				while ((s = br.readLine()) != null) {
					if (s.contains(name)) {
						String replace = s.replace(name, "Replaced");
						FileWriter fw = new FileWriter(log);
						fw.write(replace);
						fw.close();
					} else {
						System.out.println("False");
					}
				}
			}
		}
		return "Success";
	}
	
	@PostMapping(value = "/check")
	public String FileCheck(@RequestParam("path") String path, @RequestParam("name") String name) throws FileNotFoundException {
		File file = new File(path);
		String[] contents = file.list();
		for (int i = 0; i < contents.length; i++) {
			String filename = contents[i];
			String finalPath = path + "\\" + filename;
			File log = new File(finalPath);
			FileReader fr = new FileReader(log);
			String s;
			String value = "";
			try (BufferedReader br = new BufferedReader(fr)) {
				while ((s = br.readLine()) != null) {
					value += s;
					value += "\r\n";
				}

				if (value != null) {
				//	String replace = value.replace(name, "Completed!....");
					FileWriter fw = new FileWriter(log);
					String str = Stream.of(value.split("\n"))
			                .filter(s1 -> !s1.contains(name))
			                .collect(Collectors.joining("\n"));

				//	fw.write(replace);
					fw.write(str);
					fw.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		return "Success";
	}
	
	@PostMapping("/upload")
	public ResponseEntity<?>uploadBuyPrice(@RequestParam(name = "file",required = true) MultipartFile file){
		return fileServices.uploadBuyPrice(file);
		
	}
}
