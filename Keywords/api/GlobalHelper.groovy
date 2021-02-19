package api

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class GlobalHelper {
	@Keyword
	public static setWarehouseId(String origin){
		if (origin == "US"){
			GlobalVariable.warehouse_id = 1;
		} else if (origin == "UK"){
			GlobalVariable.warehouse_id = 2;
		} else if (origin == "AU"){
			GlobalVariable.warehouse_id = 3;
		}
	}
	
	@Keyword
	public static getShipmentCalculation(String ship_type){
		if (ship_type == "air"){
			return new AirShippingCalculation()
		} else if (ship_type == "sea") {
			return new SeaShippingCalculation()
		} 
		
		throw new IllegalArgumentException("Shipment type " + ship_type + " not supported.")
	}
}
