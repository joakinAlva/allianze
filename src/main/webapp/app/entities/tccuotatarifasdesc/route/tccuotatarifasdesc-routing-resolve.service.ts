import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITCCUOTATARIFASDESC, TCCUOTATARIFASDESC } from '../tccuotatarifasdesc.model';
import { TCCUOTATARIFASDESCService } from '../service/tccuotatarifasdesc.service';

@Injectable({ providedIn: 'root' })
export class TCCUOTATARIFASDESCRoutingResolveService implements Resolve<ITCCUOTATARIFASDESC> {
  constructor(protected service: TCCUOTATARIFASDESCService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITCCUOTATARIFASDESC> | Observable<never> {
    const id = route.params['idCuotaTarifaSdesc'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tCCUOTATARIFASDESC: HttpResponse<TCCUOTATARIFASDESC>) => {
          if (tCCUOTATARIFASDESC.body) {
            return of(tCCUOTATARIFASDESC.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TCCUOTATARIFASDESC());
  }
}
