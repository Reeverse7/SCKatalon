
Feature: Calculate Shipping Cost upon selection of item in cart

  #Scenario: Calculate Shipment cost for US to PH parcel in Customer Cart
    #Given I get the settings for "US" to "PH"
    #When I login my ShippingCart Account
    #And I calculate "air" cargo shipment cost for single item
    #And I calculate "sea" cargo shipment cost for single item
    
  Scenario: Calculate Shipment cost for US to MY parcel in Customer Cart
    Given I get the settings for "US" to "MY"
    When I login my ShippingCart Account
    And I calculate "air" cargo shipment cost for single item
    