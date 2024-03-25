Feature: Calculate Shipping Cost Estimate based on user input

  Scenario: Calculate Shipping cost for US to PH orders
    Given I get the settings for "AU" to "PH"
    When I calculate shipping estimate for shipment type "sea"
    #And I calculate shipping estimate for shipment type "sea"

  #Scenario: Calculate Shipping cost for UK to PH orders
    #Given I get the settings for "UK" to "PH"
    #When I calculate shipping estimate for shipment type "air"
    #And I calculate shipping estimate for shipment type "sea"
#
  #Scenario: Calculate Shipping cost for US to MY orders
    #Given I get the settings for "OR" to "MY"
    #When I calculate shipping estimate for shipment type "air"
  #
  #Scenario: Calculate Shipping cost for UK to M` orders
    #Given I get the settings for "UK" to "MY"
    #When I calculate shipping estimate for shipment type "air"
  #
  #Scenario: Calculate Shipping cost for AU to PH orders
    #Given I get the settings for "AU" to "PH"
    #When I calculate shipping estimate for shipment type "air"
    #And I calculate shipping estimate for shipment type "sea"
    #
  #Scenario: Calculate Shipping cost for AU to PH orders
    #Given I get the settings for "AU" to "MY"
    #When I calculate shipping estimate for shipment type "air"