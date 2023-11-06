package ku.cs.YakinikuWebsite;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import org.springframework.beans.factory.annotation.Autowired;
import ku.cs.YakinikuWebsite.service.CategoryService;
import ku.cs.YakinikuWebsite.entity.Category;
import ku.cs.YakinikuWebsite.model.CategoryRequest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
@Component
@SpringBootTest
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    private List
            <Category> categories;
    private CategoryRequest categoryRequest;
    private String errorMessage;

    @Given("the Category Service is running")
    public void theCategoryServiceIsRunning() {
        categoryService = new CategoryService();
    }

    @When("a user requests all categories")
    public void aUserRequestsAllCategories() {
        categories = categoryService.getAllCategories();
    }

    @Then("the service should return a list of categories")
    public void theServiceShouldReturnAListOfCategories() {
        assertTrue(categories != null && !categories.isEmpty());
    }

    @When("a user creates a new category with the following details:")
    public void aUserCreatesANewCategoryWithTheFollowingDetails(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps();
        Map<String, String> categoryData = rows.get(0);

        categoryRequest = new CategoryRequest();
        categoryRequest.setName(categoryData.get("Name"));
    }

    @Then("the category should be created successfully")
    public void theCategoryShouldBeCreatedSuccessfully() {
        categoryService.createCategory(categoryRequest);

    }

    @Then("the category creation should fail with an error message")
    public void theCategoryCreationShouldFailWithAnErrorMessage() {
        try {
            categoryService.createCategory(categoryRequest);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }

        assertEquals("Expected error message", errorMessage, "Actual error message");
    }
}
