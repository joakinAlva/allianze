import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITCDESCUENTOSUMAASEGURADA, TCDESCUENTOSUMAASEGURADA } from '../tcdescuentosumaasegurada.model';
import { TCDESCUENTOSUMAASEGURADAService } from '../service/tcdescuentosumaasegurada.service';

@Injectable({ providedIn: 'root' })
export class TCDESCUENTOSUMAASEGURADARoutingResolveService implements Resolve<ITCDESCUENTOSUMAASEGURADA> {
  constructor(protected service: TCDESCUENTOSUMAASEGURADAService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITCDESCUENTOSUMAASEGURADA> | Observable<never> {
    const id = route.params['idDescuentoSumaAsegurada'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tCDESCUENTOSUMAASEGURADA: HttpResponse<TCDESCUENTOSUMAASEGURADA>) => {
          if (tCDESCUENTOSUMAASEGURADA.body) {
            return of(tCDESCUENTOSUMAASEGURADA.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TCDESCUENTOSUMAASEGURADA());
  }
}
