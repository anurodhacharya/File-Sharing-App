import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { FileSharingService } from '../filesharingservice';

@Component({
  selector: 'app-download',
  templateUrl: './download.component.html',
  styleUrl: './download.component.css'
})
export class DownloadComponent implements OnInit {
  signupForm: FormGroup;
  isFileDownloaded: boolean = null;

  constructor(private fileSharingService: FileSharingService) {

  }

  ngOnInit(): void {
      this.signupForm = new FormGroup({
        downloadLink: new FormControl(null),
        password: new FormControl(null)
      })

      this.fileSharingService.downloadInfo.subscribe((isFileDownloaded) => {
        this.isFileDownloaded = isFileDownloaded;
      })
  }

  downloadFile() {
    console.log(this.signupForm);
    this.fileSharingService.receiveFile(this.signupForm.value);
  }
}
