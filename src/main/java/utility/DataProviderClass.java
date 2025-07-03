package utility;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviderClass {
	@DataProvider (name="loginData")
	public Object[][] loginData() throws IOException {
		return ExcelUtility.getDataFromExcel("Sheet1");
	}

}
