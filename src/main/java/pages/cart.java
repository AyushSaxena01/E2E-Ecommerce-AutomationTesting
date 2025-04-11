package pages;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.home;
import org.openqa.selenium.WebDriver;

import utilities.utilities;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class cart extends utilities {
    private WebDriver driver;


    public cart(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//h2[contains(@aria-label,'Guitar String')]")
    List<WebElement> guitarStringsList;


    @FindBy(xpath = "//img[contains(@alt,\"D'Addario\")]")
    List<WebElement> stringLogo;

    @FindBy(xpath = "//span[@class='a-price-whole']")
    WebElement price;

    @FindBy(id = "add-to-cart-button")
    WebElement addToCart;

    @FindBy(xpath = "//h1[contains(text(),'Added to cart')]")
    WebElement addedTOCart;

    @FindBy(xpath = "//span[@id='sw-subtotal-item-count']/following-sibling::span/span[1]/span[2]/span[2]")
    WebElement subTotal;

    @FindBy(id = "nav-cart-count")
    WebElement cartCount;

    @FindBy(id = "nav-logo-sprites")
    public WebElement amazonLogo;

    @FindBy(id = "anonCarousel1")
    public WebElement hompageCardCarousal;

    @FindBy(xpath = "//span[@class='a-button-inner']/button")
     List<WebElement> addToCartButtonList ;

    float productCost1=0;
    float productCost2=0;




    public void search () throws IOException, InterruptedException {
        search(getProperty("searchCart"));
        expWaitWebElement(stringLogo.get(1));
        Assert.assertTrue(stringLogo.get(1).isDisplayed());
    }

    public void clickProduct1(){
        scroll(guitarStringsList.get(10));
        guitarStringsList.get(10).click();
        Iterator<String> window = windowIterator();
        String parent = window.next();
        String child = window.next();
        driver.switchTo().window(child);
        expWaitWebElement(price);
        productCost1=price();
        addToCart();
        driver.close();
        driver.switchTo().window(parent);
        scrollToTop();
        amazonLogo.click();
        expWaitWebElement(hompageCardCarousal);

    }
    public void clickProduct2(){
        scroll(guitarStringsList.get(15));
        guitarStringsList.get(15).click();
        Iterator<String> window = windowIterator();
        String parent = window.next();
        String child = window.next();
        driver.switchTo().window(child);
        expWaitWebElement(price);
        productCost2=price();
        addToCart();
        driver.close();
        driver.switchTo().window(parent);
        scrollToTop();
        amazonLogo.click();
        expWaitWebElement(hompageCardCarousal);
    }
    public float price(){
        String cost = price.getText();
        return Float.parseFloat(onlyNumbers(cost));
    }



    public void addToCart(){
        addToCart.click();
        Assert.assertTrue(addedTOCart.isDisplayed());
        checkCartPrice();

    }

    public void checkCartPrice(){
        Assert.assertEquals(String.valueOf(productCost1+productCost2),String.valueOf(Float.parseFloat(onlyNumbers(subTotal.getText()))));
    }

    public void openCart(){
       scrollToTop();
       cartCount.click();
    }

    public void cartCount(){
        scrollToTop();
        Assert.assertEquals(2,Integer.parseInt(cartCount.getText()));
    }

    public void addMultipleProductsToCart() throws IOException, InterruptedException {
        amazonLogo.click();
        expWaitWebElement(hompageCardCarousal);
        search(getProperty("multipleProduct"));
       addToCartButtonList.get(3).click();
        addToCartButtonList.get(9).click();
        addToCartButtonList.get(10).click();
        addToCartButtonList.get(38).click();
        addToCartButtonList.get(40).click();
        scrollToTop();
        Thread.sleep(2000);
        Assert.assertEquals(7,Integer.parseInt(cartCount.getText()));
        openCart();
    }







}
