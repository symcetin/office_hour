package com.automation.tests.office_hour.nurullah;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UnderstandingExcelConnection {

    @Test
    public void testcase1() throws IOException {
        //Which file are we calling upon. Location of file
        String filepath = "VytrackTestUsers.xlsx";
        //we must load our excel file as java file (bytecode)
        //FileInputStream -> converts file a bytecode ,so java can read it
        FileInputStream byteCodeOfExcelFile = new FileInputStream(filepath);
        //Load our workbook as a file
        Workbook workbook = WorkbookFactory.create(byteCodeOfExcelFile);
        //the sheet that we are working on
        Sheet workSheet = workbook.getSheet("Sheet1");

        //Read the Cell data of Cell 1A and Cell 1B
        //to load the cell we need the row and column reference
        // --> as index numbers row 1 = index 0 and column A = index 0
        // --> as index numbers row 1 = index 0 and column B = index 1

        Cell cell;
        cell = workSheet.getRow(0).getCell(0);
        System.out.println(cell.toString()); //Print the first cell CELL 1A
    }
}
