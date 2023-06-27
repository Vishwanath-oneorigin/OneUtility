package graphs;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

import scripts.One;
public class Grapher{
	static ArrayList<String> words = new ArrayList<String>();
	static ArrayList<String> time = new ArrayList<String>();
	static ArrayList<String> percent = new ArrayList<String>();

	public static void main(String[] args) throws IOException, InterruptedException, GeneralSecurityException{
		One one = new One();
		String data;
		one.copyPaste("./directory/Vishwa-graph.xlsx", "./data");
		one.copyPaste("./directory/Vishwa-graph.xlsx", "./report/");
		
		

		
		
		
		
		
		
		
		
		
		
		
		
		one.initializeReadExcelSheets("./data/Vishwa-graph.xlsx");
		one.initializeWriteExcelSheets("./report/Vishwa-graph.xlsx");
		int lastRow=one.wb.getSheet("Sheet1").getLastRowNum();

		for(int row=0; row<lastRow; row++)
		{
			data=one.getExcelData("Sheet1", row, 0);
			if(!data.equals("") && data!=null)
			{
				getTimeAndPercent(data);
			}
		}

		for(int w=0; w<time.size(); w++)
		{
			one.setExcelData("Sheet2", w+1, 0, time.get(w));
			one.setExcelData("Sheet2", w+1, 1, percent.get(w));

		}
		System.out.println(time);
		System.out.println(percent);
		one.saveReport();
	}

	public static void getTimeAndPercent(String data)
	{
		String[] s=data.split(" ");
		String s1;
		String t;
		String p;
		words.clear();

		for(int i=0; i<s.length;i++)
		{
			s1=s[i];
			s1=s1.replaceAll(" ", "");
			if(!s1.equals("") && s1!=null)
			{
				words.add(s1);
			}
		}
		if(words.size()==8)
		{
			adding:
			{
				t=words.get(0);
				p=words.get(7);
				if(isNumber(t) && isNumber(p))
				{
					time.add(t);
					percent.add(p);
				}

			}
		}
	}

	public static boolean isNumber(String s)
	{
		s=s.replaceAll(":", "").replaceAll("\\.", "");
		try {
			Integer.parseInt(s);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
}















