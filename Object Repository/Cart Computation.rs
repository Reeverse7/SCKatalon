<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>Get shipping cost upon selecting item in cart</description>
   <name>Cart Computation</name>
   <tag></tag>
   <elementGuidId>5d9effe4-c9cc-4c60-8747-48c843ec6ced</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <autoUpdateContent>false</autoUpdateContent>
   <connectionTimeout>-1</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n  \&quot;token\&quot;:\&quot;${customer_token}\&quot;,\n  \&quot;air\&quot;: {\n    \&quot;items\&quot;: [\n      ${air_item_id}\n    ]\n  },\n  \&quot;sea\&quot;:{\n    \&quot;items\&quot;: [\n      ${sea_item_id}\n    ]\n  },\n  \&quot;insurance\&quot;: {\n    \&quot;${item_id}\&quot;: \&quot;${insured_value}\&quot;\n  },\n  \&quot;sc_protect\&quot;: {\n    \&quot;avail\&quot;: \&quot;${sc_protect}\&quot;\n  },\n  \&quot;shipment_location_details\&quot;: {\n    \&quot;origin\&quot;: \&quot;${origin}\&quot;,\n    \&quot;destination\&quot;: \&quot;${destination}\&quot;\n  },\n  \&quot;warehouse_id\&quot;: {\n    \&quot;${item_id}\&quot;: ${warehouse_id}\n  }\n}&quot;,
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
      <webElementGuid>6b48c27c-7031-4ca1-936b-03b8bce2a131</webElementGuid>
   </httpHeaderProperties>
   <maxResponseSize>-1</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>${base_url}/api/shipments</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceEndpoint></soapServiceEndpoint>
   <soapServiceFunction></soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>true</useServiceInfoFromWsdl>
   <variables>
      <defaultValue>GlobalVariable.base_url</defaultValue>
      <description></description>
      <id>6e94ef30-edbf-4be9-aee4-ea685db82fd0</id>
      <masked>false</masked>
      <name>base_url</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.customer_token</defaultValue>
      <description></description>
      <id>ba896447-6998-4f25-904d-5112f38f4691</id>
      <masked>false</masked>
      <name>customer_token</name>
   </variables>
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
