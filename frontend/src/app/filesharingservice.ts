import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Subject } from "rxjs";

@Injectable({
    providedIn: 'root',
})
export class FileSharingService {
    private receivedFile: any;

    uploadInfo = new Subject<boolean>;
    message = this.uploadInfo.asObservable();

    downloadLink = new Subject<string>;
    link = this.downloadLink.asObservable();

    isFileDownloaded = new Subject<boolean>;
    downloadInfo = this.isFileDownloaded.asObservable();

    constructor(private httpClient: HttpClient) {
    }

    sendFile(file: any) {
        this.httpClient.post("http://localhost:8080/upload", file, { responseType: 'text' }).subscribe(
            {
                next: (downloadLink) => {
                    console.log("Message: ", downloadLink);
                    this.downloadLink.next(downloadLink);
                    // this.downloadLink = downloadLink;
                    this.uploadInfo.next(true);
                },
                error: (error) => {
                    console.log("Error: ", error);
                    this.uploadInfo.next(false);
                }
            }
        );
    }

    receiveFile(downloadDetails: any) {
        this.httpClient.post("http://localhost:8080/download", downloadDetails, {responseType: 'blob'}).subscribe({
            next: (blob: any) => {
                this.isFileDownloaded.next(true);
                const link = document.createElement('a');
                link.href = window.URL.createObjectURL(blob);
                link.download = 'file.pdf';

                link.click();

                window.URL.revokeObjectURL(link.href);
            },
            error: (err) => {
                this.isFileDownloaded.next(false);
                console.log("File download failed", err);
            }
        })
    }
}