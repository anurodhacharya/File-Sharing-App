import { Component } from '@angular/core';

@Component({
  selector: 'app-body',
  templateUrl: './body.component.html',
  styleUrl: './body.component.css'
})
export class BodyComponent {
  isPasswordInputVisible = false;
  isTimerInputVisible = false;

  changePasswordInputVisibility() {
    this.isPasswordInputVisible = !this.isPasswordInputVisible;
  }

  changeTimerInputVisibility() {
    this.isTimerInputVisible = !this.isTimerInputVisible;
  }
}
