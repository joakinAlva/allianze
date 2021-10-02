import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITCTIPO, TCTIPO } from '../tctipo.model';
import { TCTIPOService } from '../service/tctipo.service';

@Injectable({ providedIn: 'root' })
export class TCTIPORoutingResolveService implements Resolve<ITCTIPO> {
  constructor(protected service: TCTIPOService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITCTIPO> | Observable<never> {
    const id = route.params['idTipo'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tCTIPO: HttpResponse<TCTIPO>) => {
          if (tCTIPO.body) {
            return of(tCTIPO.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TCTIPO());
  }
}
