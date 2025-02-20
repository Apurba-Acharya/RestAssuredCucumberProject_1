package stepdefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

public class Products {
    public int StatusCode;
    public RequestSpecification httpRequest;
    public Response response;
    public int responseCode;
    public ResponseBody body;


    @Given("I hit the url of get products api endpoint")
    public void i_hit_the_url_of_get_products_api_endpoint() {
        RestAssured.baseURI = "https://fakestoreapi.com/";
    }

    @When("I pass the url in the request")
    public void i_pass_the_url_in_the_request() {
        httpRequest = RestAssured.given();
        response = httpRequest.get("products");
    }

    @Then("I receive the response code as {int}")
    public void i_receive_the_response_code_as(Integer int1) {
        responseCode = response.getStatusCode();
        Assert.assertEquals(responseCode, 200);
    }

    @Then("I verify that the rate of the first product is {}")
    public void i_verify_that_the_rate_of_the_first_product_is (String rate){
        body = response.getBody();

        //convert response body to string
        String responseBody = body.asString();

        //JSON representation from Response Body
        JsonPath jsonPath = response.jsonPath();

        String s = jsonPath.getJsonObject("rating[0].rate").toString();

        Assert.assertEquals(rate, s);
    }
}
