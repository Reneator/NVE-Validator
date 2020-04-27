import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {ValidationResult} from "../objects/validation-result";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {ValidationRequest} from "../objects/validation-request";

@Injectable({
  providedIn: 'root'
})
export class ValidationService {
  private endpoint = 'http://localhost:8080/validatebody/';


  constructor(private http: HttpClient) {
  }


  validate(nve: string, checkDigit: string) : Observable<ValidationResult> {
    const httpOptions= {headers: new HttpHeaders({'Content-Type':'application/json'})};
    // const headers = {'Content-Type': 'application/json'};



    let request = new ValidationRequest
    request.nve = nve
    request.checkDigit = checkDigit

    return this.http.post<ValidationResult>(this.endpoint, {"nve": nve, "checkDigit": checkDigit}, httpOptions)
  }
}
