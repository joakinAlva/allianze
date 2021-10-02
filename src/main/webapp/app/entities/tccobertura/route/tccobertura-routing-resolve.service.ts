import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITCCOBERTURA, TCCOBERTURA } from '../tccobertura.model';
import { TCCOBERTURAService } from '../service/tccobertura.service';

@Injectable({ providedIn: 'root' })
export class TCCOBERTURARoutingResolveService implements Resolve<ITCCOBERTURA> {
  constructor(protected service: TCCOBERTURAService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITCCOBERTURA> | Observable<never> {
    const id = route.params['idCobertura'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tCCOBERTURA: HttpResponse<TCCOBERTURA>) => {
          if (tCCOBERTURA.body) {
            return of(tCCOBERTURA.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TCCOBERTURA());
  }
}
