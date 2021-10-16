import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITCCOVIDTARIFAS, TCCOVIDTARIFAS } from '../tccovidtarifas.model';
import { TCCOVIDTARIFASService } from '../service/tccovidtarifas.service';

@Injectable({ providedIn: 'root' })
export class TCCOVIDTARIFASRoutingResolveService implements Resolve<ITCCOVIDTARIFAS> {
  constructor(protected service: TCCOVIDTARIFASService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITCCOVIDTARIFAS> | Observable<never> {
    const id = route.params['idCovidTarifas'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tCCOVIDTARIFAS: HttpResponse<TCCOVIDTARIFAS>) => {
          if (tCCOVIDTARIFAS.body) {
            return of(tCCOVIDTARIFAS.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TCCOVIDTARIFAS());
  }
}
