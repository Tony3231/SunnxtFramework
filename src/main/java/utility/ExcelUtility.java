package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	public static Object[][] getDataFromExcel(String SheetName) throws IOException{
		
		FileInputStream fs = new FileInputStream("C:\\Users\\arunachalam.d\\eclipse-workspace\\TestNGFramework\\testdata\\test-data.xlsx");
		Workbook workbook = WorkbookFactory.create(fs);
		Sheet sheet = workbook.getSheet(SheetName);
		int rowsCount = sheet.getPhysicalNumberOfRows();
		int columnCount = sheet.getRow(0).getLastCellNum();
		
		Object[][] data= new Object[rowsCount-1] [columnCount];
		for(int i=1;i<rowsCount;i++) {
			Row row = sheet.getRow(i);
			for(int j=0;j<columnCount;j++) {
				
				Cell cell = row.getCell(j);
				if(cell == null) {
					data[i-1][j]="";
				}
				else if(cell.getCellType() ==CellType.STRING) {
					data[i-1][j]=cell.getStringCellValue();
				}else if(cell.getCellType() == CellType.NUMERIC) {
					data[i-1][j]=String.valueOf(((Double) cell.getNumericCellValue()).longValue());
				}else {
					data[i-1][j]= cell.toString();
				}
			}
			
		}
		workbook.close();
		fs.close();
		return data;
	
	}

}
