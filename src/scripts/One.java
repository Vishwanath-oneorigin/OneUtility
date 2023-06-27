package scripts;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.GeneralSecurityException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;

import io.github.bonigarcia.wdm.WebDriverManager;
import okhttp3.Cookie;
import okhttp3.Response;
public class One extends VariableDeclaration{
	String data;
	/**
	 * Generic method to launch headless chrome browser
	 * @param browser (1 = Windows Chrome, 2 = MS Edge, 3 = Mozilla Firefox, 4 = Safari)
	 * @param hdls 
	 * if hdls="headless" : headless browser
	 * if hdls="" : Non headless browser
	 * @return 
	 * @return 
	 */
	public WebDriver initializeBrowser(int browser, String hdls)
	{
		WebDriverManager.chromedriver().setup();
		options=new ChromeOptions();
		options.addArguments("headless");
		options.addArguments("window-size=1920,1080");

		if(hdls.trim().equalsIgnoreCase("headless"))
			driver = new ChromeDriver(options);

		else
			driver = new ChromeDriver();

		wait100 = new WebDriverWait(driver,100);
		wait150 = new WebDriverWait(driver,150);
		wait200 = new WebDriverWait(driver,200);
		wait5= new WebDriverWait(driver,5);
		wait8= new WebDriverWait(driver,8);
		wait12= new WebDriverWait(driver,12);
		wait15= new WebDriverWait(driver,15);
		wait20= new WebDriverWait(driver,20);
		wait50= new WebDriverWait(driver,50);
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

	/**
	 * Generic method to open the browser
	 * @param browser
	 * @return 
	 * @return 
	 */
	public WebDriver initializeBrowser(int browser)
	{

		if(browser==1) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			wait100 = new WebDriverWait(driver,100);
			wait150 = new WebDriverWait(driver,150);
			wait200 = new WebDriverWait(driver,200);
			wait5= new WebDriverWait(driver,5);
			wait8= new WebDriverWait(driver,8);
			wait12= new WebDriverWait(driver,12);
			wait15= new WebDriverWait(driver,15);
			wait20= new WebDriverWait(driver,20);
			wait50= new WebDriverWait(driver,50);
		}
		else if(browser==2) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			wait100 = new WebDriverWait(driver,100);
			wait150 = new WebDriverWait(driver,150);
			wait200 = new WebDriverWait(driver,200);
			wait5= new WebDriverWait(driver,5);
			wait8= new WebDriverWait(driver,8);
			wait12= new WebDriverWait(driver,12);
			wait15= new WebDriverWait(driver,15);
			wait20= new WebDriverWait(driver,20);
		}

