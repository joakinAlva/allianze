import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITCEDADRECARGO, TCEDADRECARGO } from '../tcedadrecargo.model';
import { TCEDADRECARGOService } from '../service/tcedadrecargo.service';

@Injectable({ providedIn: 'root' })
export class TCEDADRECARGORoutingResolveService implements Resolve<ITCEDADRECARGO> {
  constructor(protected service: TCEDADRECARGOService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITCEDADRECARGO> | Observable<never> {
    const id = route.params['idEdadRecargo'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tCEDADRECARGO: HttpResponse<TCEDADRECARGO>) => {
          if (tCEDADRECARGO.body) {
            return of(tCEDADRECARGO.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TCEDADRECARGO());
  }
}
