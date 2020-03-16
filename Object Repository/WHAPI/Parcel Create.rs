<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>Parcel Creation using 24/7 endpoint</description>
   <name>Parcel Create</name>
   <tag></tag>
   <elementGuidId>65997966-c819-427e-895c-950bd432b3ad</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n  \&quot;data\&quot;: {\n    \&quot;type\&quot;: \&quot;parcels\&quot;,\n    \&quot;attributes\&quot;: {\n      \&quot;bin_name\&quot;: \&quot;${account_number}\&quot;,\n      \&quot;warehouse\&quot;: \&quot;${warehouse}\&quot;,\n      \&quot;received_at_warehouse\&quot;: \&quot;${item_created}\&quot;,\n      \&quot;tracking_number\&quot;: \&quot;${item_courier_tracking}\&quot;,\n      \&quot;courier\&quot;: \&quot;${item_courier}\&quot;,\n      \&quot;shop_name\&quot;: \&quot;${shop_name}\&quot;\n    }\n  }\n}&quot;,
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
   <restUrl>${base_url}/warehouse/api/parcels</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>GlobalVariable.base_url</defaultValue>
      <description></description>
      <id>d0eaa9a7-9ae8-488b-bae8-7f54f3856c48</id>
      <masked>false</masked>
      <name>base_url</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.token</defaultValue>
      <description></description>
      <id>abde62fe-f8d5-4282-ba6d-224563b8118a</id>
      <masked>false</masked>
      <name>token</name>
   </variables>
   <verificationScript>import static org.assertj.core.api.Assertions.*
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager
import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
