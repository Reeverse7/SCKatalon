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

import internal.GlobalVariable

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

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


class ShippingCalculation {
	/**
	 * Send request and verify status code
	 * @param request request object, must be an instance of RequestObject
	 * @param expectedStatusCode
	 * @return a boolean to indicate whether the response status code equals the expected one
	 */
	@Keyword
	def getItemChargeableWeight(String shipment_type, double lngth, double wdth, double hght, double wght, double minChargeAir, double minChargeSea) {

		def volumetric_weight = (lngth *wdth*hght)/166;
		volumetric_weight = volumetric_weight.round(2);

		if (shipment_type == "sea") {
			def item_chargeable_weight_sea = Math.max(volumetric_weight, minChargeSea);
			GlobalVariable.item_chargeable_sea = item_chargeable_weight_sea;
			println "item chargeable weight for sea: " + GlobalVariable.item_chargeable_sea
		}else if (shipment_type == "air"){
			def max1 = Math.max(volumetric_weight, wght);
			def item_chargeable_weight_air = Math.max(max1, minChargeAir);
			GlobalVariable.item_chargeable_air = item_chargeable_weight_air;
			println "item chargeable weight for air: " + GlobalVariable.item_chargeable_air
		}
	}

	@Keyword
	def getTotalCargoFee(String ship_type, int spcl_hndling){
		double item_chargeable_weight;
		def lbs_value;
		double total_cargo_fee;

		if (ship_type == "air"){
			item_chargeable_weight = GlobalVariable.item_chargeable_air as Double;
			item_chargeable_weight = item_chargeable_weight.round(2);

			if(spcl_hndling == 1){
				lbs_value = GlobalVariable.per_pound_specialhandling as Double;
				println("per pound value for special handling: " + lbs_value)
			}else{
				lbs_value = GlobalVariable.per_pound_air as Double;
				println("per pound value for air cargo: " + lbs_value)
			}
		}else if (ship_type == "sea"){
			item_chargeable_weight = GlobalVariable.item_chargeable_sea as Double;
			item_chargeable_weight = item_chargeable_weight.round(2);
			lbs_value = GlobalVariable.per_pound_sea as Double;
			println("per pound value for sea cargo: " + lbs_value)
		}

		println("Fixed fee: "+ GlobalVariable.fixed_fee)
		total_cargo_fee = ((item_chargeable_weight * lbs_value) + GlobalVariable.fixed_fee);
		GlobalVariable.total_cargo_fee = total_cargo_fee.round(2);
	}

	@Keyword
	def getInsuranceFee(double insured_val){
		double insurance_free = GlobalVariable.free_insurance as Double;
		double insurance_percentage = GlobalVariable.insurance_percentage as Double;
		double insurance_fee;

		if(insured_val > insurance_free){
			insurance_fee = (Math.abs(insurance_percentage/100) * (insured_val - insurance_free));
			insurance_fee = insurance_fee.round(2);
		}else{
			insurance_fee = 0;
		}
		GlobalVariable.insurance_fee = insurance_fee;
	}

	@Keyword
	def getTotalShippingFee(){
		double total_ship_fee;
		total_ship_fee = (GlobalVariable.total_cargo_fee as Double) + (GlobalVariable.insurance_fee as Double)
		GlobalVariable.total_shipping_fee = total_ship_fee.round(2);
	}

	@Keyword
	def checkExpectedAndActualResults(Double expected, Double actual){
		if(expected == actual){
			KeywordUtil.markPassed("Expected Result: " + expected + " And Actual Result: " + actual + " are equal.")
		}else{
			KeywordUtil.markWarning("Expected Result: " + expected + " And Actual Result: " + actual + " did not match.")
		}
	}
}