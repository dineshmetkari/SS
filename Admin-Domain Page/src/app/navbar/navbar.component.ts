import { Component, OnInit } from "@angular/core";
import { MatIconModule } from "@angular/material";
import { Router } from "@angular/router";

@Component({
  selector: "app-navbar",
  templateUrl: "./navbar.component.html",
  styleUrls: ["./navbar.component.css"],
  inputs: ["role"]
})
export class NavbarComponent implements OnInit {
  loggedIn: string;
  role: string;
  constructor(private router: Router) {}
  value: boolean;

  ngOnInit() {
    this.loggedIn = "true";
    // console.log("loggedIn " + this.loggedIn);
    // console.log("in navbar " + this.role);
    if (this.role === "Welcome") {
      this.value = true;
      // console.log("value " + this.value);
    } else if (this.role == null) {
      this.role = "Welcome";
      this.value = true;
    } else {
      this.value = false;
      // console.log(this.value);
    }
  }

  logout() {
    this.loggedIn = "false";
    this.router.navigateByUrl("/home");
  }
}
