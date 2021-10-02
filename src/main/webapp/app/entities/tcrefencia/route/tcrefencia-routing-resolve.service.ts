import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITCREFENCIA, TCREFENCIA } from '../tcrefencia.model';
import { TCREFENCIAService } from '../service/tcrefencia.service';

@Injectable({ providedIn: 'root' })
export class TCREFENCIARoutingResolveService implements Resolve<ITCREFENCIA> {
  constructor(protected service: TCREFENCIAService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITCREFENCIA> | Observable<never> {
    const id = route.params['idReferencia'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tCREFENCIA: HttpResponse<TCREFENCIA>) => {
          if (tCREFENCIA.body) {
            return of(tCREFENCIA.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TCREFENCIA());
  }
}
