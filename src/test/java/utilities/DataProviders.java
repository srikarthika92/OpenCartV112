package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	//Data Provider 1
	
	
	@DataProvider(name="loginData")
	public String[][] getData() throws IOException
	{
		String path=".\\testdata\\LoginData.xlsx"; //taking xl file from test data
		
		ExcelReader xlreader=new ExcelReader(path); //creating an object for xlutility
		
		int totalrows=xlreader.getRowCount("Sheet1");
		int totalcells=xlreader.getCellCount("Sheet1", 1);
		System.out.println("totalrows :"+totalrows); //5 becoz excel counts rows from 0
		System.out.println("totalcells :"+totalcells);//3 becoz excel counts cells from 1
		
		String data[][]=new String[totalrows][totalcells]; //created 2 dimention array to store data
		
		for(int i=1;i<=totalrows;i++) //totalrows=5|becoz we starts from 1 skipping first row(0)|
			                           //we are using<= so that it'll fetch data from 5 rows
		{
			for(int j=0;j<totalcells;j++)//totalcells=3|java counts from 0 we need to stop by 3 rows
				                         //so we use <totalcells
			{
				data[i-1][j]=xlreader.getCellData("Sheet1", i, j);
				
			}
		}
		return data; //returning two dimention array
	}

	
	

}
