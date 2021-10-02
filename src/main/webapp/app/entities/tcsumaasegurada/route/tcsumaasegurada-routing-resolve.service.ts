import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITCSUMAASEGURADA, TCSUMAASEGURADA } from '../tcsumaasegurada.model';
import { TCSUMAASEGURADAService } from '../service/tcsumaasegurada.service';

@Injectable({ providedIn: 'root' })
export class TCSUMAASEGURADARoutingResolveService implements Resolve<ITCSUMAASEGURADA> {
  constructor(protected service: TCSUMAASEGURADAService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITCSUMAASEGURADA> | Observable<never> {
    const id = route.params['idSumaAsegurada'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tCSUMAASEGURADA: HttpResponse<TCSUMAASEGURADA>) => {
          if (tCSUMAASEGURADA.body) {
            return of(tCSUMAASEGURADA.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TCSUMAASEGURADA());
  }
}
