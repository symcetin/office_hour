package com.automation.tests.mira;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Class {

    public static void main(String[] args) {
        // Lets assume you are searching for iphone on amazon
        // many search results came in
        // i want you to store all the price element on the result page
        // in the meantime ,I want you to store all the price text into a List
        // we can use findElements and getText together
        // it will give us a List<String> that contains prices
        // but we want to get List<Double>
//
//        String price = "100.99" ;
//        double priceNum = Double.parseDouble(price);
//        System.out.println("priceNum = " + priceNum);

        List<String> priceLstStr =  new ArrayList<>( Arrays.asList("99.99","149.99","79.99","149.99","299.99","599.99","299.99","599.99"));
        priceLstStr.add("999.99");
        System.out.println("priceLstStr = " + priceLstStr);

        // but we want to get List<Double>
        List<Double> prices = new ArrayList<>();

        for (String eachPrice : priceLstStr) {
            prices.add(  Double.parseDouble(eachPrice)  );
        }

        System.out.println("prices in double = " + prices);
        // how do I get max price , min , average
        //  remove items above average
        //  get the unique prices
    }
}
