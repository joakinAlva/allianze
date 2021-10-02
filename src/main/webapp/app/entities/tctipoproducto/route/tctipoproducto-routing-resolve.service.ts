import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITCTIPOPRODUCTO, TCTIPOPRODUCTO } from '../tctipoproducto.model';
import { TCTIPOPRODUCTOService } from '../service/tctipoproducto.service';

@Injectable({ providedIn: 'root' })
export class TCTIPOPRODUCTORoutingResolveService implements Resolve<ITCTIPOPRODUCTO> {
  constructor(protected service: TCTIPOPRODUCTOService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITCTIPOPRODUCTO> | Observable<never> {
    const id = route.params['idTipoProducto'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tCTIPOPRODUCTO: HttpResponse<TCTIPOPRODUCTO>) => {
          if (tCTIPOPRODUCTO.body) {
            return of(tCTIPOPRODUCTO.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TCTIPOPRODUCTO());
  }
}
