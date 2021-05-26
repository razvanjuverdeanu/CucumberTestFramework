Feature: Category

  Scenario Outline: Select random category and verify search results category
    Given that I open ebay
    And I select a random search category
    When I press search button
    Then Page header display the category title
    Examples:

  Scenario Outline: Apply price interval filter and order by highest price and check if the first product price is less or equal than the maximum value of interval
    Given that I open ebay
    And I select a random search category
    And I type "<text>" in search bar
    When I press search button
    Then Search results contains "<text>"
    And I set a "<type>" value of "<max_value>" to price filter
    When I sort by "<order_type>"
    Then The price of first product should be less or equal to the "<max_value>"
    Examples:
      | text | type    | max_value | order_type                      |
      | blue | Maximum | 100       | Price + Shipping: highest first |
      | red  | Maximum | 150       | Price + Shipping: highest first |


  Scenario Outline: Apply price interval filter and sort by lowest price and check if the first product price is greater or equal than the minimum value of interval
    Given that I open ebay
    And I select a random search category
    And I type "<text>" in search bar
    When I press search button
    Then Search results contains "<text>"
    And I set a "<type>" value of "<min_value>" to price filter
    When I sort by "<order_type>"
    Then The price of first product should be greater or equal to the "<min_value>"
    Examples:
      | text  | type    | min_value | order_type                     |
      | black | Minimum | 20.50     | Price + Shipping: lowest first |
      | white | Minimum | 55.40     | Price + Shipping: lowest first |