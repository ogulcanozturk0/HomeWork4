Feature:
  @order
  Scenario: Place an order for a pet
    When User makes order request
    And Order response status is 200
