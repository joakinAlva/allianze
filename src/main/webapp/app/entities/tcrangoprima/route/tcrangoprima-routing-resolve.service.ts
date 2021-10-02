import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITCRANGOPRIMA, TCRANGOPRIMA } from '../tcrangoprima.model';
import { TCRANGOPRIMAService } from '../service/tcrangoprima.service';

@Injectable({ providedIn: 'root' })
export class TCRANGOPRIMARoutingResolveService implements Resolve<ITCRANGOPRIMA> {
  constructor(protected service: TCRANGOPRIMAService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITCRANGOPRIMA> | Observable<never> {
    const id = route.params['idRangoPrima'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tCRANGOPRIMA: HttpResponse<TCRANGOPRIMA>) => {
          if (tCRANGOPRIMA.body) {
            return of(tCRANGOPRIMA.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TCRANGOPRIMA());
  }
}
