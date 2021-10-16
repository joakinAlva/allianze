import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITCESTATUSCOTIZACION, TCESTATUSCOTIZACION } from '../tcestatuscotizacion.model';
import { TCESTATUSCOTIZACIONService } from '../service/tcestatuscotizacion.service';

@Injectable({ providedIn: 'root' })
export class TCESTATUSCOTIZACIONRoutingResolveService implements Resolve<ITCESTATUSCOTIZACION> {
  constructor(protected service: TCESTATUSCOTIZACIONService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITCESTATUSCOTIZACION> | Observable<never> {
    const id = route.params['idEstatusCotizacion'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tCESTATUSCOTIZACION: HttpResponse<TCESTATUSCOTIZACION>) => {
          if (tCESTATUSCOTIZACION.body) {
            return of(tCESTATUSCOTIZACION.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TCESTATUSCOTIZACION());
  }
}
