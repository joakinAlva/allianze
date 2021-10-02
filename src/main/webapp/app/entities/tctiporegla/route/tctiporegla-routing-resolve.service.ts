import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITCTIPOREGLA, TCTIPOREGLA } from '../tctiporegla.model';
import { TCTIPOREGLAService } from '../service/tctiporegla.service';

@Injectable({ providedIn: 'root' })
export class TCTIPOREGLARoutingResolveService implements Resolve<ITCTIPOREGLA> {
  constructor(protected service: TCTIPOREGLAService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITCTIPOREGLA> | Observable<never> {
    const id = route.params['idTipoRegla'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tCTIPOREGLA: HttpResponse<TCTIPOREGLA>) => {
          if (tCTIPOREGLA.body) {
            return of(tCTIPOREGLA.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TCTIPOREGLA());
  }
}
