package subscriber;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Upload {
	WebDriver driver;


	@BeforeTest
		public void setup() 
		{
		    System.setProperty("webdriver.chrome.driver","E:\\chromedriver.exe");
		    driver=new ChromeDriver();
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		    driver.manage().window().maximize();
		    driver.get("https://playactio.com/auth/login");
		} 
	@Test(dataProvider="getData", priority=0)
	public void Login(String uname, String pwd) throws InterruptedException
	{
		//System.out.println("Welcome");
		Thread.sleep(1000);
		
		driver.findElement(By.xpath(".//input[@name='userName']")).clear();
		driver.findElement(By.xpath(".//input[@name='userName']")).sendKeys(uname);
		Thread.sleep(1000);
		
	    driver.findElement(By.xpath(".//input[@name='password']")).clear();
	    driver.findElement(By.xpath(".//input[@name='password']")).sendKeys(pwd);
		
		driver.findElement(By.xpath("//button['Login\']")).click();
		
	  // assert driver.getPageSource().contains(msg);
	}

	@Test(priority=1)
	public void upload() throws InterruptedException {
		//click Upload
		driver.findElement(By.xpath(".//div[@class='ps-content']//span[@class='pcoded-mtext ng-tns-c51-1 ng-star-inserted']")).click();
		//Click Subscriber Import
		driver.findElement(By.xpath(".//app-nav-item//li[@class='ng-star-inserted']//a[' Subscriber Import ']")).click();
		//Click Download
		driver.findElement(By.xpath(".//div['_ngcontent-seg-c109']//button[' Download CSV ']")).click();
		// Upload CSV
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//div['custom-file']//input[@name='myFile']")).sendKeys("C:\\Users\\dell\\Desktop\\File\\ACTIO_Bulk.csv");
		//Click Save
		driver.findElement(By.xpath(".//div[@class='table-responsive text-nowrap ng-star-inserted']//div[@align='center']//button")).click();
		
		String expectedMessage = "Bulk Subscriber Upload";
		String message = driver.findElement(By.xpath(".//div[@class='toast-text ng-star-inserted']//span")).getText();
		//System.out.println(""+message);
		if(message.equals(expectedMessage)) {
			System.out.println("Bulk Subscriber Upload successfully");
		}
		else {
			System.out.println("Error");
		}
		//Assert.assertTrue(message.contains(expectedMessage));
		
			}
	
	@Test(priority=2)
	public void subscriber_Test() throws InterruptedException {
		//click Subscriber
				driver.findElement(By.xpath("//*[@id='nav-ps-elite']//app-nav-collapse[3]//span[2]")).click();
		//click Subscriber_List		
				driver.findElement(By.xpath("//app-nav-collapse[3]//app-nav-item//li//a")).click();
				String today = getCurrentDay();
				System.out.println("Today's number: " + today + "\n");
		//click From Date		
				driver.findElement(By.xpath("/html/body/app-root/app-admin/div/div/div/div/div/div/app-subscriberlist/div/div/app-card/div/div[2]/div/div/form/div/div[1]/div/div/div/span")).click();
				WebElement dateWidgetFrom = driver.findElement(By.xpath("/html/body/app-root/app-admin/div/div/div/div/div/div/app-subscriberlist/div/div/app-card/div/div[2]/div/div/form/div/div[1]/div/div/ngb-datepicker/div[2]/div/ngb-datepicker-month"));
				List<WebElement> columns = dateWidgetFrom.findElements(By.tagName("td"));
			List<WebElement> rows = dateWidgetFrom.findElements(By.tagName("tr"));
				 for (WebElement cell: columns){
					   if (cell.getText().equals("9")) {
						   cell.click();
			                break;
					 }}
				//click To Date		
					driver.findElement(By.xpath("/html/body/app-root/app-admin/div/div/div/div/div/div/app-subscriberlist/div/div/app-card/div/div[2]/div/div/form/div/div[2]/div/div/div")).click();
					WebElement todateWidgetFrom = driver.findElement(By.xpath("/html/body/app-root/app-admin/div/div/div/div/div/div/app-subscriberlist/div/div/app-card/div/div[2]/div/div/form/div/div[2]/div/div/ngb-datepicker/div[2]/div/ngb-datepicker-month"));
					List<WebElement> columns2 = todateWidgetFrom.findElements(By.tagName("td"));
				List<WebElement> rows2 = dateWidgetFrom.findElements(By.tagName("tr"));
					 for (WebElement cell: columns2){
						   if (cell.getText().equals("9")) {
							   cell.click();
				                break;
						 }}		 
				 
				 
				 try {
			            Thread.sleep(2000);
			        } catch (InterruptedException e) {
			            e.printStackTrace();
			        }
			        
//				driver.findElement(By.xpath(".//div[@class='input-group']//input[@name='fromDate']")).sendKeys("09-01-2021");
//		//click To Date		
//				driver.findElement(By.xpath(".//div[@class='input-group']//input[@name='toDate']")).click();	
//				Thread.sleep(2000);
//				driver.findElement(By.xpath(".//div[@class='input-group']//input[@name='toDate']")).sendKeys("09-01-2021");			
	}
	
	private String getCurrentDay (){
        //Create a Calendar Object
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        //Get Current Day as a number
        int todayInt = calendar.get(Calendar.DAY_OF_MONTH);
        //System.out.println("Today Int: " + todayInt +"\n");
        //Integer to String Conversion
        String todayStr = Integer.toString(todayInt);
        //System.out.println("Today Str: " + todayStr + "\n");
        return todayStr;
    }
	
	@DataProvider
	public Object[][] getData(){
		return new Object[][] {
				{"admin", "admins"}
		};
		//return details;
	}}
