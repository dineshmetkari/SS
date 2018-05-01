import { Injectable } from '@angular/core';
import { LoginComponent } from "./login.component";
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from "@angular/router/";

@Injectable()
export class LoginService  implements CanActivate {

  constructor(private loginStatusCheck : LoginComponent, private router: Router) { }

  canActivate() {
    if(this.loginStatusCheck.getLoginStatus()) {
      return true;
    }
    else {
      this.router.navigate(['login']);
      return false;
    }
  }
}
