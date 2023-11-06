package ku.cs.YakinikuWebsite;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ku.cs.YakinikuWebsite.entity.Menu;
import ku.cs.YakinikuWebsite.entity.OrderItem;
import ku.cs.YakinikuWebsite.entity.PurchaseOrder;
import ku.cs.YakinikuWebsite.status.Status;
import org.junit.Assert;

public class PurchaseOrderTest {
    private PurchaseOrder purchaseOrder;
    private double totalPrice;

    @Given("a new purchase order")
    public void createNewPurchaseOrder() {
        purchaseOrder = new PurchaseOrder();
    }

    @When("I order {int} items with a menu price of {double} and a quantity of {int}")
    public void addItemsWithMenuPriceAndQuantity(int itemCount, double menuPrice, int quantity) {
        for (int i = 0; i < itemCount; i++) {
            OrderItem orderItem = new OrderItem();
            Menu menu = new Menu();
            menu.setPrice(menuPrice);
            orderItem.setMenu(menu);
            orderItem.setQuantity(quantity);
            purchaseOrder.getItems().add(orderItem);
        }
    }


    @Then("the total should be {double}")
    public void calculateTotal(double expectedTotal) {
        totalPrice = purchaseOrder.getTotal();
        Assert.assertEquals(expectedTotal, totalPrice, 0.01);
    }

    @And("the order should meet the minimum price requirement")
    public void checkMinimumPriceRequirement() {
        boolean meetsMinimumPrice = purchaseOrder.checkMinimumPrice();
        Assert.assertTrue(meetsMinimumPrice);
    }

    @And("the order should be delivered")
    public void checkOrderDeliveryStatus() {
        purchaseOrder.setStatus(Status.DELIVERED);
        boolean isDelivered = purchaseOrder.isDelivered();
        Assert.assertTrue(isDelivered);
    }

    }
