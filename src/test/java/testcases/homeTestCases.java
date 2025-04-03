package testcases;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
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
    @Feature("Amazon Website")
    @Description("Navigating to Amazon website.")
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
    @Feature("Search")
    @Description("Searching for a mobile phone product.")
    public void search_H_2() throws IOException {
        homeObject.Search(getProperty("search"));
    }

    @Test(priority = 4)
    @Feature("Product Count")
    @Description("Counting the relevant products being shown.")
    public void countProducts_H_3(){
        homeObject.countProducts();
    }

    @Test(priority=5)
    @Feature("Next page")
    @Description("goes to next page.")
    public void nextPage_H_4() throws IOException {
        homeObject.nextPage();
    }

    @Test(priority = 6)
    @Feature("Filter")
    @Description("Filtering the results for only 8gb models.")
    public void filter_H_5(){
        homeObject.applyFilter();
    }

    @Test(priority = 7)
    @Feature("Sorting")
    @Description("Price:Low to Hihg")
    public void sortLowTOHigh_H_6(){
        homeObject.sortPriceLowToHigh();
    }

    @Test(priority = 8)
    @Feature("Sorting")
    @Description("Price:Hihg to Low")
    public void sortHighToLow_H_7()
    {
        homeObject.sortPriceoHighToLow();
    }



    @Test(priority=9)
    @Feature("Product page")
    @Description("Opening a random product page.")
    public void clickProduct_H_8() throws IOException {
        homeObject.clickOnProduct();
    }


}
