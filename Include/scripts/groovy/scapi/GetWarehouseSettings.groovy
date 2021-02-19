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


class GetWarehouseSettings {

	@Given('I get the settings for "(.*)" to "(.*)"')
	def I_get_the_settings_for_origin_destination(String origin, String destination) {
		GlobalVariable.origin = origin;

		//Set origin and destination from parameter
		def ship_calc = GlobalHelper.setWarehouseId(GlobalVariable.origin)

		def parameter = [:];
		parameter.warehouse_id = GlobalVariable.warehouse_id;

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
		GlobalVariable.per_pound_sh_air = per_pound_setting.lbs_value_special_handling_air.value
		GlobalVariable.per_pound_sh_sea = per_pound_setting.lbs_value_special_handling_sea.value

		//get fixed fee value from response and assign to global variable
		def fixed_fee = respBody.data[5].settings;
		def fixed_fees_setting = new groovy.json.JsonSlurper().parseText(fixed_fee)
		if (GlobalVariable.destination == "MY"){
			GlobalVariable.air_fixed_fee = fixed_fees_setting.air_fixed_fee.value;
		}else{
			GlobalVariable.air_fixed_fee = fixed_fees_setting.air_fixed_fee.value;
			GlobalVariable.sea_fixed_fee = fixed_fees_setting.sea_fixed_fee.value;
		}
		//get min chargeable weight value from response and assign to global variable
		def min_chargeable = respBody.data[7].settings;
		def min_chageable_weight_setting = new groovy.json.JsonSlurper().parseText(min_chargeable)
		GlobalVariable.minimum_chargeable_air = min_chageable_weight_setting.air_cargo.value as Double;
		GlobalVariable.minimum_chargeable_sea = min_chageable_weight_setting.sea_cargo.value as Double;

		//get storage fees and assign to global variables
		def storage_fees = respBody.data[4].settings
		def storage_settings = new groovy.json.JsonSlurper().parseText(storage_fees)
		GlobalVariable.storage_days_free = storage_settings.days_free_of_charge.value as Double;
		GlobalVariable.storage_fee = storage_settings.charge_beyond_free_day.value as Double;
	}
}