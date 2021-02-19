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
import groovy.json.JsonSlurper
import groovy.json.internal.LazyMap
import api.*


class ItemCartShipmentCalculation {

	@When('I calculate "(.*)" cargo shipment cost for single item')
	def I_update_insured_value(String ship_type) {

		//update parcel insured value
		def parcel_param =  [:]
		def cart_calc_param = [:]
		def row_count = (GlobalVariable.td_cart_items).getRowNumbers()

		def ship_calc
		if (ship_type == "air"){
			ship_calc = new AirShippingCalculation()
		} else if (ship_type == "sea") {
			ship_calc = new SeaShippingCalculation()
		} else {
			throw new IllegalArgumentException("Shipment type " + ship_type + " not supported.")
		}
		
		for (int i = 1; i <= row_count; i++) {

			parcel_param.insured_value = GlobalVariable.td_cart_items.getValue("insured_value",i) as Double
			parcel_param.item_id = GlobalVariable.td_cart_items.getValue("item_id",i)

			//send update insured value request and verify response
			ResponseObject update_insured_val = WS.sendRequest(findTestObject('Update Item Insured Value', parcel_param))
			WS.verifyResponseStatusCode(update_insured_val, 200)

			//get parcel details
			ResponseObject get_parcel_info = WS.sendRequest(findTestObject('Get Parcel By ID', parcel_param))
			WS.verifyResponseStatusCode(get_parcel_info, 200)

			//get values from parcel detail response
			def parcel_slurper = new groovy.json.JsonSlurper()
			def parcel_detail_slurper = parcel_slurper.parseText(get_parcel_info.getResponseBodyContent())

			cart_calc_param.length = parcel_detail_slurper.data.dimensions.volume.length as Double
			cart_calc_param.width = parcel_detail_slurper.data.dimensions.volume.width as Double
			cart_calc_param.height = parcel_detail_slurper.data.dimensions.volume.height as Double
			cart_calc_param.weight = parcel_detail_slurper.data.dimensions.weight.weight as Double
			cart_calc_param.special_handling = parcel_detail_slurper.data.flags.special_handling as int
			cart_calc_param.storage_days = parcel_detail_slurper.data.storage.total_days as int

			cart_calc_param.warehouse_id = GlobalVariable.warehouse_id
			cart_calc_param.origin = GlobalVariable.origin
			cart_calc_param.destination = GlobalVariable.destination

			//identify cargo type

			if (ship_type == "air"){
				cart_calc_param.air_item_id = parcel_param.item_id
				cart_calc_param.sea_item_id = ''
			} else if (ship_type == "sea") {
				cart_calc_param.sea_item_id = parcel_param.item_id
				cart_calc_param.air_item_id = ''
			}

			//get Shipment cost
			ResponseObject cart_computation = WS.sendRequest(findTestObject('Cart Computation', cart_calc_param))
			WS.verifyResponseStatusCode(cart_computation, 200)

			//get values shipment calculation response
			def cart_slurper = new groovy.json.JsonSlurper()
			def cart_compute_slurper = parcel_slurper.parseText(cart_computation.getResponseBodyContent())

			//get chargeable weight, cargo fee, insurance fee, storage fee and total shipping fee
			ship_calc.getItemChargeableWeight(cart_calc_param.length, cart_calc_param.width, cart_calc_param.height, cart_calc_param.weight,
					GlobalVariable.minimum_chargeable_air, GlobalVariable.minimum_chargeable_sea)
			ship_calc.getTotalCargoFee(cart_calc_param.special_handling)
			ship_calc.getInsuranceFee(parcel_param.insured_value)
			ship_calc.getDefaultStorageFee(cart_calc_param.storage_days, cart_calc_param.weight)
			ship_calc.getTotalShippingFee()

			checkCalculations(ship_calc, cart_compute_slurper, ship_type)
		}
	}

	def checkCalculations(ShippingCalculation ship_calc, LazyMap cart_compute_map, String shipment_type){
		def shipment_type_values = cart_compute_map.get("data").get(shipment_type)

		def actual_total_cargo_fee = shipment_type_values.usd.total_cargo as Double
		def insurance_fee = shipment_type_values.usd.total_insurance as Double
		def actual_chargeable_weight = shipment_type_values.usd.total_chargeable_weight as Double
		def actual_shipping_fee = shipment_type_values.usd.total_price as Double
		def actual_storage_fee = shipment_type_values.usd.total_storage_charge as Double

		ship_calc.checkExpectedAndActualAmount(GlobalVariable.total_cargo_fee, actual_total_cargo_fee)
		ship_calc.checkExpectedAndActualAmount(GlobalVariable.item_chargeable, actual_chargeable_weight)
		ship_calc.checkExpectedAndActualAmount(GlobalVariable.insurance_fee, insurance_fee)
		ship_calc.checkExpectedAndActualAmount(GlobalVariable.total_storage_fee, actual_storage_fee)
		ship_calc.checkExpectedAndActualAmount(GlobalVariable.total_shipping_fee, actual_shipping_fee)
	}

}