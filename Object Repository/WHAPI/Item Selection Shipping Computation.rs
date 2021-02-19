<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>Get shipping cost upon selecting item in cart</description>
   <name>Item Selection Shipping Computation</name>
   <tag></tag>
   <elementGuidId>5d9effe4-c9cc-4c60-8747-48c843ec6ced</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n  \&quot;token\&quot;:\&quot;${customer_token}\&quot;,\n  \&quot;air\&quot;: {\n    \&quot;items\&quot;: [\n      ${item_id}\n    ]\n  },\n  \&quot;sea\&quot;: {\n    \&quot;items\&quot;: [\n      ${item_id}\n    ]\n  },\n  \&quot;shipment_location_details\&quot;: {\n    \&quot;origin\&quot;: \&quot;${warehouse}\&quot;,\n    \&quot;destination\&quot;: \&quot;${destination}\&quot;\n  },\n  \&quot;insurance\&quot;: {\n    \&quot;${item_id}\&quot;: \&quot;${insurance}\&quot;\n  },\n  \&quot;warehouse_id\&quot;: {\n    \&quot;${item_id}\&quot;: ${warehouse}\n  }\n}&quot;,
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
      <value>Bearer ${customer_token}</value>
   </httpHeaderProperties>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>${base_url}/api/shipments</restUrl>
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
