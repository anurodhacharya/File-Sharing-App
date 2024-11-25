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
  isFormValid: boolean = false;
  isPasswordChosen: boolean = false;
  isTimerChosen: boolean = false;
  fileUploadType: boolean = true;
  downloadLink: string = null;

  constructor(private fileSharingService: FileSharingService) {
  }

  ngOnInit(): void {
      this.signupForm = new FormGroup({
        password: new FormControl(null, Validators.minLength(5)),
        hour: new FormControl(null),
        min: new FormControl(null),
        sec: new FormControl(null)
      });

      this.fileSharingService.message.subscribe((message) => {
        this.isSubmitSuccess = message;
        console.log("Message: ", message)
      })

      this.fileSharingService.link.subscribe((downloadLink) => {
        this.downloadLink = downloadLink;
      })
  }

  changePasswordInputVisibility() {
    this.isPasswordInputVisible = !this.isPasswordInputVisible;
    this.isFormValid = false;
  }

  changeTimerInputVisibility() {
    this.isTimerInputVisible = !this.isTimerInputVisible;
  }

  onFileSelect(event: Event) {
    const input = event.target as HTMLInputElement;
    if(input.files && input.files.length> 0) {
      this.selectedFile = input.files[0];
    }
    this.isFormValid = true;
  }

  submitForm() {
    if(this.signupForm.valid && this.selectedFile) {
      const formData = new FormData();
      formData.append('file', this.selectedFile);
      formData.append('password', this.signupForm.get('password')?.value);
      
      const totalSeconds: any = this.signupForm.get('hour')?.value * 60 * 60 + this.signupForm.get('min')?.value * 60 + this.signupForm.get('sec')?.value;

      console.log("Total Seconds: ", totalSeconds);

      formData.append('timer', totalSeconds)
      // formData.append('timer', this.signupForm.get('timer')?.value);
      console.log(formData);
      this.fileSharingService.sendFile(formData)
      this.signupForm.reset();
      this.selectedFile = null;
    }
  }

  optionalPasswordChecker() {
    this.signupForm.valid == true ? this.isFormValid = true : this.isFormValid = false;
  }

  changeUploadType() {
    this.fileUploadType = !this.fileUploadType;
  }
}
