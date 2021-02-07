import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms'
import { Router } from '@angular/router';
import { RegistrationService } from '../registration.service';
import { User } from '../user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user = new User();
  msg = "";

  constructor(private _service: RegistrationService,private _router:Router) { }

  ngOnInit() {
  }


  loginUser() {
    this._service.loginUserFormRemote(this.user).subscribe(
      data => {
        console.log("response receieved");
        this._router.navigate(["login"]);

      },
      error => {
        console.log("exception occurred");
        this.msg = "Bad Credentials, Please enter valid emailId and password";
      }
    )
  }
}
