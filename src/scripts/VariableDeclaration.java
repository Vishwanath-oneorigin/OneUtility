package scripts;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;


public class VariableDeclaration {
	public static WebDriver driver;
	public static ChromeOptions options;
	public static int browserNumber;
	public static WebDriverWait wait100;
	public static WebDriverWait wait150;
	public static WebDriverWait wait200;
	public static WebDriverWait wait5;
	public static WebDriverWait wait8;
	public static WebDriverWait wait12;
	public static WebDriverWait wait15;
	public static WebDriverWait wait20;
	public static WebDriverWait wait50;
	
	public static FileInputStream fis; 
	public static Workbook wb;
	public static FileOutputStream fos;
	
	public static FileInputStream fis1; 
	public static Workbook wb1;
	public static FileOutputStream fos1;
	
	public static final String APPLICATION_NAME = "Content Comparison";
	public static final GsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	public static final String TOKENS_DIRECTORY_PATH = "tokens";
	public static final List<String> SCOPES = Arrays.asList(SheetsScopes.SPREADSHEETS,SheetsScopes.DRIVE, SheetsScopes.DRIVE_FILE);

	
	protected final static NetHttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	public static String valueInputOption = "RAW";
	public static Object checked;
	public static Sheets service;
	
	// APIs
	public static HttpRequest request;
	public static HttpResponse<String> response ;
	
}

