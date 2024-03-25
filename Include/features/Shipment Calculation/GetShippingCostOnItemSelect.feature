
Feature: Calculate Shipping Cost upon selection of item in cart

  Scenario: Calculate Shipment cost for OR to PH parcel in Customer Cart
    Given I login my ShippingCart Account
    When I get the settings for "OR" to "PH"
    And I calculate "air" cargo shipment cost for single item
    And I calculate "sea" cargo shipment cost for single item
    

    