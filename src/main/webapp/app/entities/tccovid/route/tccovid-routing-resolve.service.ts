import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITCCOVID, TCCOVID } from '../tccovid.model';
import { TCCOVIDService } from '../service/tccovid.service';

@Injectable({ providedIn: 'root' })
export class TCCOVIDRoutingResolveService implements Resolve<ITCCOVID> {
  constructor(protected service: TCCOVIDService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITCCOVID> | Observable<never> {
    const id = route.params['idCovid'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tCCOVID: HttpResponse<TCCOVID>) => {
          if (tCCOVID.body) {
            return of(tCCOVID.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TCCOVID());
  }
}
