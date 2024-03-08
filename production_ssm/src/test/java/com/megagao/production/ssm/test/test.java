package com.megagao.production.ssm.test;

import cn.hutool.core.util.NumberUtil;
import com.megagao.production.ssm.domain.Custom;
import com.megagao.production.ssm.service.CustomService;
import com.megagao.production.ssm.util.FileUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;


public class test {
	
	@Test
	public void testFile(){
		String oldName = "aaa.jpg";
		String date = new DateTime().toString("yyyy/MM/dd");

		//生成新文件名
		//UUID.randomUUID();
		 String newName = oldName.substring(0,oldName.lastIndexOf("."))+"("+date+")"+oldName.substring(oldName.lastIndexOf("."));
	System.out.println(newName);
	}
	
	@Test
	public void testFile1(){
		FileUtil.deleteFile("F:\\upload\\temp\\file\\"+"新建文本文档(2016-10-05).txt");
	}
	
	@Test
	public void test1() throws Exception{
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext-*.xml");
		CustomService customService = (CustomService)context.getBean("customServiceImpl");
		Custom custom = new Custom();
		custom.setCustomId("1253");
		custom.setCustomName("aaa");
		customService.insert(custom);
	}

	@Test
	public void testFormate(){
		System.out.println(String.format("%04d", 0));
	}

	@Test
	public void testHuTool() throws IOException {
//		ExcelReader reader = ExcelUtil.getReader("/Users/cxdpc/Desktop/房.xlsx", 0);
//		String fileName = "/Users/cxdpc/Desktop/房.xlsx";
//		String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
//		FileInputStream inputStream = new FileInputStream(fileName);
//		Workbook workbook = new XSSFWorkbook(inputStream);
//		Sheet sheet = workbook.getSheetAt(0);
//		int firstRowNum = sheet.getFirstRowNum();
//		int rowStart = firstRowNum + 1;
//		int rowEnd = sheet.getPhysicalNumberOfRows();
//		for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
//			Row row = sheet.getRow(rowNum);
//
//			if (null == row) {
//				continue;
//			}
//
//			Iterator<Cell> iterator = row.iterator();
//			while (iterator.hasNext()){
//				Cell cell = iterator.next();
//				CellType cellType = cell.getCellType();
//				System.out.println(cellType);
//			}
//
//		}

//		BigDecimal add = NumberUtil.add("1", "2");
//		System.out.println(add);
		System.out.println(NumberUtil.parseInt(""));
		System.out.println(NumberUtil.parseInt(null));

//		System.out.println(Integer.parseInt(""));
//		NumberUtil.add()


	}
}