		else if(browser==3) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			wait100 = new WebDriverWait(driver,100);
			wait150 = new WebDriverWait(driver,150);
			wait200 = new WebDriverWait(driver,200);
			wait5= new WebDriverWait(driver,5);
			wait8= new WebDriverWait(driver,8);
			wait12= new WebDriverWait(driver,12);
			wait15= new WebDriverWait(driver,15);
			wait20= new WebDriverWait(driver,20);
		}
		else if(browser==4) {
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
			wait100 = new WebDriverWait(driver,100);
			wait150 = new WebDriverWait(driver,150);
			wait200 = new WebDriverWait(driver,200);
			wait5= new WebDriverWait(driver,5);
			wait8= new WebDriverWait(driver,8);
			wait12= new WebDriverWait(driver,12);
			wait15= new WebDriverWait(driver,15);
			wait20= new WebDriverWait(driver,20);
		}
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

	public void openWebsite(String url)
	{
		driver.get(url);
	}

	public void closeBrowser()
	{
		driver.quit();
	}

	public String perform(String action, String elementPath, String writeData)
	{
		switch(action)
		{
		case "click":
			driver.findElement(By.xpath(elementPath)).click();
			break;
		case "clear":
			driver.findElement(By.xpath(elementPath)).clear();
			break;
		case "getText":
			data=driver.findElement(By.xpath(elementPath)).getText();
			break;
		case "write":
			driver.findElement(By.xpath(elementPath)).sendKeys(writeData);
		case "submit":
			driver.findElement(By.xpath(elementPath)).submit();
		case "getPageTitle":
			data=driver.getTitle();
		case "currentUrl":
			data=driver.getCurrentUrl();
		case "refresh":
			driver.navigate().refresh();
		case "back" :
			driver.navigate().back();
		case "forward":
			driver.navigate().forward();
		}
		return data;
	}


	public void WaitUntil(String element, String elementPath)
	{
		Waiter:
		{
			if(element=="url")
			{
				wait15.until(ExpectedConditions.urlToBe(element));
				break Waiter;
			}
			wait15.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementPath)));
		}
	}

	/**
	 * @author Vishwanath
	 * @param data
	 * @throws AWTException
	 * @throws InterruptedException
	 */
	public static void paste(String data) throws AWTException, InterruptedException
	{
		StringSelection s;
		s= new StringSelection(data);
		Clipboard cp =Toolkit.getDefaultToolkit().getSystemClipboard();
		cp.setContents(s, null);
		Thread.sleep(2000);
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
	}

	public static void initializeReadExcelSheets(String inputFilePath) throws EncryptedDocumentException, IOException
	{

		fis = new FileInputStream(inputFilePath);
		wb = WorkbookFactory.create(fis);
		fos = new FileOutputStream(inputFilePath);
	}

	public static void initializeWriteExcelSheets(String inputFilePath) throws EncryptedDocumentException, IOException
	{
		fis1 = new FileInputStream(inputFilePath);
		wb1 = WorkbookFactory.create(fis1);
		fos1 = new FileOutputStream(inputFilePath);

	}


	/**
	 * @author Vishwanath
	 * Generic method to fetch data from Excel sheet
	 * @param sheetname
	 * @param rownum
	 * @param cellnum
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public static String getExcelData(String sheetname, int rownum, int cellnum) throws EncryptedDocumentException, IOException
	{
		String value=null;
		try {
			value= wb.getSheet(sheetname).getRow(rownum).getCell(cellnum).getStringCellValue();
		}
		catch(Exception e)
		{
			value=wb.getSheet(sheetname).getRow(rownum).getCell(cellnum).toString();
		}
		return value;	
	}
	/**
	 * @author Vishwanath
	 * Generic method to write data to Excel sheet
	 */
	public static void setExcelData(String sheetname, int rownum, int cellnum, String value) throws EncryptedDocumentException, IOException
	{
		wb1.getSheet(sheetname).getRow(rownum).getCell(cellnum).setCellValue(value);
	}
	/**
	 * @author Vishwanath
	 * Generic method to save the excel data
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	public static void saveReport() throws IOException, InterruptedException
	{
		try {
			//copying data from data sheet
			wb.write(fos);
			wb.close();
		}
		catch(Exception e) {
		}
		try {
			// Writing results to index sheet
			wb1.write(fos1);
			wb1.close();
		}
		catch(Exception e) {
		}
	}

	/**
	 * @author Vishwanath
	 * @return
	 * @throws InterruptedException
	 */
	public boolean check404Error() throws InterruptedException
	{
		boolean error=false;
		Thread.sleep(1000);
		String title=driver.getTitle();
		if(title.contains("404") | title.startsWith("Page not found") | title.startsWith("Site under main"))
			error=true;
		return error;
	}

	/**
	 * @author Vishwanath
	 * @return
	 */
	public synchronized static Timestamp getTime()
	{
		Date date = new Date();
		return new Timestamp(date.getTime());
	}

	/**
	 * @author Vishwanath
	 * @param myAccountEmail
	 * @param password
	 * @param recepients
	 * @param msgSubject
	 * @param body
	 * @param attachment1Path
	 * @param attachment1Name
	 * @param attachment2Path
	 * @param attachment2Name
	 * @throws Exception
	 */
	public synchronized static void sendMail(String myAccountEmail, String password, String recepients, String msgSubject , String body, String attachment1Path, String attachment1Name, String attachment2Path,String attachment2Name) throws Exception
	{ 
		Properties properties= new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.starttls.required", "true");
		properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccountEmail, password);
			}
		});

		Message message= prepareMessage(session, myAccountEmail, recepients);
		message.setSubject(msgSubject);
		BodyPart msgBodyPart = new MimeBodyPart();
		msgBodyPart.setText(body);

		MimeBodyPart messageBodyPart2 = new MimeBodyPart(); 


		File f1 = new File(attachment1Path);
		String path1=f1.getAbsolutePath();
		messageBodyPart2.attachFile(path1);
		messageBodyPart2.setFileName(attachment1Name);

		MimeBodyPart messageBodyPart3 = new MimeBodyPart();  
		File f2 = new File(attachment2Path);
		String path2=f2.getAbsolutePath();
		messageBodyPart3.attachFile(path2);
		messageBodyPart3.setFileName(attachment2Name);

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart2);
		multipart.addBodyPart(msgBodyPart);
		multipart.addBodyPart(messageBodyPart3);

		message.setContent(multipart);
		Transport.send(message);

		System.out.println("Message sent successfully");
	}

	private static Message prepareMessage(Session session, String myAccountEmail, String recepients)  
	{
		try {
			Message  message= new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccountEmail));

			String[] rcpts = recepients.split(",");

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(rcpts[0].trim()));
			for(int m=1; m<rcpts.length;m++)
			{
				message.addRecipient(Message.RecipientType.CC, new InternetAddress(rcpts[m].trim()));
			}

			return message;
		}
		catch(Exception e)
		{}
		return null;
	}

	/**
	 * @author Vishwanath
	 * Generic method to generate random Numbers
	 * @param from
	 * @param to
	 * @param count
	 * @return
	 */
	public static ArrayList<Integer> getRandomNumber(int from, int to, int count) {
		Random r = new Random();
		Set<Integer> list = new TreeSet<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		int number;
		while (list.size() < count) {
			while (true) {
				number = r.nextInt(to + 1);
				if (number >= from)
					break;
			}
			list.add(number);
		}
		list2.addAll(list);
		Collections.sort(list2);
		return list2;
	}


	/**
	 * Generic method to scroll down the webpage
	 * @param length
	 */
	public void scrollDown(int length)
	{
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,"+length+")", "");
	}
	/**
	 * Generic method to scroll up the webpage
	 * @param length
	 */
	public void scrollUp(int length)
	{
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,"+-length+")", "");
	}

	/**
	 * @author Vishwanath
	 * @param s
	 * @param t
	 * @throws InterruptedException
	 */
	public static void slowEffect(String s, int t) throws InterruptedException
	{
		Reporter.log(s);
		for(int i=0; i<s.length();i++) 
		{
			char ch=s.charAt(i);
			System.out.print(ch);
			Thread.sleep(t);
		}
		System.out.println();
	}

	/**
	 * @author Vishwanath
	 * Generic method to fetch the absolute paths of the all the files in a given folder
	 * @param folderPath
	 * @return
	 * @throws IOException
	 */
	public static ArrayList<String> fetchFiles(String folderPath) throws IOException
	{
		ArrayList<String> filesPath = new ArrayList<String>();
		//From a folder 
		File f = new File(folderPath);
		String absPath=f.getAbsolutePath();

		File[] files = new File(absPath).listFiles();
		for (File file : files) {
			if (file.isFile()) {
				filesPath.add(absPath.concat(file.getName()));
			}
		}
		return filesPath;

	}
	/**
	 * @author Vishwanath
	 * Generic method to remove non digits from a String
	 * @param s
	 * @return
	 */
	public static String removeNonDigits(String s) {
		if (s == null || s.length() == 0) {
			return "";
		}
		return s.replaceAll("\\D+", "");
	}


	public static Map readFromGS(String CREDENTIALS_FILE_PATH, String readSpreadsheetId, String readRange, int columnNum) throws IOException, GeneralSecurityException
	{
		Map texts = new HashMap();
		// Build a new authorized API client service.
		try {
			Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(CREDENTIALS_FILE_PATH, HTTP_TRANSPORT))
					.setApplicationName(APPLICATION_NAME)
					.build();

			ValueRange response = service.spreadsheets().values()
					.get(readSpreadsheetId, readRange)
					.execute();


			List<List<Object>> values = response.getValues();
			if (values == null || values.isEmpty()) {
				System.out.println("No data found.");
			}
			else {
				String text;
				for (int v=1; v<values.size(); v++) {
					try {
						text=(String)values.get(v).get(columnNum);
						texts.put(v+1,text);
					}
					catch(Exception e) {
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return texts;
	}

	public static void writeToGSheets(final String CREDENTIALS_FILE_PATH, String writeSpreadsheetId, String data,String overWriteRange ) throws IOException, GeneralSecurityException
	{
		checked= new Object();
		service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(CREDENTIALS_FILE_PATH, HTTP_TRANSPORT)).setApplicationName(APPLICATION_NAME).build();
		checked = data;
		{
			service.spreadsheets().values().update(writeSpreadsheetId, overWriteRange, new ValueRange().setValues(Collections.singletonList(Arrays.asList(checked))))
			.setValueInputOption(valueInputOption)
			.execute();
		}
	}

	/**
	 * Creates an authorized Credential object.
	 * @param HTTP_TRANSPORT The network HTTP Transport.
	 * @return An authorized Credential object.
	 * @throws IOException If the credentials.json file cannot be found.
	 */
	public static Credential getCredentials(final String CREDENTIALS_FILE_PATH, NetHttpTransport HTTP_TRANSPORT) throws IOException 
	{
		// Load client secrets.
		FileInputStream in = new FileInputStream(CREDENTIALS_FILE_PATH);
		if (in == null)
		{
			throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
		}
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
				HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
				.setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
				.setAccessType("offline")
				.build();
		LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
		return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
	}

	public String getAPI(String uri)
	{
		request = HttpRequest.newBuilder()
				.uri(URI.create(uri))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		response = null;
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(response.body());
		return response.body();
	}

	public String getAPI(String uri, String authorizationTypeAndValue)
	{
		request = HttpRequest.newBuilder()
				.uri(URI.create(uri))
				.header("Authorization", authorizationTypeAndValue)
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();

		response = null;
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(response.body());
		return response.body();
	}

	public String getAPI(String uri, String authorizationTypeAndValue, String clientIdType, String clientIdValue) throws IOException
	{
		request = HttpRequest.newBuilder()
				.uri(URI.create(uri))
				.header(clientIdType, clientIdValue)
				.header("Authorization", authorizationTypeAndValue)
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();

		response = null;
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(response.body());
		return response.body();
	}


	public void postAPI(String uri) throws Exception
	{
		JSONObject values = new JSONObject();
		values.put("accnt_key", "oneorigin");
		values.put("email", "mrudula-sa-jku@oneorigin.us");
		values.put("password","Tester@1");

		HttpPost request = new HttpPost(uri);

		CloseableHttpClient httpClient = HttpClientBuilder.create().build();

		org.apache.hc.core5.http.io.entity.StringEntity params = new org.apache.hc.core5.http.io.entity.StringEntity(values.toString());
		request.addHeader("content-type", "application/json");
		request.setEntity(params);
		Object response = httpClient.execute(request);
		System.out.println(response.toString());

	}

	public void copyPaste(String source, String dest)
	{
		File s = new File(source);
		File d = new File(dest);
		try {
			FileUtils.copyFileToDirectory(s,d);
		} catch (IOException e) {}

	}

	public String POSTLogin(String uri , String body) throws IOException   
	{  
		System.out.println(body);  
		String url = uri;  
		String token = null;
		URL urlObj = new URL(url);  


		HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();  
		connection.setRequestMethod("POST");  
		connection.setRequestProperty("Content-Type", "application/json");  
		connection.setDoOutput(true);  

		OutputStream osObj = connection.getOutputStream();  
		osObj.write(body.getBytes());  
		osObj.flush();  
		osObj.close(); 

		token = connection.getHeaderField("Set-cookie");
		
		int statusCode = connection.getResponseCode();  
		System.out.println("Response from the server is:");  
		System.out.println("The POST Request Response Code :  " + statusCode);  
		System.out.println("The POST Request Response Message : " + connection.getResponseMessage()); 

		try {
			InputStreamReader irObj = new InputStreamReader(connection.getInputStream());   
			BufferedReader br = new BufferedReader(irObj);  
			String input = null;  
			StringBuffer sb = new StringBuffer();  
			while ((input = br .readLine()) != null)   
			{  
				sb.append(input);  
			}   
			br.close();  
			connection.disconnect();  
			// printing the response  
			System.out.println(sb.toString());  
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return token;
	}   

	public void POSTtpDetails(String uri , String body, String token) throws IOException   
	{  
		System.out.println(body);  
		String url = uri;  
		URL urlObj = new URL(url);  
		HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();  
		connection.setRequestMethod("POST");  
		String myCookie = token;
		connection.setRequestProperty("Cookie", myCookie);  
		connection.setRequestProperty("Content-Type", "application/json");  
		connection.setDoOutput(true);  

		OutputStream osObj = connection.getOutputStream();  
		osObj.write(body.getBytes());  
		osObj.flush();  
		osObj.close(); 

		int statusCode = connection.getResponseCode();  
		System.out.println("Response from the server is:");  
		System.out.println("The POST Request Response Code :  " + statusCode);  
		System.out.println("The POST Request Response Message : " + connection.getResponseMessage()); 

		try {
			InputStreamReader irObj = new InputStreamReader(connection.getInputStream());   
			BufferedReader br = new BufferedReader(irObj);  
			String input = null;  
			StringBuffer sb = new StringBuffer();  
			while ((input = br .readLine()) != null)   
			{  
				sb.append(input);  
			}   
			br.close();  
			connection.disconnect();  
			// printing the response  
			System.out.println(sb.toString());  
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	} 
}

