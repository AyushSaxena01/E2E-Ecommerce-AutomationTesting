package testcases;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.home;
import utilities.utilities;
import utilities.log;

import java.io.IOException;

public class homeTestCases extends utilities {
    private static final Logger log = LoggerFactory.getLogger(homeTestCases.class);
    public home homeObject;


    @Test(priority=1)
    public void setup() throws IOException {
        homeObject = new home(driver);
    }
    @Test(priority=2)
    public void getUrl_H_1() throws IOException {
       log.info("=======================Starting Home Test Cases========================");
         try{
             getUrl(getProperty("url"));
         } catch (Exception e) {
             log.info("Exception occured");
             log.info(e.getMessage());
         }
     }
    @Test(priority=3)
    public void search_H_2() throws IOException {
        homeObject.search(getProperty("search"));
    }

    @Test(priority = 4)
    public void countProducts_H_3(){
        homeObject.countProducts();
    }

    @Test(priority = 5)
    public void filter_H_4(){
        homeObject.applyFilter();
    }

    @Test(priority = 6)
    public void sortLowTOHigh_H_5(){
        homeObject.sortPriceLowToHigh();
    }

    @Test(priority = 7)
    public void sortHighToLow_H_6(){
        homeObject.sortPriceoHighToLow();
    }

    @Test(priority=8)
    public void clickProduct_H_7() throws IOException {
        homeObject.clickOnProduct();
    }

}
