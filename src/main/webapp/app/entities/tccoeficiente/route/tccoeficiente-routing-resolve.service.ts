import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITCCOEFICIENTE, TCCOEFICIENTE } from '../tccoeficiente.model';
import { TCCOEFICIENTEService } from '../service/tccoeficiente.service';

@Injectable({ providedIn: 'root' })
export class TCCOEFICIENTERoutingResolveService implements Resolve<ITCCOEFICIENTE> {
  constructor(protected service: TCCOEFICIENTEService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITCCOEFICIENTE> | Observable<never> {
    const id = route.params['idCoeficiente'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tCCOEFICIENTE: HttpResponse<TCCOEFICIENTE>) => {
          if (tCCOEFICIENTE.body) {
            return of(tCCOEFICIENTE.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TCCOEFICIENTE());
  }
}
