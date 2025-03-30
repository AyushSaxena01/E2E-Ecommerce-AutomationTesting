package utilities;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import netscape.javascript.JSObject;
import org.apache.commons.io.FileUtils;

import org.json.JSONObject;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class utilities {
    public static WebDriver driver;
    public static ExtentReports report;

    public static String getProperty(String key) throws IOException {
        String path = System.getProperty("user.dir")+File.separator+"\\src\\main\\java\\data\\data.properties";
        Properties prop = new Properties();
        FileInputStream file = new FileInputStream(path);
        prop.load(file);
        file.close();
        return prop.getProperty(key);
    }

    public static WebDriver setDriver() throws IOException {
        WebDriver driver=null;
        String browser = System.getProperty("browser")!=null?System.getProperty("browser"):getProperty("browser");

        if(browser.contains("chrome")){
            WebDriverManager.chromedriver().clearDriverCache().setup();
            ChromeOptions options = new ChromeOptions();
            if(browser.equalsIgnoreCase("chromeHeadless")){
                options.addArguments("--remote-allow-origins=*","headless");
            }else{
                options.addArguments("--remote-allow-origins=*");
            }
            driver=new ChromeDriver(options);
        } else if (browser.contains("edge")) {
            WebDriverManager.edgedriver().clearDriverCache().setup();
            EdgeOptions options = new EdgeOptions();
            if(browser.equalsIgnoreCase("edgeHeadless")){
                options.addArguments("headless");
            }
            driver = new EdgeDriver(options);
        }

        try{
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        }catch (Exception e){
            System.out.println(e);
        }
        return driver;

    }

    public static void getAmazon(String url) throws IOException {
        driver.get(getProperty("url"));
    }

    public static WebDriverWait Wait(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        return wait;
    }

    public static void expWait(By element){
        Wait().until(ExpectedConditions.visibilityOfElementLocated(element));

    }
    public static void pgUp(String xpath){
        Actions a = new Actions(driver);
        a.moveToElement(driver.findElement(By.xpath(xpath))).click().build().perform();
        a.sendKeys(Keys.PAGE_UP).build().perform();
    }

    public static void pgDown(String xpath){
        Actions a = new Actions(driver);
        a.moveToElement(driver.findElement(By.xpath(xpath))).click().build().perform();
        a.sendKeys(Keys.PAGE_DOWN).build().perform();
    }

     public List<HashMap<String, String>> getJSONDataToMap(String path) throws IOException {
        //read JSON to string
         String jsonContent = FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);
         //string to HashMap Jackson Databind
         ObjectMapper mapper = new ObjectMapper();
         List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
         });
         return data;
     }

     public static void stroll(WebElement element){
         JavascriptExecutor js = (JavascriptExecutor)driver;
         js.executeScript("arguments[0].scrollIntoView(true);",element);
     }

     public static void clearLogFile() {
             File logFile = new File("logs/test.log");
             if (logFile.exists()) {
                 logFile.delete();  // Delete log file before test execution
             }

    }

    public static ExtentReports getReportObject() {
        String path = "Amazon.html";
        ExtentSparkReporter extent = new ExtentSparkReporter(path);
        extent.viewConfigurer()
                .viewOrder()
                .as(new ViewName[]{ViewName.DASHBOARD, ViewName.TEST, ViewName.CATEGORY, ViewName.EXCEPTION, ViewName.LOG, ViewName.AUTHOR, ViewName.DEVICE})
                .apply();
        extent.config().setReportName("Amazon UI Automation Test Report");
        extent.config().setDocumentTitle("UI Automation Test Report");
        report = new ExtentReports();
        report.attachReporter(extent);
        report.setSystemInfo("Tester","Ayush Saxena");
        return report;
    }

    public static String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
       TakesScreenshot ts = (TakesScreenshot) driver;
       File source = ts.getScreenshotAs(OutputType.FILE);
       String destinationFile = "\\E2E_Amazon\\Reports\\"+ testCaseName + ".png" ;
       FileUtils.copyFile(source,new File(destinationFile));
       return destinationFile;
    }

    public static String getText(String xpath){
        String text = driver.findElement(By.xpath(xpath)).getText();
return text;
    }

    public static void waitForAnElementToDisplay(By locator){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static String setName(ITestResult result) throws IOException {
        String path = System.getProperty("user.dir")+File.separator+"src\\main\\java\\data\\testCode.JSON";
String methodName = result.getMethod().getMethodName();
String [] codeArr = {"a","",""};
String code="Common";
String content = new String(Files.readAllBytes(Paths.get(path)));
        JSONObject obj = new JSONObject(content);
        for(int i=0;i<codeArr.length;i++){
            if(methodName.contains(codeArr[i])){
                code=codeArr[i];
            }
        }
        return code;
    }

    public static void getUrl(String url){
        driver.get(url);
    }


     @BeforeClass(alwaysRun = true)
    public static void configuration() throws IOException {
        driver = setDriver();
        clearLogFile();
     }

     @AfterClass(alwaysRun = true)
    public static void closeDriver(){
        driver.close();
     }

}
