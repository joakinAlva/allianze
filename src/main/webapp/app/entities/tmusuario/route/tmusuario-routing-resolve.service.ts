import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITMUSUARIO, TMUSUARIO } from '../tmusuario.model';
import { TMUSUARIOService } from '../service/tmusuario.service';

@Injectable({ providedIn: 'root' })
export class TMUSUARIORoutingResolveService implements Resolve<ITMUSUARIO> {
  constructor(protected service: TMUSUARIOService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITMUSUARIO> | Observable<never> {
    const id = route.params['idUsuario'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tMUSUARIO: HttpResponse<TMUSUARIO>) => {
          if (tMUSUARIO.body) {
            return of(tMUSUARIO.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TMUSUARIO());
  }
}
