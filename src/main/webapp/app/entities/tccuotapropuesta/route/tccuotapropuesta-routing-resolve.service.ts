import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITCCUOTAPROPUESTA, TCCUOTAPROPUESTA } from '../tccuotapropuesta.model';
import { TCCUOTAPROPUESTAService } from '../service/tccuotapropuesta.service';

@Injectable({ providedIn: 'root' })
export class TCCUOTAPROPUESTARoutingResolveService implements Resolve<ITCCUOTAPROPUESTA> {
  constructor(protected service: TCCUOTAPROPUESTAService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITCCUOTAPROPUESTA> | Observable<never> {
    const id = route.params['idCuotaPropuesta'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tCCUOTAPROPUESTA: HttpResponse<TCCUOTAPROPUESTA>) => {
          if (tCCUOTAPROPUESTA.body) {
            return of(tCCUOTAPROPUESTA.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TCCUOTAPROPUESTA());
  }
}
