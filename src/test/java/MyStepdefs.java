import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;

public class MyStepdefs {
    @Given("given")
    public void given() {
        System.out.println("GGG");
    }

    @When("when")
    public void when() {
        System.out.println("WWW");
    }

    @Then("then")
    public void then() {
        System.out.println("TTT");
    }

    @Given("User logged in")
    public void userLoggedIn() {
        System.out.println("pleeeease log in");
    }

    @When("User click at {string}")
    public void userClickAt(String param) {
//        loginPage.login("sfg");
        System.out.println(param);
    }

    @Then("User sees items list")
    public void userSeesItemsList() {
//        catalogPage.getItems()
        assertTrue(true);
    }
}
