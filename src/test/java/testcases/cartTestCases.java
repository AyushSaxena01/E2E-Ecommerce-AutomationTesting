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
    public void SearchProduct_1() throws IOException, InterruptedException {
       cartObj.search();
    }
    @Test(priority = 4)
    public void clickProduct_1_AndAddToCart(){
      cartObj.clickProduct1();
    }
    @Test(priority = 5)
    public void SearchProduct_2() throws IOException, InterruptedException {
        cartObj.search();
    }
    @Test(priority = 6)
    public void clickProduct_2_AndAddToCart(){
        cartObj.clickProduct2();
    }
    @Test(priority = 7)
    public void cartCountNumber(){
        expWaitWebElement(cartObj.hompageCardCarousal);
      cartObj.cartCount();
    }
    @Test(priority = 8)
    public void openCart(){
       cartObj.openCart();
    }
    @Test(priority = 9)
    public void addMultipleProductsToCart() throws IOException, InterruptedException {
        cartObj.addMultipleProductsToCart();
    }
    @Test(priority = 10)
    public void removeProductFromCart() throws IOException, InterruptedException {
        cartObj.removePproducts();
    }

    



}
