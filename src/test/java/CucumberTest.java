import cucumber.api.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/Demo.feature"},
        plugin = {"pretty", "html:target/cucumber-html-report"}
)
public class CucumberTest {
    @Test
    public void test01() {

    }
}
