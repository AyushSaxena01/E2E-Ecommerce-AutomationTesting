package testcases;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.home;
import utilities.utilities;

import java.io.IOException;

public class homeTestCases extends utilities {
public home homeObject;


    @Test(priority=1)
    public void setup() throws IOException {
        homeObject = new home(driver);
    }
    @Test(priority=2)
    public void getUrl() throws IOException {

          getUrl(getProperty("url"));
    }
    @Test(priority=3)
    public void search() throws IOException {
        homeObject.search(getProperty("search"));
    }

    @Test(priority = 4)
    public void countProducts(){
        homeObject.countProducts();
    }

    @Test(priority = 5)
    public void filter(){
        homeObject.applyFilter();
    }

    @Test(priority = 6)
    public void sortLowTOHigh(){
        homeObject.sortPriceLowToHigh();
    }

    @Test(priority = 7)
    public void sortHighToLow(){
        homeObject.sortPriceoHighToLow();
    }

}
