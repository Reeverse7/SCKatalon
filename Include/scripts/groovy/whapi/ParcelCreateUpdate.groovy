package whapi
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

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When


class ParcelCreateUpdate {

	@Given("I send Parcel Create and Parcel Update Request")
	def send_parcel_create_request() {
		def parameter = [:];
		parameter.base_url = GlobalVariable.base_url;
		parameter.token = GlobalVariable.token;

		def row_count = (GlobalVariable.td_parcel_data).getRowNumbers();
		for (int i = 1; i <= row_count; i++) {
			parameter.account_number = GlobalVariable.td_parcel_data.getValue("account_number",i);
			parameter.warehouse = GlobalVariable.td_parcel_data.getValue("warehouse",i);
			parameter.item_courier_tracking = GlobalVariable.td_parcel_data.getValue("item_courier_tracking",i);
			parameter.item_courier = GlobalVariable.td_parcel_data.getValue("item_courier",i);
			parameter.shop_name = GlobalVariable.td_parcel_data.getValue("shop_name",i);
			parameter.item_created = GlobalVariable.td_parcel_data.getValue("item_created",i);
			parameter.item_updated = GlobalVariable.td_parcel_data.getValue("item_updated",i);
			parameter.bin = GlobalVariable.td_parcel_data.getValue("bin",i);
			parameter.item_name = GlobalVariable.td_parcel_data.getValue("item_name",i);
			parameter.item_length = GlobalVariable.td_parcel_data.getValue("item_length",i);
			parameter.item_width = GlobalVariable.td_parcel_data.getValue("item_width",i);
			parameter.item_height = GlobalVariable.td_parcel_data.getValue("item_height",i);
			parameter.item_weight = GlobalVariable.td_parcel_data.getValue("item_weight",i);
			parameter.item_dangerous = GlobalVariable.td_parcel_data.getValue("item_dangerous",i);
			parameter.item_prohibited = GlobalVariable.td_parcel_data.getValue("item_prohibited",i);
			parameter.special_handling = GlobalVariable.td_parcel_data.getValue("special_handling",i);
			parameter.item_air = GlobalVariable.td_parcel_data.getValue("item_air",i);
			parameter.item_sea = GlobalVariable.td_parcel_data.getValue("item_sea",i);

			ResponseObject pcresponse = WS.sendRequest(findTestObject('Object Repository/WHAPI/Parcel Create', parameter));
			WS.verifyResponseStatusCode(pcresponse, 200)

			def slurper = new groovy.json.JsonSlurper()
			def result = slurper.parseText(pcresponse.getResponseBodyContent())
			def parcelID = result.data.id
			GlobalVariable.parcel_id = parcelID
			parameter.parcel_id = GlobalVariable.parcel_id;

			ResponseObject puresponse = WS.sendRequest(findTestObject('Object Repository/WHAPI/Parcel Update',parameter));
			WS.verifyResponseStatusCode(puresponse, 200)
		}
	}
}