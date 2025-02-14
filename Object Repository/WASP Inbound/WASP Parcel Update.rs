<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>WASP Parcel Update</name>
   <tag></tag>
   <elementGuidId>cbf8ebdb-92fd-40d1-a5d6-095b95eeae96</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <connectionTimeout>-1</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n\t\&quot;id\&quot;:\&quot;${parcel_id}\&quot;,\n\t\&quot;warehouse\&quot;:\&quot;${warehouse}\&quot;,\n\t\&quot;receivedAt\&quot;:\&quot;${received_at_warehouse}\&quot;,\n\t\&quot;customer\&quot;:{\n\t\t\&quot;account\&quot;:\&quot;${account_number}\&quot;,\n\t\t\&quot;name\&quot;:\&quot;${account_name}\&quot;,\n\t\t\&quot;type\&quot;:\&quot;premium\&quot;,\n\t\t\&quot;location\&quot;:\&quot;Philippine\&quot;\n\t},\n\t\&quot;dimensions\&quot;: [\n\t\t{\n\t\t\t\&quot;timestamp\&quot;:\&quot;${received_at_warehouse}\&quot;,\n\t\t\t\&quot;workstationId\&quot;:\&quot;ws_001\&quot;,\n\t\t\t\&quot;wmsUpdated\&quot;:\&quot;false\&quot;,\n\t\t\t\&quot;dimensions\&quot;:{\n\t\t\t\t\&quot;volume\&quot;:{\n\t\t\t\t\t\&quot;width\&quot;:0.01,\n\t\t\t\t\t\&quot;height\&quot;:0.01,\n\t\t\t\t\t\&quot;length\&quot;:0.01,\n\t\t\t\t\t\&quot;uom\&quot;:\&quot;in\&quot;\n\t\t\t\t\t},\n\t\t\t\t\t\&quot;weight\&quot;:{\n\t\t\t\t\t\t\&quot;weight\&quot;:0.01,\n\t\t\t\t\t\t\&quot;uom\&quot;:\&quot;lb\&quot;\n\t\t\t\t\t}\n\t\t\t\t}\n\t\t\t},\n\t\t\t{\n\t\t\t\&quot;timestamp\&quot;:\&quot;${updated_at_warehouse}\&quot;,\n\t\t\t\&quot;workstationId\&quot;:\&quot;ws_002\&quot;,\n\t\t\t\&quot;dimensions\&quot;:{\n\t\t\t\t\&quot;volume\&quot;:{\n\t\t\t\t\t\&quot;width\&quot;:${item_width},\n\t\t\t\t\t\&quot;height\&quot;:${item_height},\n\t\t\t\t\t\&quot;length\&quot;:${item_length},\n\t\t\t\t\t\&quot;uom\&quot;:\&quot;in\&quot;\n\t\t\t\t},\n\t\t\t\t\&quot;weight\&quot;:{\n\t\t\t\t\t\&quot;weight\&quot;:${item_weight},\n\t\t\t\t\t\&quot;uom\&quot;:\&quot;lb\&quot;\n\t\t\t\t}\n\t\t\t}\n\t\t}\n\t],\n\t\&quot;barcodes\&quot;: [\n\t\t{\n\t\t\t\&quot;id\&quot;:\&quot;Other1\&quot;,\n\t\t\t\&quot;code\&quot;:\&quot;${item_courier_tracking}\&quot;,\n\t\t\t\&quot;courier\&quot;:\&quot;Others\&quot;,\n\t\t\t\&quot;isTracking\&quot;:true\n\t\t}\t\n\t],\n\t\&quot;description\&quot;:{\n\t\t\&quot;description\&quot;:\&quot;${item_name}\&quot;,\n\t\t\&quot;allowedAir\&quot;:\&quot;${item_air}\&quot;,\n\t\t\&quot;allowedSea\&quot;:\&quot;${item_sea}\&quot;,\n\t\t\&quot;prohibited\&quot;:\&quot;${item_prohibited}\&quot;,\n\t\t\&quot;prohibitedDescription\&quot;:\&quot;${prohibited_note}\&quot;,\t\t\t\t\t\t\t \t\t\t\t\n        \&quot;dangerous\&quot;:\&quot;${item_dangerous}\&quot;,\n\t\t\&quot;specialHandlingItem\&quot;:\&quot;${special_handling}\&quot;,\n\t\t\&quot;inboundBin\&quot;:\&quot;${bin}\&quot;,\n\t\t\&quot;shopName\&quot;:\&quot;${shop_name}\&quot;,\n\t\t\&quot;rfid\&quot;:\&quot;${item_courier_tracking}\&quot;\n\t},\n\t\t\&quot;resources\&quot;:[{\n            \&quot;id\&quot;: \&quot;${image_id}\&quot;,\n            \&quot;type\&quot;: \&quot;IMAGE\&quot;,\n            \&quot;url\&quot;: \&quot;${image_url}\&quot;\n \t\t}],\n\t\t\&quot;packageItems\&quot;:[],\n  \t    \&quot;specialRequests\&quot;: null,\n\t    \&quot;hasPendingSpecialRequests\&quot;: false,\n\t    \&quot;processingSpecialRequests\&quot;: false,\n\t    \&quot;inboundProcessCompleted\&quot;: false,\n\t\t\&quot;packageType\&quot;:\&quot;INBOUND\&quot;,\n\t\t\&quot;parentPackageId\&quot;:null,\n\t\t\&quot;updateWMS\&quot;:true,\n\t\t\&quot;warehouseItemId\&quot;:\&quot;${warehouse_id}\&quot;,\n\t\t\&quot;status\&quot;:\&quot;WMS_CREATE_DONE\&quot;,\n\t\t\&quot;create\&quot;:{\n\t\t\t\&quot;timestamp\&quot;:${create_timestamp},\n\t\t\t\&quot;operator\&quot;:\&quot;ukreeve\&quot;\n\t\t},\n\t\t\&quot;update\&quot;:{\n\t\t\t\&quot;timestamp\&quot;:${updated_timestamp},\n\t\t\t\&quot;operator\&quot;:\&quot;ukreeve\&quot;\n\t\t},\n  \&quot;order\&quot;: null\n}&quot;,
  &quot;contentType&quot;: &quot;application/json&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;
}</httpBodyContent>
   <httpBodyType>text</httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>application/json</value>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Authorization</name>
      <type>Main</type>
      <value>${token}</value>
   </httpHeaderProperties>
   <maxResponseSize>-1</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>PUT</restRequestMethod>
   <restUrl>${base_url}/packages/${parcel_id}?unlock=true&amp;inboundProcessCompleted=true</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceEndpoint></soapServiceEndpoint>
   <soapServiceFunction></soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>true</useServiceInfoFromWsdl>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()
</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
