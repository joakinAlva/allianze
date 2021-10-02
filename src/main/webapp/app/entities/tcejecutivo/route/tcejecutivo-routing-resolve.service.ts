import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITCEJECUTIVO, TCEJECUTIVO } from '../tcejecutivo.model';
import { TCEJECUTIVOService } from '../service/tcejecutivo.service';

@Injectable({ providedIn: 'root' })
export class TCEJECUTIVORoutingResolveService implements Resolve<ITCEJECUTIVO> {
  constructor(protected service: TCEJECUTIVOService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITCEJECUTIVO> | Observable<never> {
    const id = route.params['idEjecutivo'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tCEJECUTIVO: HttpResponse<TCEJECUTIVO>) => {
          if (tCEJECUTIVO.body) {
            return of(tCEJECUTIVO.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TCEJECUTIVO());
  }
}
