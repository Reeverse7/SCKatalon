<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>Update a parcel in ShippingCart using 24/7 endpoint</description>
   <name>Parcel Update</name>
   <tag></tag>
   <elementGuidId>a5f4f5b7-39e1-40fd-b34b-2a365c0e9dda</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{  \n   \&quot;data\&quot;:{  \n      \&quot;id\&quot;:${parcel_id},\n      \&quot;type\&quot;:\&quot;parcels\&quot;,\n      \&quot;attributes\&quot;:{  \n         \&quot;bin_name\&quot;:\&quot;${account_number}\&quot;,\n         \&quot;warehouse\&quot;:\&quot;${warehouse}\&quot;,\n         \&quot;tracking_number\&quot;:\&quot;${item_courier_tracking}\&quot;,\n         \&quot;courier\&quot;:\&quot;${item_courier}\&quot;,\n         \&quot;shop_name\&quot;:\&quot;${shop_name}\&quot;,\n         \&quot;received_at_warehouse\&quot;:\&quot;${item_updated}\&quot;,\n         \&quot;inbound_bin\&quot;:\&quot;${bin}\&quot;,\n         \&quot;description\&quot;:\&quot;${item_name}\&quot;,\n         \&quot;dimensions\&quot;:{  \n            \&quot;volume\&quot;:{  \n               \&quot;length\&quot;:${item_length},\n               \&quot;width\&quot;:${item_width},\n               \&quot;height\&quot;:${item_height},\n               \&quot;uom\&quot;:\&quot;in\&quot;\n            },\n            \&quot;weight\&quot;:{  \n               \&quot;weight\&quot;:${item_weight},\n               \&quot;uom\&quot;:\&quot;lb\&quot;\n            }\n         },\n         \&quot;flags\&quot;:{  \n            \&quot;insured\&quot;:0,\n            \&quot;dangerous\&quot;:${item_dangerous},\n            \&quot;prohibited\&quot;:${item_prohibited},\n            \&quot;allowed_air\&quot;:${item_air},\n            \&quot;allowed_sea\&quot;:${item_sea},\n            \&quot;special_handling\&quot;:${special_handling}\n         },\n         \&quot;notes\&quot;:[  \n            \&quot;.\&quot;\n         ],\n         \&quot;images\&quot;:[  \n            \&quot;https:\\/\\/s3-us-west-1.amazonaws.com\\/zone24x7-ocr\\/images\\/originals\\/61-123942-12112018203553-1.png\&quot;\n         ]\n      },\n      \&quot;links\&quot;:null\n   }\n}&quot;,
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
   <restUrl>${base_url}/warehouse/api/parcels/${parcel_id}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>GlobalVariable.parcel_id</defaultValue>
      <description></description>
      <id>1e912ed6-9212-41b6-8f1a-6d1c14bb8656</id>
      <masked>false</masked>
      <name>parcel_id</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.td_parcel_data</defaultValue>
      <description></description>
      <id>cdc07ca5-2a1c-4819-9720-0a2556b5da71</id>
      <masked>false</masked>
      <name>td_data</name>
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
