package testcases;

import org.testng.annotations.Test;
import pages.cart;
import pages.home;
import utilities.utilities;

import java.io.IOException;


public class cartTestCases extends utilities{
    cart cartObj;
    home homeObj;
    @Test(priority = 1)
    public void setup(){
        cartObj = new cart(driver);
        homeObj = new home(driver);

    }
    @Test(priority = 2)
    public void login () throws IOException {
        getUrl(getProperty("url"));
    }
    @Test(priority =3)
    public void SearchProduct1() throws IOException, InterruptedException {
       cartObj.search();
    }
    @Test(priority = 4)
    public void clickProduct1(){
      cartObj.clickProduct1();
    }
    @Test(priority = 5)
    public void addProduct1ToCart(){
        cartObj.addToCart();
    }
    @Test(priority = 6)
    public void SearchProduct2() throws IOException, InterruptedException {
        cartObj.search();
    }
    @Test(priority = 7)
    public void clickProduct2(){
        cartObj.clickProduct2();
    }
    @Test(priority = 8)
    public void addProduct2ToCart(){
        cartObj.addToCart();
    }

}
