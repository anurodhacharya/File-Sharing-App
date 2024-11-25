import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomepageComponent } from './homepage/homepage.component';
import { BodyComponent } from './body/body.component';
import { HeaderComponent } from './header/header.component';
import { DownloadComponent } from './download/download.component';
const routes: Routes = [
  { path: '', component: HomepageComponent },
  { path: 'upload', component: BodyComponent },
  { path: 'download', component: DownloadComponent},
  { path: '*', redirectTo: '', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
