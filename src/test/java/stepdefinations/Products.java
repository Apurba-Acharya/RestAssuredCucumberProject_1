package stepdefinations;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;

public class Products {
    public int StatusCode;
    public RequestSpecification httpRequest;
    public Response response;
    public int responseCode;
    public ResponseBody body;
    public JSONObject requestParams;
    public JsonPath jsnpath;


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

    @Given("I hit the url of post product api endpoint")
    public void i_hit_the_url_of_post_product_api_endpoint() {
        RestAssured.baseURI = "https://fakestoreapi.com/";
        httpRequest = RestAssured.given();
        requestParams = new JSONObject();

    }

    @And("I pass the request body of the product title {}")
    public void i_pass_the_request_body_of_the_product_title(String title) {
        requestParams.put("title", title);
        requestParams.put("price", 13.5);
        requestParams.put("description", "lorem ipsum set");
        requestParams.put("image", "https://i.pravatar.cc");
        requestParams.put("category", "electronic");

        httpRequest.body(requestParams.toJSONString());
        Response response = httpRequest.post("products");
        ResponseBody Body = response.getBody();

        System.out.println(response.getStatusLine());
        System.out.println(Body.asString());
    }

    @Then("I receive the response body with id as {}")
    public void i_receive_the_response_body_with_id_as(String id) {
        jsnpath = response.jsonPath();
        String s = jsnpath.getJsonObject("id").toString();
        Assert.assertEquals("21", s);
    }

    @Given("I hit the url of put product api endpoint")
    public void i_hit_the_url_of_put_product_api_endpoint() {
        RestAssured.baseURI = "https://fakestoreapi.com/";
        requestParams = new JSONObject();
        
    }

    @When("I pass the url of products in the request with {}")
    public void i_pass_the_url_of_products_in_the_request_with(String productnumber) {
        httpRequest = RestAssured.given();
        requestParams.put("title", "test product");
        requestParams.put("price", 13.5);
        requestParams.put("description", "lorem ipsum set");
        requestParams.put("image", "https://i.pravatar.cc");
        requestParams.put("category", "electronic");
        httpRequest.body(requestParams.toJSONString());
        response = httpRequest.put("products/"+ productnumber);
        ResponseBody Body = response.getBody();
        jsnpath = response.jsonPath();
        String s = jsnpath.getJsonObject("id").toString();
        System.out.println(response.getStatusLine());
        System.out.println(Body.asString());
    }

    @Given("I hit the url of delete product api endpoint")
    public void i_hit_the_url_of_delete_product_api_endpoint() {
        RestAssured.baseURI = "https://fakestoreapi.com/";
        requestParams = new JSONObject();
    }

    @When("I pass the url of delete products in the request with {}")
    public void i_pass_the_url_of_delete_products_in_the_request_with(String productnumber) {
        httpRequest = RestAssured.given();
        requestParams.put("title", "test product");
        requestParams.put("price", 13.5);
        requestParams.put("description", "lorem ipsum set");
        requestParams.put("image", "https://i.pravatar.cc");
        requestParams.put("category", "electronic");
        httpRequest.body(requestParams.toJSONString());
        response = httpRequest.delete("products/"+ productnumber);
        ResponseBody Body = response.getBody();
        jsnpath = response.jsonPath();
        String s = jsnpath.getJsonObject("id").toString();
        System.out.println(response.getStatusLine());
        System.out.println(Body.asString());
    }
}
