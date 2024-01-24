Feature: Validation place Api

  @tag1
  Scenario: Verify if place is being succesfully added with AddPlaceAPI
    Given Add Place Payload
    When user calls "AddPlaceApi" with post http request
    Then the API got status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"

