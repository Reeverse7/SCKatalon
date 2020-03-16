package calculator
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
import api.*

class CalculateShipmentCost {
	def parameter = [:];

	@Given('I get the settings for "(.*)" to "(.*)"')
	def I_get_the_settings_for_origin_destination(String origin, String destination) {
		parameter.base_url = GlobalVariable.base_url;

		//Check origin and destination from parameter
		if (origin == "US"){
			GlobalVariable.warehouse_id = 1;
			parameter.warehouse_id = GlobalVariable.warehouse_id;
		}else if (origin == "UK"){
			GlobalVariable.warehouse_id = 2;
			parameter.warehouse_id = GlobalVariable.warehouse_id;
		}
		GlobalVariable.destination = destination;
		parameter.destination_country = GlobalVariable.destination;

		//send get all settings request based on origin and destination
		ResponseObject response = WS.sendRequest(findTestObject('Get All Settings', parameter));
		WS.verifyResponseStatusCode(response, 200);

		//parse response body
		def slurper = new groovy.json.JsonSlurper();
		def respBody = slurper.parseText(response.getResponseBodyContent());

		//get values of general settings from response and assign to global variable
		def gen_set = respBody.data[0].settings;
		def general_setting = new groovy.json.JsonSlurper().parseText(gen_set)
		GlobalVariable.free_insurance = general_setting.insurance_free.value;
		GlobalVariable.insurance_percentage = general_setting.insurance_percentage.value

		//get per pound value from response and assign to global variable
		def per_pound = respBody.data[2].settings;
		def per_pound_setting = new groovy.json.JsonSlurper().parseText(per_pound)
		GlobalVariable.per_pound_air = per_pound_setting.lbs_value_air.value;
		GlobalVariable.per_pound_sea = per_pound_setting.lbs_value_sea.value;
		GlobalVariable.per_pound_specialhandling = per_pound_setting.lbs_value_special_handling_air.value

		//get fixed fee value from response and assign to global variable
		def fixed_fee = respBody.data[5].settings;
		def fixed_fees_setting = new groovy.json.JsonSlurper().parseText(fixed_fee)
		GlobalVariable.fixed_fee = fixed_fees_setting.luzon.value;

		//get min chargeable weight value from response and assign to global variable
		def min_chargeable = respBody.data[7].settings;
		def min_chageable_weight_setting = new groovy.json.JsonSlurper().parseText(min_chargeable)
		GlobalVariable.minimum_chargeable_air = min_chageable_weight_setting.air_cargo.value as Double;
		GlobalVariable.minimum_chargeable_sea = min_chageable_weight_setting.sea_cargo.value as Double;

	}

	@When('I calculate shipping estimate for shipment type "(.*)"')
	def I_calculate_shipping_estimate(String ship_type) {

		def shipCalc = new ShippingCalculation();
		//get parcel dimensions from data
		def parameter =  [:];
		def row_count = (GlobalVariable.td_shipping_estimate).getRowNumbers();
		for (int i = 1; i <= row_count; i++) {

			parameter.warehouse_id = GlobalVariable.warehouse_id;
			parameter.destination_country = GlobalVariable.destination;
			parameter.length = GlobalVariable.td_shipping_estimate.getValue("item_length",i) as Double;
			parameter.width = GlobalVariable.td_shipping_estimate.getValue("item_width",i) as Double;
			parameter.height = GlobalVariable.td_shipping_estimate.getValue("item_height",i) as Double;
			parameter.weight = GlobalVariable.td_shipping_estimate.getValue("item_weight",i) as Double;
			parameter.insured_value = GlobalVariable.td_shipping_estimate.getValue("insured_value",i) as Double;
			parameter.dangerous = GlobalVariable.td_shipping_estimate.getValue("dangerous",i) as boolean;
			parameter.special_handling = GlobalVariable.td_shipping_estimate.getValue("special_handling",i) as int;

			//send get shipping estimate request
			ResponseObject response = WS.sendRequest(findTestObject('Get Shipping Estimate', parameter));
			WS.verifyResponseStatusCode(response, 200);

			//parse response body
			def slurper = new groovy.json.JsonSlurper();
			def respBody = slurper.parseText(response.getResponseBodyContent());
			def actual_total_air_cargo_fee = respBody.data.air.usd.total_cargo as Double;
			def actual_total_sea_cargo_fee = respBody.data.sea.usd.total_cargo as Double;
			def actual_chargeable_weight_air = respBody.data.air.usd.total_chargeable_weight as Double;
			def actual_chargeable_weight_sea = respBody.data.sea.usd.total_chargeable_weight as Double;
			def insurance_fee_air = respBody.data.air.usd.total_insurance as Double;
			def insurance_fee_sea = respBody.data.sea.usd.total_insurance as Double;
			def actual_shipping_fee_air = respBody.data.air.usd.total_price as Double;
			def actual_shipping_fee_sea = respBody.data.sea.usd.total_price as Double;

			//get chargeable weight, cargo fee, insurance fee and total shipping fee
			shipCalc.getItemChargeableWeight(ship_type, parameter.length, parameter.width, parameter.height, parameter.weight,
					GlobalVariable.minimum_chargeable_air, GlobalVariable.minimum_chargeable_sea);
			shipCalc.getTotalCargoFee(ship_type, parameter.special_handling);
			shipCalc.getInsuranceFee(parameter.insured_value);
			shipCalc.getTotalShippingFee();

			if (ship_type == "air"){
				shipCalc.checkExpectedAndActualResults(GlobalVariable.total_cargo_fee, actual_total_air_cargo_fee);
				shipCalc.checkExpectedAndActualResults(GlobalVariable.item_chargeable_air, actual_chargeable_weight_air);
				shipCalc.checkExpectedAndActualResults(GlobalVariable.insurance_fee, insurance_fee_air);
				shipCalc.checkExpectedAndActualResults(GlobalVariable.total_shipping_fee, actual_shipping_fee_air);
			}else if (ship_type == "sea"){
				shipCalc.checkExpectedAndActualResults(GlobalVariable.total_cargo_fee, actual_total_sea_cargo_fee);
				shipCalc.checkExpectedAndActualResults(GlobalVariable.item_chargeable_air, actual_chargeable_weight_sea);
				shipCalc.checkExpectedAndActualResults(GlobalVariable.insurance_fee, insurance_fee_sea);
				shipCalc.checkExpectedAndActualResults(GlobalVariable.total_shipping_fee, actual_shipping_fee_sea);
			}
		}
	}
}