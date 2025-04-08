package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader
{
	public FileInputStream fis;
	public FileOutputStream fos;
	public XSSFWorkbook wb;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;
	
	public ExcelReader(String path)
	{
		this.path=path;
	}

	public int getRowCount(String sheetName) throws IOException 
	{
	    fis=new FileInputStream(path);
		wb=new XSSFWorkbook(fis);
		sheet=wb.getSheet(sheetName);
		int rowNum=sheet.getLastRowNum();
		wb.close();
		fis.close();
		return rowNum;
		
	}
	
	public int getCellCount(String sheetName,int rowNum) throws IOException
	{
		fis=new FileInputStream(path);
		wb=new XSSFWorkbook(fis);
		sheet=wb.getSheet(sheetName);
		row=sheet.getRow(rowNum);
		int cellNum= row.getLastCellNum();
		wb.close();
		fis.close();
		return cellNum;
		
	}
	
	public String getCellData(String sheetName,int rownum,int cellnum) throws IOException
	{
		fis=new FileInputStream(path);
		wb=new XSSFWorkbook(fis);
		sheet=wb.getSheet(sheetName);
		row=sheet.getRow(rownum);
		cell=row.getCell(cellnum);
		
		DataFormatter formatter=new DataFormatter();
		String data;
		try 
		{
			data=formatter.formatCellValue(cell);
		}
		catch(Exception e)
		
		{
			data="";
		}
		wb.close();
		fis.close();
		return data;
	}	
	
	public void setCellData(String sheetName,int rowNum,int cellNum,String data) throws IOException
	
	{
		File xlfile=new File(path);
		if(!xlfile.exists()) // if file not exist then create new file
		{
			wb=new XSSFWorkbook();
			fos=new FileOutputStream(path);
			wb.write(fos);	
		}
		
		fis=new FileInputStream(path);
		wb=new XSSFWorkbook(fis);
		
		if(wb.getSheetIndex(sheetName)==-1)  //if sheet not exist then create new sheet
		wb.createSheet(sheetName);
		sheet=wb.getSheet(sheetName);
		
		if(sheet.getRow(rowNum)==null)   //if row not exist then create new row
		sheet.createRow(rowNum);
		row=sheet.getRow(rowNum);
		
		
		cell=row.getCell(cellNum);
		cell.setCellValue(data);  
		fos=new FileOutputStream(path);
		wb.write(fos);
		wb.close();
		fos.close();
	}
	
	
	
	
	
	
	
	
	
	
}
