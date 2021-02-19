package ssm
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



class AddBox {
	/**
	 * The step definitions below match with Katalon sample Gherkin steps
	 */
	@Given("I send Shipment Add Box Request")
	def I_send_shipment_add_box_request() {
		def parameter = [:];
		parameter.base_url = GlobalVariable.base_url;
		//parameter.token = GlobalVariable.token;

		def row_count = (GlobalVariable.td_orders).getRowNumbers();
		for (int i = 1; i <= row_count; i++) {
			parameter.order_tn = GlobalVariable.td_orders.getValue("order_tn",i);
			parameter.lbc_tn = GlobalVariable.td_orders.getValue("lbc_tn",i);
			parameter.order_length = GlobalVariable.td_orders.getValue("order_length",i);
			parameter.order_width = GlobalVariable.td_orders.getValue("order_width",i);
			parameter.order_height = GlobalVariable.td_orders.getValue("order_height",i);
			parameter.order_weight = GlobalVariable.td_orders.getValue("order_weight",i);
			parameter.item_id_one = GlobalVariable.td_orders.getValue("item_id_one",i);
			parameter.item_id_two = GlobalVariable.td_orders.getValue("item_id_two",i);
			parameter.item_id_three = GlobalVariable.td_orders.getValue("item_id_three",i);
			parameter.item_id_four = GlobalVariable.td_orders.getValue("item_id_four",i);

			ResponseObject pcresponse = WS.sendRequest(findTestObject('Object Repository/SSM/Add Box', parameter));
			WS.verifyResponseStatusCode(pcresponse, 200)

			def slurper = new groovy.json.JsonSlurper()
			def result = slurper.parseText(pcresponse.getResponseBodyContent())
		}
	}

	@Given("I send SKU and Inbound Request")
	def I_send_sku_inbound_request() {
		def parameter = [:];

		def row_count = (GlobalVariable.td_wms).getRowNumbers();
		for (int i = 1; i <= row_count; i++) {
			parameter.data = GlobalVariable.td_wms.getValue("data",i) as String;

			ResponseObject response = WS.sendRequest(findTestObject('Object Repository/SSM/Create SKU Inbound', parameter));
			WS.verifyResponseStatusCode(response, 200)

			def slurper = new groovy.json.JsonSlurper()
			def result = slurper.parseText(response.getResponseBodyContent())

			def slurper1 = new groovy.json.JsonSlurper();
			def respBody = slurper1.parseText(response.getResponseBodyContent());

			def results = respBody.resp.Response.value as String
			if(results == "FAILED"){
				KeywordUtil.markWarning("ERROR IN SENDING SKU")
				System.out.println(respBody.resp.Desc.value)
				System.out.println(parameter.data)
			}else{
				System.out.println(respBody.resp.Desc.value)
			}
		}
	}
}