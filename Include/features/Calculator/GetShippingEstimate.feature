Feature: Calculate Shipping Cost Estimate based on user input

  Scenario: Calculate Shipping cost for US to PH orders
    Given I get the settings for "US" to "PH"
    When I calculate shipping estimate for shipment type "air"
    And I calculate shipping estimate for shipment type "sea"

  Scenario: Calculate Shipping cost for UK to PH orders
    Given I get the settings for "UK" to "PH"
    When I calculate shipping estimate for shipment type "air"
    And I calculate shipping estimate for shipment type "sea"

  Scenario: Calculate Shipping cost for US to MY orders
    Given I get the settings for "US" to "MY"
    When I calculate shipping estimate for shipment type "air"
  
  Scenario: Calculate Shipping cost for UK to MY orders
    Given I get the settings for "UK" to "MY"
    When I calculate shipping estimate for shipment type "air"
  