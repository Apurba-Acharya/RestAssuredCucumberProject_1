Feature: insert product using POST API

  Scenario Outline: validate post product api status code works correctly
    Given I hit the url of post product api endpoint
    When I pass the url in the request
    And I pass the request body of the product title <ProductTitles>
    Then I receive the response code as 200
    Examples:
      | ProductTitles |
      | shoes         |


  Scenario Outline: validate post product api response body works correctly
    Given I hit the url of post product api endpoint
    When I pass the url in the request
    And I pass the request body of the product title <ProductTitles>
    Then I receive the response body with id as <id>
    Examples:
      | ProductTitles | id |
      | shoes         | 21 |