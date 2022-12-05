package com.example.demo.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.payload.response.MessageResponse;
import com.example.demo.payload.response.PricesList;
import com.example.demo.repository.BuyRateRepository;
import com.example.demo.services.FileServices;
import com.example.demo.utils.FileAsyn;
import com.example.demo.utils.FilecheckingUtils;

@Service
public class FileServicesImp implements FileServices {

	@Autowired
	BuyRateRepository buyRateRepository;

	@Autowired
	FilecheckingUtils filecheckingUtils;

	@Autowired
	Environment env;

	@Autowired
	FileAsyn asyn;
	
	@Override
	public ResponseEntity<?> uploadBuyPrice(MultipartFile file) {
		boolean check = FilecheckingUtils.hasExcelFormat(file);
		if (!check) {
			return ResponseEntity.ok(MessageResponse.builder().status(HttpStatus.BAD_REQUEST.value())
					.message(env.getProperty("invalid.format")).httpStatus(HttpStatus.BAD_REQUEST).build());
		}
		List<PricesList> list = buyPriceList(file);
		asyn.buyPriceList(list);
		return null;
	}

	private List<PricesList> buyPriceList(MultipartFile file) {
		List<PricesList> buyList = new ArrayList<>();
		Workbook workbook = null;
		try {
			workbook = WorkbookFactory.create(file.getInputStream());

			workbook.forEach(e -> {
				DataFormatter dataFormatter = new DataFormatter();
				int index = 0;
				for (Row row : e) {
					if (index++ == 0)
						continue;
					PricesList list = new PricesList();
					Date date = row.getCell(0).getDateCellValue();
					list.setDate(date.toString());
					list.setPrice(dataFormatter.formatCellValue(row.getCell(1)));
					buyList.add(list);
				}
			});
		} catch (EncryptedDocumentException | IOException e) {

			e.printStackTrace();
		}
		return buyList;
	}

}
