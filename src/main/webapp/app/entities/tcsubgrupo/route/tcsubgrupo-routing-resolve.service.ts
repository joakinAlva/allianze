import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITCSUBGRUPO, TCSUBGRUPO } from '../tcsubgrupo.model';
import { TCSUBGRUPOService } from '../service/tcsubgrupo.service';

@Injectable({ providedIn: 'root' })
export class TCSUBGRUPORoutingResolveService implements Resolve<ITCSUBGRUPO> {
  constructor(protected service: TCSUBGRUPOService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITCSUBGRUPO> | Observable<never> {
    const id = route.params['idSubGrupo'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tCSUBGRUPO: HttpResponse<TCSUBGRUPO>) => {
          if (tCSUBGRUPO.body) {
            return of(tCSUBGRUPO.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TCSUBGRUPO());
  }
}
