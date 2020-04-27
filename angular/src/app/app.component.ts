import {Component} from '@angular/core';
import {ValidationService} from "./services/validation.service";
import {ValidationResult} from "./objects/validation-result";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'angular';

  nve_list: string[] = [];

  errorMessage: string;
  nve: string;
  checkDigit: string;

  validate() {
    this.errorMessage = null
    this.validationService.validate(this.nve, this.checkDigit).subscribe((result: ValidationResult) => {
      if (result.errorMessage) {
        this.errorMessage = result.errorMessage;
      } else {
        this.nve_list.push(result.nve);
      }
    })

  }

  constructor(private validationService: ValidationService) {
  }

  ngOnInit() {
  }

}
