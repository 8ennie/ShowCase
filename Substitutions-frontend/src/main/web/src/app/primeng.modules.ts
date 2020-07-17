import { NgModule } from '@angular/core';

import { DialogModule } from 'primeng/dialog';
import { TableModule } from 'primeng/table';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { ToggleButtonModule } from 'primeng/togglebutton';

@NgModule({
    imports: [
        TableModule,
        DialogModule,
        ToastModule,
        ButtonModule,
        ToggleButtonModule,
    ],
    exports: [
        TableModule,
        DialogModule,
        ToastModule,
        ButtonModule,
        ToggleButtonModule,
    ],
    providers: [MessageService]
})
export class PrimeNgModule { }