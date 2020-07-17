import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';
import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {


  constructor(
    public authService: AuthService,
    public router: Router
  ) { }

  canActivate(route: ActivatedRouteSnapshot,
              state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    const user = this.authService.getUser();

    if (user) {
      if(route.data.roles && user.roles.some(r=> route.data.roles.includes(r))){
        return true;
      } else {
        this.router.navigate(['/test']);
        return false;
      }
     
    } else {
      this.router.navigate(['manage/auth/signin'], {
        queryParams: {
          return: state.url
        }
      });
      return false;
    }

  }

}
