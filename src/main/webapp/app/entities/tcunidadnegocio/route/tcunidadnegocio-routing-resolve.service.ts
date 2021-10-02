import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITCUNIDADNEGOCIO, TCUNIDADNEGOCIO } from '../tcunidadnegocio.model';
import { TCUNIDADNEGOCIOService } from '../service/tcunidadnegocio.service';

@Injectable({ providedIn: 'root' })
export class TCUNIDADNEGOCIORoutingResolveService implements Resolve<ITCUNIDADNEGOCIO> {
  constructor(protected service: TCUNIDADNEGOCIOService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITCUNIDADNEGOCIO> | Observable<never> {
    const id = route.params['idUnidadNegocio'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tCUNIDADNEGOCIO: HttpResponse<TCUNIDADNEGOCIO>) => {
          if (tCUNIDADNEGOCIO.body) {
            return of(tCUNIDADNEGOCIO.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TCUNIDADNEGOCIO());
  }
}
