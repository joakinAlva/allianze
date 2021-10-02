import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITCDESCUENTOTIPORIESGO, TCDESCUENTOTIPORIESGO } from '../tcdescuentotiporiesgo.model';
import { TCDESCUENTOTIPORIESGOService } from '../service/tcdescuentotiporiesgo.service';

@Injectable({ providedIn: 'root' })
export class TCDESCUENTOTIPORIESGORoutingResolveService implements Resolve<ITCDESCUENTOTIPORIESGO> {
  constructor(protected service: TCDESCUENTOTIPORIESGOService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITCDESCUENTOTIPORIESGO> | Observable<never> {
    const id = route.params['idDescuentoTipoRiesgo'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tCDESCUENTOTIPORIESGO: HttpResponse<TCDESCUENTOTIPORIESGO>) => {
          if (tCDESCUENTOTIPORIESGO.body) {
            return of(tCDESCUENTOTIPORIESGO.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TCDESCUENTOTIPORIESGO());
  }
}
