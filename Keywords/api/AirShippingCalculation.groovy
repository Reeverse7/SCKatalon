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

public class AirShippingCalculation extends ShippingCalculation {

	/**
	 * Send request and verify status code
	 * @param request request object, must be an instance of RequestObject
	 * @param expectedStatusCode
	 * @return a boolean to indicate whether the response status code equals the expected one
	 */
	@Keyword
	@Override
	def getItemChargeableWeight(double lngth, double wdth, double hght, double wght, double minChargeAir, double minChargeSea) {

		def volumetric_weight = ((lngth *wdth*hght)/166).round(2)

		//		if (shipment_type == "sea") {
		//			def item_chargeable_weight_sea = Math.max(volumetric_weight, minChargeSea);
		//			GlobalVariable.item_chargeable = item_chargeable_weight_sea;
		//			println "item chargeable weight for sea: " + GlobalVariable.item_chargeable
		//		}else if (shipment_type == "air"){
		def max1 = Math.max(volumetric_weight, wght);
		def item_chargeable_weight_air = Math.max(max1, minChargeAir);
		GlobalVariable.item_chargeable = item_chargeable_weight_air;
		println "item chargeable weight for air: " + GlobalVariable.item_chargeable
		//		}
	}

	@Keyword
	@Override
	def getTotalCargoFee(int spcl_hndling){
		double item_chargeable_weight;
		def lbs_value;
		double total_cargo_fee;

		//		if (ship_type == "air"){
		item_chargeable_weight = GlobalVariable.item_chargeable as Double;
		item_chargeable_weight = item_chargeable_weight.round(2);

		if(spcl_hndling == 1){
			lbs_value = GlobalVariable.per_pound_sh_air as Double;
			println("per pound value for air special handling: " + lbs_value)
		}else{
			lbs_value = GlobalVariable.per_pound_air as Double;
			println("per pound value for air cargo: " + lbs_value)
		}
		println("AIR Fixed fee: "+ GlobalVariable.air_fixed_fee)
		total_cargo_fee = ((item_chargeable_weight * lbs_value) + (GlobalVariable.air_fixed_fee as Double));
		GlobalVariable.total_cargo_fee = total_cargo_fee.round(2);
		println("Total Cargo Fee:" + GlobalVariable.total_cargo_fee);
		//		}else if (ship_type == "sea"){
		//			item_chargeable_weight = GlobalVariable.item_chargeable as Double;
		//			item_chargeable_weight = item_chargeable_weight.round(2);
		//
		//			if(spcl_hndling == 1){
		//				lbs_value = GlobalVariable.per_pound_sh_sea as Double;
		//				println("per pound value for sea special handling: " + lbs_value)
		//			}else{
		//				lbs_value = GlobalVariable.per_pound_sea as Double;
		//				println("per pound value for sea cargo: " + lbs_value)
		//			}
		//			println("SEA Fixed fee: "+ GlobalVariable.sea_fixed_fee)
		//			total_cargo_fee = ((item_chargeable_weight * lbs_value) + (GlobalVariable.sea_fixed_fee as Double));
		//			GlobalVariable.total_cargo_fee = total_cargo_fee.round(2);
		//			println("Total Cargo Fee:" + GlobalVariable.total_cargo_fee);
		//		}
	}


}
