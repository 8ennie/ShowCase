import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {TranslateModule, TranslateService} from '@ngx-translate/core';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ShowcaseComponent } from './showcase/showcase.component';
import { ManageComponent } from './manage/manage.component';
import { MonitorComponent } from './manage/monitor/monitor.component';
import { PrimeNgModule } from './primeng.modules';
import { AuthComponent } from './auth/auth.component';
import { authInterceptorProviders } from './auth/auth.interceptor';
import { ManageShowcaseComponent } from './manage/manage-showcase/manage-showcase.component';

@NgModule({
  declarations: [
    AppComponent,
    AuthComponent,
    ShowcaseComponent,
    ManageComponent,
    MonitorComponent,
    ManageShowcaseComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    NgbModule,
    PrimeNgModule,
    HttpClientModule,
    TranslateModule.forRoot(),
    BrowserAnimationsModule,
  ],
  providers: [
    authInterceptorProviders,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { 
  constructor(
    public translate: TranslateService
  ) {
    translate.addLangs(['en', 'de']);
    translate.setDefaultLang('de');
  }
}

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http, '/assets/translations/', '.json');
}
