import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITCCUOTAVALOR, TCCUOTAVALOR } from '../tccuotavalor.model';
import { TCCUOTAVALORService } from '../service/tccuotavalor.service';

@Injectable({ providedIn: 'root' })
export class TCCUOTAVALORRoutingResolveService implements Resolve<ITCCUOTAVALOR> {
  constructor(protected service: TCCUOTAVALORService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITCCUOTAVALOR> | Observable<never> {
    const id = route.params['idCuotaValor'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tCCUOTAVALOR: HttpResponse<TCCUOTAVALOR>) => {
          if (tCCUOTAVALOR.body) {
            return of(tCCUOTAVALOR.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TCCUOTAVALOR());
  }
}
