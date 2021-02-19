<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>Parcel Create for WASP</description>
   <name>WASP Parcel Create</name>
   <tag></tag>
   <elementGuidId>e58ea15c-4261-45c2-82a7-4f123ddf17ba</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n  \&quot;id\&quot;: null,\n  \&quot;warehouse\&quot;: \&quot;${warehouse}\&quot;,\n  \&quot;parentPackageId\&quot;: null,\n  \&quot;packageType\&quot;: \&quot;INBOUND\&quot;,\n  \&quot;customer\&quot;: {\n    \&quot;account\&quot;: \&quot;${account_number}\&quot;,\n    \&quot;name\&quot;: \&quot;WASP Four\&quot;,\n    \&quot;type\&quot;: \&quot;\&quot;\n  },\n  \&quot;receivedAt\&quot;: \&quot;2021-02-08T06:27:39.420Z\&quot;,\n  \&quot;dimensions\&quot;: [\n    {\n      \&quot;timestamp\&quot;: \&quot;2021-02-08T06:27:39.420Z\&quot;,\n      \&quot;workstationId\&quot;: \&quot;ws_001\&quot;,\n      \&quot;dimensions\&quot;: {\n        \&quot;volume\&quot;: {\n          \&quot;width\&quot;: 0.01,\n          \&quot;height\&quot;: 0.01,\n          \&quot;length\&quot;: 0.01,\n          \&quot;uom\&quot;: \&quot;in\&quot;\n        },\n        \&quot;weight\&quot;: {\n          \&quot;weight\&quot;: 0.01,\n          \&quot;uom\&quot;: \&quot;lb\&quot;\n        }\n      }\n    }\n  ],\n  \&quot;barcodes\&quot;: [\n    {\n      \&quot;id\&quot;: \&quot;Other 1\&quot;,\n      \&quot;code\&quot;: \&quot;${item_courier_tracking}\&quot;,\n      \&quot;courier\&quot;: \&quot;Others\&quot;,\n      \&quot;isTracking\&quot;: true\n    }\n  ],\n  \&quot;description\&quot;: {\n    \&quot;allowedAir\&quot;: false,\n    \&quot;allowedSea\&quot;: false,\n    \&quot;prohibited\&quot;: false,\n    \&quot;description\&quot;: \&quot;.\&quot;,\n    \&quot;prohibitedDescription\&quot;: \&quot;\&quot;,\n    \&quot;dangerous\&quot;: false,\n    \&quot;inboundBin\&quot;: \&quot;\&quot;,\n    \&quot;shopName\&quot;: \&quot;\&quot;,\n    \&quot;rfid\&quot;: \&quot;\&quot;\n  },\n  \&quot;resources\&quot;: [],\n  \&quot;updateWMS\&quot;: false,\n  \&quot;packageItems\&quot;: [],\n  \&quot;iteration\&quot;: 0\n}&quot;,
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
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>${base_url}/packages</restUrl>
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
