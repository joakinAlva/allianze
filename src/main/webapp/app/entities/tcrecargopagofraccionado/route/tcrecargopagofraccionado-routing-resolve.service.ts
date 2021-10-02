import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITCRECARGOPAGOFRACCIONADO, TCRECARGOPAGOFRACCIONADO } from '../tcrecargopagofraccionado.model';
import { TCRECARGOPAGOFRACCIONADOService } from '../service/tcrecargopagofraccionado.service';

@Injectable({ providedIn: 'root' })
export class TCRECARGOPAGOFRACCIONADORoutingResolveService implements Resolve<ITCRECARGOPAGOFRACCIONADO> {
  constructor(protected service: TCRECARGOPAGOFRACCIONADOService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITCRECARGOPAGOFRACCIONADO> | Observable<never> {
    const id = route.params['idRecargoPagoFraccionado'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tCRECARGOPAGOFRACCIONADO: HttpResponse<TCRECARGOPAGOFRACCIONADO>) => {
          if (tCRECARGOPAGOFRACCIONADO.body) {
            return of(tCRECARGOPAGOFRACCIONADO.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TCRECARGOPAGOFRACCIONADO());
  }
}
