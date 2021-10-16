import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITMASEGURADO, TMASEGURADO } from '../tmasegurado.model';
import { TMASEGURADOService } from '../service/tmasegurado.service';

@Injectable({ providedIn: 'root' })
export class TMASEGURADORoutingResolveService implements Resolve<ITMASEGURADO> {
  constructor(protected service: TMASEGURADOService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITMASEGURADO> | Observable<never> {
    const id = route.params['idAsegurados'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tMASEGURADO: HttpResponse<TMASEGURADO>) => {
          if (tMASEGURADO.body) {
            return of(tMASEGURADO.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TMASEGURADO());
  }
}
