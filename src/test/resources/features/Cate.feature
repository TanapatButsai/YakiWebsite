Feature: Category Service Test
  @SpringBootTest
  Background:
    Given the Category Service is running

  @get-categories
  Scenario: Get All Categories
    When a user requests all categories
    Then the service should return a list of categories

  @create-category
  Scenario: Create a Category
    When a user creates a new category with the following details:
      | Name        |
      | TestCategory |
    Then the category should be created successfully

  @create-category-invalid
  Scenario: Create a Category with Invalid Data
    When a user creates a new category with the following details:
      | Name |
      |      |
    Then the category creation should fail with an error message
