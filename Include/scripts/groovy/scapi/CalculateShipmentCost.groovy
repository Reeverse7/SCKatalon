package scapi
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.eclipse.persistence.internal.jpa.parsing.jpql.antlr.JPQLParser.generalCaseExpression_scope
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

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import groovy.json.internal.LazyMap
import api.*

class CalculateShipmentCost {


	@When('I calculate shipping estimate for shipment type "(.*)"')
	def I_calculate_shipping_estimate(String ship_type) {

		def ship_calc = GlobalHelper.getShipmentCalculation(ship_type)

		//get parcel dimensions from data
		def ship_estimate_param =  [:];
		def row_count = (GlobalVariable.td_shipping_estimate).getRowNumbers();
		for (int i = 1; i <= row_count; i++) {

			ship_estimate_param.warehouse_id = GlobalVariable.warehouse_id;
			ship_estimate_param.destination_country = GlobalVariable.destination;
			ship_estimate_param.length = GlobalVariable.td_shipping_estimate.getValue("item_length",i) as Double;
			ship_estimate_param.width = GlobalVariable.td_shipping_estimate.getValue("item_width",i) as Double;
			ship_estimate_param.height = GlobalVariable.td_shipping_estimate.getValue("item_height",i) as Double;
			ship_estimate_param.weight = GlobalVariable.td_shipping_estimate.getValue("item_weight",i) as Double;
			ship_estimate_param.insured_value = GlobalVariable.td_shipping_estimate.getValue("insured_value",i) as Double;
			ship_estimate_param.dangerous = GlobalVariable.td_shipping_estimate.getValue("dangerous",i) as boolean;
			ship_estimate_param.special_handling = GlobalVariable.td_shipping_estimate.getValue("special_handling",i) as int;

			//send get shipping estimate request
			ResponseObject shipping_estimate = WS.sendRequest(findTestObject('Get Shipping Estimate', ship_estimate_param));
			WS.verifyResponseStatusCode(shipping_estimate, 200);

			//parse response body
			def shipping_estimate_info = new groovy.json.JsonSlurper();
			def shipping__est_slurper = shipping_estimate_info.parseText(shipping_estimate.getResponseBodyContent());

			//get chargeable weight, cargo fee, insurance fee and total shipping fee
			ship_calc.getItemChargeableWeight(ship_estimate_param.length, ship_estimate_param.width, ship_estimate_param.height, ship_estimate_param.weight,
					GlobalVariable.minimum_chargeable_air, GlobalVariable.minimum_chargeable_sea)
			ship_calc.getTotalCargoFee(ship_estimate_param.special_handling)
			ship_calc.getInsuranceFee(ship_estimate_param.insured_value);
			ship_calc.getTotalShippingFee();

			checkShippingEstimate(ship_calc, shipping__est_slurper, ship_type)
		}
	}

	def checkShippingEstimate(ShippingCalculation ship_calc, LazyMap shipping_est_slurper, String ship_type){
		def shipmentTypeValues = shipping_est_slurper.get("data").get(ship_type)

		//		TODO: change to camel case
		def actual_total_cargo_fee = shipmentTypeValues.usd.total_cargo as Double
		def insurance_fee = shipmentTypeValues.usd.total_insurance as Double
		def actual_chargeable_weight = shipmentTypeValues.usd.total_chargeable_weight as Double
		def actual_shipping_fee = shipmentTypeValues.usd.total_price as Double

		ship_calc.checkExpectedAndActualResults(GlobalVariable.total_cargo_fee, actual_total_cargo_fee)
		ship_calc.checkExpectedAndActualResults(GlobalVariable.item_chargeable, actual_chargeable_weight)
		ship_calc.checkExpectedAndActualResults(GlobalVariable.insurance_fee, insurance_fee)
		ship_calc.checkExpectedAndActualResults(GlobalVariable.total_shipping_fee, actual_shipping_fee)
	}
}