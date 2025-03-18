package demo.wrappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.print.attribute.IntegerSyntax;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.NumberFormat;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */

     public static void enterText(WebDriver driver, By Locator, String enterText){
        try{
            WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(Locator));
            WebElement inputElement=driver.findElement(Locator);
            inputElement.clear();
            inputElement.sendKeys(enterText);
            inputElement.sendKeys(Keys.ENTER);
        }catch(Exception e){
            System.out.println("Exception message : "+e.getMessage()); 
        }
     }

     public static void clickButton(WebDriver driver, By Locator){
        try{
            WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(Locator));
            WebElement clickElement=driver.findElement(Locator);
            clickElement.click();
        }catch(Exception e){            
            System.out.println("Exception message : "+e.getMessage());              
        }
     }


     public static void searchStarRating(WebDriver driver, By Locator, double starRating){               
        int washingMachineCount=0;        
        float ratingval=0;
        try{
        List<WebElement> ratingElement=driver.findElements(Locator);
        for(WebElement rating:ratingElement){          
            String ratingvalue=rating.getText();         
            ratingval = Float.parseFloat(ratingvalue); 
            if(ratingval<=starRating){
                washingMachineCount++;
            }                     
        }     
        System.out.println("The count of Washing Machine items with rating less than or equal to 4 stars is :: "+washingMachineCount);        
        }catch(Exception e){
            System.out.println("Exception Occured :"+e.getMessage());
        }
     }

     public static void printTitleandDiscount(WebDriver driver, By Locator, int discount){               
        try{
            List<WebElement> productRowsParent=driver.findElements(Locator);
            Map<String,String> iphoneTitleDiscountMap=new HashMap<>();
            for(WebElement product:productRowsParent){
                WebElement productdiscount=product.findElement(By.xpath("//div[@class='UkUFwK']/span"));
                String discountpercent=productdiscount.getText();
                int discountvalue=Integer.parseInt(discountpercent.replaceAll("[^\\d]",""));
                if(discountvalue>discount){
                    WebElement iphoneTitle=product.findElement(By.xpath(""));
                    String iphoneTitletext=iphoneTitle.getText();
                    iphoneTitleDiscountMap.put(discountpercent, iphoneTitletext);
                }
                for(Map.Entry<String,String> iphoneTitleDiscount:iphoneTitleDiscountMap.entrySet()){
                    System.out.println("Iphone Discount Percentage :" + iphoneTitleDiscount.getKey() + " and its title is : "+iphoneTitleDiscount.getValue());
                }
            }
        }catch(Exception e){
            System.out.println("Exception Occured : "+e.getMessage());
        }

     }

     public static void printTitleImageURL(WebDriver driver, By Locator){               
        try{
            List<WebElement> reviewElement=driver.findElements(Locator);
            Set<Integer> reviewSet=new HashSet<>();
            for(WebElement review:reviewElement){
                int userReview=Integer.parseInt(review.getText().replaceAll("[^\\d]", ""));
                reviewSet.add(userReview);
            }
            List<Integer> userReviewCountList=new ArrayList<>(reviewSet);
            Collections.sort(userReviewCountList,Collections.reverseOrder());
            System.out.println(userReviewCountList);

            NumberFormat numberFormat=NumberFormat.getInstance(Locale.US);
            HashMap<String, String> productDetailsMap=new HashMap<>();

            for(int i=0;i<5;i++){
                String formatReviewCount="("+numberFormat.format(userReviewCountList.get(i))+")";
                String productTitle=driver.findElement(By.xpath("//div[@class='slAVV4']//span[contains(text(),'" +formatReviewCount+ "')]/../../a[@class='wjcEIp']")).getText();
                String productImageURL=driver.findElement(By.xpath("//div[@class='slAVV4']//span[contains(text(),'" +formatReviewCount+ "')]/../..//img[@class='DByuf4']")).getAttribute("src");
                
                String finalReviewCounttitle=String.valueOf(i+1)+"Highest Review Count : "+formatReviewCount+"  Product Title : "+productTitle;
                productDetailsMap.put(finalReviewCounttitle, productImageURL);                
            }

            for(Map.Entry<String,String> productDetail:productDetailsMap.entrySet()){
                System.out.println(productDetail.getKey()+" and the product Image URL is : "+productDetail.getValue());
            }        


        }catch(Exception e){
            System.out.println("Error Occured : "+e.getMessage());
        }

    }
}
