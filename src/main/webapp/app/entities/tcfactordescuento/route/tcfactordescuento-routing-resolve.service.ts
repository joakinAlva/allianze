import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITCFACTORDESCUENTO, TCFACTORDESCUENTO } from '../tcfactordescuento.model';
import { TCFACTORDESCUENTOService } from '../service/tcfactordescuento.service';

@Injectable({ providedIn: 'root' })
export class TCFACTORDESCUENTORoutingResolveService implements Resolve<ITCFACTORDESCUENTO> {
  constructor(protected service: TCFACTORDESCUENTOService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITCFACTORDESCUENTO> | Observable<never> {
    const id = route.params['idFactorDescuento'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tCFACTORDESCUENTO: HttpResponse<TCFACTORDESCUENTO>) => {
          if (tCFACTORDESCUENTO.body) {
            return of(tCFACTORDESCUENTO.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TCFACTORDESCUENTO());
  }
}
