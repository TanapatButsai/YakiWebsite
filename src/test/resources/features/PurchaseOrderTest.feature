Feature: Purchase Order Calculation

  Background:
    Given a new purchase order

  Scenario: Calculate total price of a purchase order
    When I order 1 items with a menu price of 100.0 and a quantity of 2
    Then the total should be 200.0

  Scenario: Check if purchase order meets the minimum price requirement
    When I order 2 items with a menu price of 60 and a quantity of 3
    Then the order should meet the minimum price requirement

  Scenario: Calculate price differences of menus in a purchase order
    When I order 2 items with a menu price of 150.0 and a quantity of 2
    When I order 1 items with a menu price of 250.0 and a quantity of 3
    Then the total should be 1350.0

  Scenario: Check if a delivered order is marked as delivered
    Given the order should be delivered
    Then the order should be delivered