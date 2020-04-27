package rene.weiss.NVEAPP;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class DataController {


    @PostMapping(path = "/validateparams")
    public Response validateNveParams(@RequestParam String nve, @RequestParam(required = false) String checkDigit) {
        Response response = NveValidator.validateNVE(nve, checkDigit);
        return response;
    }

    @PostMapping(path = "/validatebody")
    public Response validateNveBody(@RequestBody Map<String, String> requestBody) {
        String nve = requestBody.get("nve");
        String checkDigit = requestBody.get("checkDigit");
        Response response = NveValidator.validateNVE(nve, checkDigit);
        System.out.println(response);
        return response;
//        return null;
    }


    @GetMapping(path = "/test")
    public String getTestString() {
        return "Test";
    }
}
