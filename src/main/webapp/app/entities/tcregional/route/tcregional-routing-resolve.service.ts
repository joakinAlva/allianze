import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITCREGIONAL, TCREGIONAL } from '../tcregional.model';
import { TCREGIONALService } from '../service/tcregional.service';

@Injectable({ providedIn: 'root' })
export class TCREGIONALRoutingResolveService implements Resolve<ITCREGIONAL> {
  constructor(protected service: TCREGIONALService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITCREGIONAL> | Observable<never> {
    const id = route.params['idRegional'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tCREGIONAL: HttpResponse<TCREGIONAL>) => {
          if (tCREGIONAL.body) {
            return of(tCREGIONAL.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TCREGIONAL());
  }
}
