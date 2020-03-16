<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>WASP Parcel Update</name>
   <tag></tag>
   <elementGuidId>cbf8ebdb-92fd-40d1-a5d6-095b95eeae96</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n  \&quot;id\&quot;: \&quot;${parcel_id}\&quot;,\n  \&quot;warehouse\&quot;: \&quot;${warehouse}\&quot;,\n  \&quot;receivedAt\&quot;: \&quot;2020-02-26T05:14:27.956Z\&quot;,\n  \&quot;customer\&quot;: {\n    \&quot;account\&quot;: \&quot;${account_number}\&quot;,\n    \&quot;name\&quot;: \&quot;WASP WMS\&quot;,\n    \&quot;type\&quot;: \&quot;premium\&quot;,\n    \&quot;location\&quot;: \&quot;Philippine\&quot;\n  },\n  \&quot;dimensions\&quot;: [\n    {\n      \&quot;timestamp\&quot;: \&quot;2020-02-26T05:14:27.956Z\&quot;,\n      \&quot;workstationId\&quot;: \&quot;ws_001\&quot;,\n      \&quot;wmsUpdated\&quot;: false,\n      \&quot;dimensions\&quot;: {\n        \&quot;volume\&quot;: {\n          \&quot;width\&quot;: 0.01,\n          \&quot;height\&quot;: 0.01,\n          \&quot;length\&quot;: 0.01,\n          \&quot;uom\&quot;: \&quot;in\&quot;\n        },\n        \&quot;weight\&quot;: {\n          \&quot;weight\&quot;: 0.01,\n          \&quot;uom\&quot;: \&quot;lb\&quot;\n        }\n      }\n    },\n    {\n      \&quot;timestamp\&quot;: \&quot;2020-02-26T05:14:27.956Z\&quot;,\n      \&quot;workstationId\&quot;: \&quot;ws_002\&quot;,\n      \&quot;dimensions\&quot;: {\n        \&quot;volume\&quot;: {\n          \&quot;width\&quot;:\&quot;${item_width}\&quot;,\n          \&quot;height\&quot;:\&quot;${item_height}\&quot;,\n          \&quot;length\&quot;:\&quot;${item_length}\&quot;,\n          \&quot;uom\&quot;: \&quot;in\&quot;\n        },\n        \&quot;weight\&quot;: {\n          \&quot;weight\&quot;:\&quot;${item_weight}\&quot;,\n          \&quot;uom\&quot;: \&quot;lb\&quot;\n        }\n      }\n    }\n  ],\n  \&quot;barcodes\&quot;: [\n    {\n      \&quot;id\&quot;: \&quot;Other 1\&quot;,\n      \&quot;code\&quot;: \&quot;${item_courier_tracking}\&quot;,\n      \&quot;courier\&quot;: \&quot;Others\&quot;,\n      \&quot;isTracking\&quot;: true\n    }\n  ],\n  \&quot;description\&quot;: {\n    \&quot;description\&quot;: \&quot;${item_name}\&quot;,\n    \&quot;allowedAir\&quot;: \&quot;${item_air}\&quot;,\n    \&quot;allowedSea\&quot;: \&quot;${item_sea}\&quot;,\n    \&quot;prohibited\&quot;: \&quot;${item_prohibited}\&quot;,\n    \&quot;prohibitedDescription\&quot;: \&quot;\&quot;,\n    \&quot;dangerous\&quot;: \&quot;${item_dangerous}\&quot;,\n    \&quot;specialHandlingItem\&quot;: \&quot;${special_handling}\&quot;,\n    \&quot;inboundBin\&quot;: \&quot;${bin}\&quot;,\n    \&quot;shopName\&quot;: \&quot;Amazon\&quot;,\n    \&quot;rfid\&quot;: \&quot;${warehouse_id}\&quot;\n  },\n  \&quot;resources\&quot;: [],\n  \&quot;packageItems\&quot;: [],\n  \&quot;packageType\&quot;: \&quot;INBOUND\&quot;,\n  \&quot;parentPackageId\&quot;: null,\n  \&quot;updateWMS\&quot;: true,\n  \&quot;warehouseItemId\&quot;:\&quot;${warehouse_id}\&quot;,\n  \&quot;status\&quot;: \&quot;WMS_CREATE_DONE\&quot;,\n  \&quot;create\&quot;: {\n    \&quot;timestamp\&quot;: 1581657321533,\n    \&quot;operator\&quot;: \&quot;SCTEAM\&quot;\n  },\n  \&quot;update\&quot;: {\n    \&quot;timestamp\&quot;: 1581657875898,\n    \&quot;operator\&quot;: \&quot;SCTEAM\&quot;\n  }\n}&quot;,
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
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>PUT</restRequestMethod>
   <restUrl>${base_url}/packages/${parcel_id}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
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
