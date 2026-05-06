Feature:Login Tests

Scenario:Login Correct Email And Password
#Given I open the browser
Given I am in koel login page
When I input email "shahnaj.khatun@testpro.io"
And I input password "Faizan@123"
And I click log In
Then I am Logged in
