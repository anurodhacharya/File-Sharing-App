import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Subject } from "rxjs";

@Injectable({
    providedIn: 'root',
})
export class FileSharingService {
    uploadInfo = new Subject<boolean>;

    message = this.uploadInfo.asObservable();

    constructor(private httpClient: HttpClient) {
    }

    sendFile(file: any) {
        this.httpClient.post("http://localhost:8080/upload", file, { responseType: 'text' }).subscribe(
            {
                next: (downloadLink) => {
                    console.log("Message: ", downloadLink);
                    this.uploadInfo.next(true);
                },
                error: (error) => {
                    console.log("Error: ", error);
                    this.uploadInfo.next(false);
                }
            }
        );
    }
}