import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITCCONCEPTO, TCCONCEPTO } from '../tcconcepto.model';
import { TCCONCEPTOService } from '../service/tcconcepto.service';

@Injectable({ providedIn: 'root' })
export class TCCONCEPTORoutingResolveService implements Resolve<ITCCONCEPTO> {
  constructor(protected service: TCCONCEPTOService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITCCONCEPTO> | Observable<never> {
    const id = route.params['idConcepto'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tCCONCEPTO: HttpResponse<TCCONCEPTO>) => {
          if (tCCONCEPTO.body) {
            return of(tCCONCEPTO.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TCCONCEPTO());
  }
}
