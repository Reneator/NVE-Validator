package rene.weiss.NVEAPP;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class DataController {


    @PostMapping(path="/validate")
    public Response validateNVE(@RequestParam String nve, @RequestParam(required = false) String checkDigit){
        Response response = NveValidator.validateNVE(nve, checkDigit);
        return response;
    }


    @GetMapping(path = "/test")
    public String getTestString(){
        return "Test";
    }
}
