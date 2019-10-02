package com.inmetrics.controler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.inmetrics.domain.Sites;

public class FeederControler {

	public static Logger logger = Logger.getLogger(FeederControler.class);

	public static List<Sites> lerExcel(String fileName) {
		logger.info("Starting excel");

		File arquivo = new File(fileName);
		Workbook workbook = null;

		try {
			if (StringUtils.endsWithIgnoreCase(arquivo.getName(), "XLS")) {
				workbook = WorkbookFactory.create(arquivo);
			} else {
				logger.info("XLS file does not exist");
			}
			final Sheet sheet = workbook.getSheetAt(0);

			List<Sites> sites = new ArrayList<>();

			for (Row row : sheet) {

				Cell cellName = sheet.getRow(row.getRowNum()).getCell(0);
				Cell cellUrl = sheet.getRow(row.getRowNum()).getCell(1);

				if (null != cellName && null != cellUrl) {
					String name = cellName.getStringCellValue();
					String address = cellUrl.getStringCellValue();

					Sites site = new Sites(name, address);

					sites.add(site);

					logger.info(name + address);
				}

			}

			return sites;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}
