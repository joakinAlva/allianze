import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITCFACTORSAMI, TCFACTORSAMI } from '../tcfactorsami.model';
import { TCFACTORSAMIService } from '../service/tcfactorsami.service';

@Injectable({ providedIn: 'root' })
export class TCFACTORSAMIRoutingResolveService implements Resolve<ITCFACTORSAMI> {
  constructor(protected service: TCFACTORSAMIService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITCFACTORSAMI> | Observable<never> {
    const id = route.params['idFactorSami'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tCFACTORSAMI: HttpResponse<TCFACTORSAMI>) => {
          if (tCFACTORSAMI.body) {
            return of(tCFACTORSAMI.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TCFACTORSAMI());
  }
}
