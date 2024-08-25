import { AfterViewInit, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Validators } from '@angular/forms';
import { FormControl } from '@angular/forms';
import { FormGroup } from '@angular/forms';
import { FileSharingService } from '../filesharingservice';

@Component({
  selector: 'app-body',
  templateUrl: './body.component.html',
  styleUrl: './body.component.css'
})
export class BodyComponent implements OnInit {
  isPasswordInputVisible = false;
  isTimerInputVisible = false;
  signupForm: FormGroup;
  selectedFile: File | null = null;
  isSubmitSuccess: boolean = null;

  
  constructor(private fileSharingService: FileSharingService) {

  }

  ngOnInit(): void {
      this.signupForm = new FormGroup({
        password: new FormControl(null, Validators.required),
        timer: new FormControl(null, Validators.required)
      });

      this.fileSharingService.message.subscribe((message) => {
        this.isSubmitSuccess = message;
        console.log("Message: ", message)
      })
  }

  changePasswordInputVisibility() {
    this.isPasswordInputVisible = !this.isPasswordInputVisible;
  }

  changeTimerInputVisibility() {
    this.isTimerInputVisible = !this.isTimerInputVisible;
  }

  
  onFileSelect(event: Event) {
    const input = event.target as HTMLInputElement;
    if(input.files && input.files.length> 0) {
      this.selectedFile = input.files[0];
    }
    // console.log(this.selectedFile);
  }

  submitForm() {
    if(this.signupForm.valid && this.selectedFile) {
      const formData = new FormData();
      formData.append('file', this.selectedFile);
      formData.append('password', this.signupForm.get('password')?.value);
      formData.append('timer', this.signupForm.get('timer')?.value);
      // console.log(formData);
      this.fileSharingService.sendFile(formData)
      this.signupForm.reset();
      this.selectedFile = null;
    }
  }

  isUploadSuccess(isSubmitSuccess: boolean) {
    this.isSubmitSuccess = isSubmitSuccess;
  }
}
