Feature: The user can change their own password
  Rule: As a user
  I want to be able to change my own password within the guidelines of password policy
  So that the integrity of my account security requirements are met.

    @regression
    Scenario: Successful Password Change
      Given the user is logged in to the system
      When the user enters their current username "username"
      And the user enters their current password "current password"
      And the user enters a new password that meets the requirements (at least 3 characters with at least one digit) "new_password123"
      And the user confirms the new password by re-typing it "new_password123"
      Then the system should update the password successfully
      And the system should display a success message "Password changed successfully"

    @regression
    Scenario: Password Change - Invalid Username
      Given the user is logged in to the system
      When the user enters an incorrect username "invalid_username"
      And the user enters their current password "current password"
      And the user enters a new password that meets the requirements (at least 3 characters with at least one digit) "new_password123"
      And the user confirms the new password by re-typing it "new_password123"
      Then the system should not update the password
      And the system should display an error message "Invalid username"

    @regression
    Scenario: Password Change - Invalid Current Password
      Given the user is logged in to the system
      When the user enters their current username "username"
      And the user enters an incorrect current password "invalid_password"
      And the user enters a new password that meets the requirements (at least 3 characters with at least one digit) "new_password123"
      And the user confirms the new password by re-typing it "new_password123"
      Then the system should not update the password
      And the system should display an error message "Invalid current password"

    @regression
    Scenario: Password Change - New Password Does Not Meet Minimum Length Requirement
      Given the user is logged in to the system
      When the user enters their current username "username"
      And the user enters their current password "current password"
      And the user enters a new password that does not meet the minimum length requirement (less than 3 characters) "short"
      And the user confirms the new password by re-typing it "short"
      Then the system should not update the password
      And the system should display an error message "Password does not meet minimum length requirement" (at least 3 characters)

    @regression
    Scenario: Password Change - New Password Does Not Contain a Digit
      Given the user is logged in to the system
      When the user enters their current username "username"
      And the user enters their current password "current password"
      And the user enters a new password that does not contain a digit "allletters"
      And the user confirms the new password by re-typing it "allletters"
      Then the system should not update the password
      And the system should display an error message "Password does not meet requirements" (must contain at least one digit)

    @regression
    Scenario: Password Change - New Password and Confirm Password Do Not Match
      Given the user is logged in to the system
      When the user enters their current username "username"
      And the user enters their current password "current password"
      And the user enters a new password "new_password123"
      And the user confirms the new password with a typo "wrong_password"
      Then the system should not update the password
      And the system should display an error message "Passwords do not match”