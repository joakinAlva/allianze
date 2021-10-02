import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITCHIPOTESIS, TCHIPOTESIS } from '../tchipotesis.model';
import { TCHIPOTESISService } from '../service/tchipotesis.service';

@Injectable({ providedIn: 'root' })
export class TCHIPOTESISRoutingResolveService implements Resolve<ITCHIPOTESIS> {
  constructor(protected service: TCHIPOTESISService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITCHIPOTESIS> | Observable<never> {
    const id = route.params['idHipotesis'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tCHIPOTESIS: HttpResponse<TCHIPOTESIS>) => {
          if (tCHIPOTESIS.body) {
            return of(tCHIPOTESIS.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TCHIPOTESIS());
  }
}
