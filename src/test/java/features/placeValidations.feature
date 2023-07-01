Feature: Validating Place API's
  Scenario: Verify If Place is being Successfully added using AddPlaceAPI
    Given Add Place Payload
    When User calls "AddPlaceAPI" with post http request
    Then the API call is success with status code 200
    And "status" in response body as "OK"
    And "scope" in response body as "APP"