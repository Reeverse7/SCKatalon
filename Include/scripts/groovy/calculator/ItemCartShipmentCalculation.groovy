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
import groovy.json.JsonSlurper
import groovy.json.internal.LazyMap
import api.*

//TODO: Remove all semi-colon
class ItemCartShipmentCalculation {

	@When('I calculate "(.*)" cargo shipment cost for single item')
	def I_update_insured_value(String ship_type) {

//		TODO: Transfer to global helper
		def shipCalc;
		if(ship_type == "air"){
			shipCalc = new AirShippingCalculation()
		} else {
			//			shipCalc = new Sea()
		}

		def globalHelper = new GlobalHelper();

		//update parcel insured value
		def parameter =  [:];
		def row_count = (GlobalVariable.td_cart_items).getRowNumbers();
		for (int i = 1; i <= row_count; i++) {

			//basic
			//			TODO: Check if base url and customer token can be used on the object repository
			parameter.base_url = GlobalVariable.base_url;
			parameter.customer_token = GlobalVariable.customer_token;

			parameter.warehouse_id = GlobalVariable.warehouse_id;
			parameter.insured_value = GlobalVariable.td_cart_items.getValue("insured_value",i) as Double;
			parameter.origin = GlobalVariable.origin;
			parameter.destination = GlobalVariable.destination;
			parameter.item_id = GlobalVariable.td_cart_items.getValue("item_id",i);

			//identify cargo type
			if (ship_type == "air"){
				parameter.air_item_id = parameter.item_id;
				parameter.sea_item_id = ''
			}else{
				parameter.sea_item_id = parameter.item_id;
				parameter.air_item_id = ''
			}

			//send update insured value request and verify response
			//			TODO: Create parameters only specific to Update Item Insured Value
			ResponseObject updateValue = WS.sendRequest(findTestObject('Object Repository/Update Item Insured Value', parameter));
			WS.verifyResponseStatusCode(updateValue, 200);

			//get parcel details
			//			TODO: create parameters only specific to Get Percel by ID
			ResponseObject parcelDetails = WS.sendRequest(findTestObject('Object Repository/Get Parcel By ID', parameter));
			WS.verifyResponseStatusCode(parcelDetails, 200);

			//get values from parcel detail response
			def slurper = new groovy.json.JsonSlurper();
			def parcelDetailsSlurper = slurper.parseText(parcelDetails.getResponseBodyContent());

			parameter.length = parcelDetailsSlurper.data.dimensions.volume.length as Double;
			parameter.width = parcelDetailsSlurper.data.dimensions.volume.width as Double;
			parameter.height = parcelDetailsSlurper.data.dimensions.volume.height as Double;
			parameter.weight = parcelDetailsSlurper.data.dimensions.weight.weight as Double;
			parameter.special_handling = parcelDetailsSlurper.data.flags.special_handling as int;
			parameter.storage_days = parcelDetailsSlurper.data.storage.total_days as int;

			//get Shipment cost
			//			TODO: Create parameters specific to shipping computation
			ResponseObject itemSelect = WS.sendRequest(findTestObject('Object Repository/Item Selection Shipping Computation', parameter));
			WS.verifyResponseStatusCode(itemSelect, 200);

			//get values shipment calculation response
			def slurper1 = new groovy.json.JsonSlurper();
			def itemSelectSlurper = slurper.parseText(itemSelect.getResponseBodyContent());

			//get chargeable weight, cargo fee, insurance fee, storage fee and total shipping fee
			shipCalc.getItemChargeableWeight(parameter.length, parameter.width, parameter.height, parameter.weight,
					GlobalVariable.minimum_chargeable_air, GlobalVariable.minimum_chargeable_sea);
			shipCalc.getTotalCargoFee(parameter.special_handling);
			shipCalc.getInsuranceFee(parameter.insured_value);
			shipCalc.getDefaultStorageFee(parameter.storage_days, parameter.weight)
			shipCalc.getTotalShippingFee();

			checkCalculations(shipCalc, itemSelectSlurper, ship_type)
		}
	}

	def checkCalculations(ShippingCalculation shipCalc, LazyMap itemSelectSlurper, String shipmentType){
		def shipmentTypeValues = itemSelectSlurper.get("data").get(shipmentType)

		//		TODO: change to camel case
		def actual_total_cargo_fee = shipmentTypeValues.usd.total_cargo as Double;
		def insurance_fee = shipmentTypeValues.usd.total_insurance as Double;
		def actual_chargeable_weight = shipmentTypeValues.usd.total_chargeable_weight as Double;
		def actual_shipping_fee = shipmentTypeValues.usd.total_price as Double;
		def actual_storage_fee = shipmentTypeValues.usd.total_storage_charge as Double;

		shipCalc.checkExpectedAndActualResults(GlobalVariable.total_cargo_fee, actual_total_cargo_fee);
		shipCalc.checkExpectedAndActualResults(GlobalVariable.item_chargeable, actual_chargeable_weight);
		shipCalc.checkExpectedAndActualResults(GlobalVariable.insurance_fee, insurance_fee);
		shipCalc.checkExpectedAndActualResults(GlobalVariable.total_storage_fee, actual_storage_fee);
		shipCalc.checkExpectedAndActualResults(GlobalVariable.total_shipping_fee, actual_shipping_fee);
	}

}