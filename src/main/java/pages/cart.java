package pages;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.home;
import org.openqa.selenium.WebDriver;

import utilities.utilities;

import java.io.IOException;
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

    @FindBy(xpath = "a-price-whole")
    WebElement price;

    @FindBy(id = "add-to-cart-button")
    WebElement addToCart;

    @FindBy(xpath = "//h1[contains(text(),'Added to cart')]")
    WebElement addedTOCart;

    @FindBy(xpath = "//span[@id='sw-subtotal-item-count']/following-sibling::span/span[1]/span")
    WebElement subTotal;

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
        expWaitWebElement(price);
        productCost1=price();

    }
    public void clickProduct2(){
        scroll(guitarStringsList.get(15));
        guitarStringsList.get(15).click();
        expWaitWebElement(price);
        productCost2=price();

    }
    public float price(){
        String cost = price.getText();
        return Float.parseFloat(cost);
    }

    public void addToCart(){
        addToCart.click();
        Assert.assertTrue(addedTOCart.isDisplayed());
        checkCartPrice();
    }

    public void checkCartPrice(){
        Assert.assertEquals((productCost1+productCost2),subTotal);
    }







}
