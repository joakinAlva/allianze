import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITCOCUPACION, TCOCUPACION } from '../tcocupacion.model';
import { TCOCUPACIONService } from '../service/tcocupacion.service';

@Injectable({ providedIn: 'root' })
export class TCOCUPACIONRoutingResolveService implements Resolve<ITCOCUPACION> {
  constructor(protected service: TCOCUPACIONService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITCOCUPACION> | Observable<never> {
    const id = route.params['idOcupacion'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tCOCUPACION: HttpResponse<TCOCUPACION>) => {
          if (tCOCUPACION.body) {
            return of(tCOCUPACION.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TCOCUPACION());
  }
}
