Feature: User Controller Feature
  
  Scenario: Calling getAllUsers with no records in db
    When getAllUsers api is called with no records in DB
    Then api should return NOT_FOUND error

