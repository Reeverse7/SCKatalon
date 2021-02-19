package api
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException
import internal.GlobalVariable

class ShippingCalculation {

	@Keyword
	def getInsuranceFee(double insured_val){
		double insurance_free = GlobalVariable.free_insurance as Double
		double insurance_percentage = GlobalVariable.insurance_percentage as Double
		double insurance_fee

		if(insured_val > insurance_free){
			insurance_fee = (Math.abs(insurance_percentage/100) * (insured_val - insurance_free))
			insurance_fee = insurance_fee.round(2)
		}else{
			insurance_fee = 0
		}
		GlobalVariable.insurance_fee = insurance_fee
		println("Total Valuation Fee:" + GlobalVariable.insurance_fee)
	}

	@Keyword
	def getTotalShippingFee(){
		double total_ship_fee
		total_ship_fee = (GlobalVariable.total_cargo_fee as Double) + (GlobalVariable.insurance_fee as Double) + (GlobalVariable.total_storage_fee as Double)
		GlobalVariable.total_shipping_fee = total_ship_fee.round(2)
		println("Total Shipping Fee is: " + GlobalVariable.total_shipping_fee)
	}


	@Keyword
	def getDefaultStorageFee(int stored_days, double wght){
		double total_storage_fee
		if (stored_days <= GlobalVariable.storage_days_free){
			GlobalVariable.total_storage_fee = 0
		}else{
			total_storage_fee = ((stored_days - (GlobalVariable.storage_days_free as Double)) * GlobalVariable.storage_fee * wght)
			GlobalVariable.total_storage_fee = total_storage_fee.round(2)
			println("Total Storage Fee is: " + GlobalVariable.total_storage_fee)
		}
	}

	@Keyword
	def checkExpectedAndActualAmount(Double expected, Double actual){
		if(expected == actual){
			KeywordUtil.markPassed("Expected Result: " + expected + " And Actual Result: " + actual + " are equal.")
		}else{
			KeywordUtil.markFailed("Expected Result: " + expected + " And Actual Result: " + actual + " did not match.")
		}
	}
}