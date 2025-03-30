package pages;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class home extends utilities {
private WebDriver driver;
    public home(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id="twotabsearchtextbox")
    WebElement searchInput;

    @FindBy(id="nav-search-submit-button")
    WebElement searchSubmitButton;

    @FindBy(xpath = "//a[@aria-label='Samsung']")
    WebElement samsungLogo;

    @FindBy(xpath = "//h2[contains(@aria-label,'Samsung')]")
    List<WebElement> productsOnFirstPage;

    @FindBy(xpath = "//h2[contains(@class,'a-size-base a-spacing-small a-spacing-top-small a-text-normal')]/span[1]")
    WebElement displayProductCount;

    @FindBy(xpath="//a[contains(@aria-label,'Apply the filter 8 GB to narrow results')]/div/label/i")
    WebElement filter8GB;

    @FindBy(xpath = "//span[@id='a-autoid-0-announce']")
    WebElement sortDropDown;

    @FindBy(xpath = "//a[text()='Price: Low to High']")
    WebElement priceLowToHihg;

    @FindBy(xpath = "//a[text()='Price: High to Low']")
    WebElement priceHihgToLow;

    @FindBy(xpath = "//span[@class='a-price-whole']")
    List<WebElement> priceElement;

    @FindBy(xpath = "//span[@class='a-list-item']/a[text()='Smartphones']")
    WebElement category;

    public void search(String search){
        searchInput.sendKeys(search);
        searchSubmitButton.click();
        Assert.assertTrue(samsungLogo.isDisplayed());
    }

    public int displayProductCount(){
        int count=0;
        String display = displayProductCount.getText();
        String strCount = display.substring(2,4);
        count = Integer.parseInt(strCount);
        return count;
    }

    public int displayProductCountAfterFilter(){
        int count=0;
        String display = displayProductCount.getText();
        String strCount = display.substring(0,2);
        count = Integer.parseInt(strCount);
        return count;
    }

    public void countProducts(){
       Assert.assertEquals(productsOnFirstPage.size(),displayProductCount());
    }

    public void applyFilter(){
        utilities.stroll(filter8GB);
        filter8GB.click();
        Assert.assertEquals(productsOnFirstPage.size(),displayProductCountAfterFilter());
    }

    public void sortPriceLowToHigh(){
        boolean condition = true;
        sortDropDown.click();
        priceLowToHihg.click();
        List<Integer> priceList = new ArrayList<>();
        for(int i=0;i<priceElement.size();i++){
            priceList.add(Integer.parseInt(priceElement.get(i).getText().replaceAll(",","")));
            System.out.println(priceList.get(i));
        }
        for(int i =1 ;i<priceList.size();i++){
            if(priceList.get(i-1).compareTo(priceList.get(i))>0){
               condition=false;
            }
        }
       Assert.assertTrue(condition);
    }

    public void sortPriceoHighToLow(){
        boolean condition = true;
        sortDropDown.click();
        priceHihgToLow.click();
        List<Integer> priceList = new ArrayList<>();
        for(int i=0;i<priceElement.size();i++){
            priceList.add(Integer.parseInt(priceElement.get(i).getText().replaceAll(",","")));
            System.out.println(priceList.get(i));
        }
        for(int i =1 ;i<priceList.size();i++){
            if(priceList.get(i-1).compareTo(priceList.get(i))<0){
                condition=false;
            }
        }
        Assert.assertTrue(condition);
    }

    public void clickOnProduct() throws IOException {
        searchInput.clear();
        search(getProperty("search"));
        utilities.stroll(productsOnFirstPage.get(9));
        productsOnFirstPage.get(9).click();
        Assert.assertTrue(category.isDisplayed());
    }

}
