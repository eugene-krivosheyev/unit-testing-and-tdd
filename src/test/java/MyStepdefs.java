import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

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
}
