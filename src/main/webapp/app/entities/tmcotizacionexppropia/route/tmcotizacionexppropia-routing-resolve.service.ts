import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITMCOTIZACIONEXPPROPIA, TMCOTIZACIONEXPPROPIA } from '../tmcotizacionexppropia.model';
import { TMCOTIZACIONEXPPROPIAService } from '../service/tmcotizacionexppropia.service';

@Injectable({ providedIn: 'root' })
export class TMCOTIZACIONEXPPROPIARoutingResolveService implements Resolve<ITMCOTIZACIONEXPPROPIA> {
  constructor(protected service: TMCOTIZACIONEXPPROPIAService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITMCOTIZACIONEXPPROPIA> | Observable<never> {
    const id = route.params['idCotizacionExpPropia'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tMCOTIZACIONEXPPROPIA: HttpResponse<TMCOTIZACIONEXPPROPIA>) => {
          if (tMCOTIZACIONEXPPROPIA.body) {
            return of(tMCOTIZACIONEXPPROPIA.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TMCOTIZACIONEXPPROPIA());
  }
}
