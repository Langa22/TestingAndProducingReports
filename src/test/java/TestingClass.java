import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Properties;

public class TestingClass {
    static WebDriver webDriver;
    static Properties prop;
    public static ExtentReports extentReporter;
    public static ArrayList<GetDTO> read;
    public static void main(String[] args) throws InterruptedException {
        prop=new Properties();
        try{
            prop.load(TestingClass.class.getClassLoader().getResourceAsStream("resources.properties"));
        }catch(Exception e){
            e.printStackTrace();
        }
        System.setProperty(prop.getProperty("key"),prop.getProperty("driverPath"));
        webDriver=new ChromeDriver();
        webDriver.manage().window().maximize();
        read=ReadCSV.readNamesCSV("src/test/resources/information.csv");
        extentReporter=new ExtentReports();
        getReport();
        for(int i=1;i<read.size();i++){
            ExtentTest test=extentReporter.createTest(read.get(i).getAge()+" "+read.get(i).getId());
            webDriver.get(prop.getProperty("url"));
            WebElement search= webDriver.findElement(By.name(prop.getProperty("search")));
            search.sendKeys(read.get(i).getName()+" "+read.get(i).getPosition());
            try {
                test.log(Status.PASS,"Entering firstname and position in the search bar for "+" "+read.get(i).getName()+" their position is  "+read.get(i).getPosition(),
                        MediaEntityBuilder.createScreenCaptureFromPath(takeSnapShot(webDriver,"C:\\Reports1\\ScreenShots"+"img"+System.nanoTime()+".png")).build());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            webDriver.findElement(By.xpath(prop.getProperty("htmlBody"))).click();
            Thread.sleep(2000);
            WebElement clickSearch= webDriver.findElement(By.xpath(prop.getProperty("googleSearch")));
            Thread.sleep(3000);
            clickSearch.click();
        }
        extentReporter.flush();
    }
    public static void getReport(){
        extentReporter=new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("C:\\Reports1\\Spark.html");
        extentReporter.attachReporter(spark);

    }
    public static String takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception{
        TakesScreenshot scrShot =((TakesScreenshot)webdriver);
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile=new File(fileWithPath);
        FileUtils.copyFile(SrcFile, DestFile);
        return fileWithPath;
    }

}
