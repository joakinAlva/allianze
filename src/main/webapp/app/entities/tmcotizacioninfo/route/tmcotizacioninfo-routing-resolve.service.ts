import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITMCOTIZACIONINFO, TMCOTIZACIONINFO } from '../tmcotizacioninfo.model';
import { TMCOTIZACIONINFOService } from '../service/tmcotizacioninfo.service';

@Injectable({ providedIn: 'root' })
export class TMCOTIZACIONINFORoutingResolveService implements Resolve<ITMCOTIZACIONINFO> {
  constructor(protected service: TMCOTIZACIONINFOService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITMCOTIZACIONINFO> | Observable<never> {
    const id = route.params['idCotizacionInfo'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tMCOTIZACIONINFO: HttpResponse<TMCOTIZACIONINFO>) => {
          if (tMCOTIZACIONINFO.body) {
            return of(tMCOTIZACIONINFO.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TMCOTIZACIONINFO());
  }
}
