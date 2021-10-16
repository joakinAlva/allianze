import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITMCOTIZACION, TMCOTIZACION } from '../tmcotizacion.model';
import { TMCOTIZACIONService } from '../service/tmcotizacion.service';

@Injectable({ providedIn: 'root' })
export class TMCOTIZACIONRoutingResolveService implements Resolve<ITMCOTIZACION> {
  constructor(protected service: TMCOTIZACIONService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITMCOTIZACION> | Observable<never> {
    const id = route.params['idCotizacion'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tMCOTIZACION: HttpResponse<TMCOTIZACION>) => {
          if (tMCOTIZACION.body) {
            return of(tMCOTIZACION.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TMCOTIZACION());
  }
}
