import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ManageComponent } from './manage/manage.component';
import { ShowcaseComponent } from './showcase/showcase.component';
import { MonitorComponent } from './manage/monitor/monitor.component';
import { AuthComponent } from './auth/auth.component';
import { AuthGuard } from './auth/auth.guard';
import { Role } from './auth/role.model';
import { ManageShowcaseComponent } from './manage/manage-showcase/manage-showcase.component';

const routes: Routes = [
    { path: 'manage', component: ManageComponent, children: [
        { path: 'auth/signin', component: AuthComponent },
        { path: 'monitors', component: MonitorComponent , canActivate: [AuthGuard], data: { roles: [Role.ROLE_ADMIN] } },
        { path: 'showcase', component: ManageShowcaseComponent, canActivate: [AuthGuard], data: { roles: [Role.ROLE_ADMIN] } },
    ]},

    { path: 'showcase', component: ShowcaseComponent },
];


@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
