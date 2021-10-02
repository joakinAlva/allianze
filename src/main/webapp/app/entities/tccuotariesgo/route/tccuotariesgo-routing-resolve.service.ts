import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITCCUOTARIESGO, TCCUOTARIESGO } from '../tccuotariesgo.model';
import { TCCUOTARIESGOService } from '../service/tccuotariesgo.service';

@Injectable({ providedIn: 'root' })
export class TCCUOTARIESGORoutingResolveService implements Resolve<ITCCUOTARIESGO> {
  constructor(protected service: TCCUOTARIESGOService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITCCUOTARIESGO> | Observable<never> {
    const id = route.params['idCuotaRiesgo'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tCCUOTARIESGO: HttpResponse<TCCUOTARIESGO>) => {
          if (tCCUOTARIESGO.body) {
            return of(tCCUOTARIESGO.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TCCUOTARIESGO());
  }
}
