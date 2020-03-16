<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>Get Shipping Estimate</name>
   <tag></tag>
   <elementGuidId>d16a27a7-f3d6-4a21-83ba-07d6b6b870d3</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n  \&quot;item\&quot;: {\n    \&quot;weight\&quot;: \&quot;${weight}\&quot;,\n    \&quot;length\&quot;: \&quot;${length}\&quot;,\n    \&quot;width\&quot;: \&quot;${width}\&quot;,\n    \&quot;height\&quot;: \&quot;${height}\&quot;,\n    \&quot;insured_value\&quot;: \&quot;${insured_value}\&quot;,\n    \&quot;dangerous\&quot;: \&quot;${dangerous}\&quot;,\n    \&quot;warehouse_id\&quot;: \&quot;${warehouse_id}\&quot;,\n    \&quot;special_handling\&quot;:\&quot;${special_handling}\&quot;,\n    \&quot;destination_country\&quot;: \&quot;${destination_country}\&quot;\n  }\n}&quot;,
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
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>${base_url}/api/shipping-estimate</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>GlobalVariable.base_url</defaultValue>
      <description></description>
      <id>d251182c-c772-4004-ac87-d9f729e32493</id>
      <masked>false</masked>
      <name>base_url</name>
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
