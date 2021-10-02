import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITCPRIMANETASDESC, TCPRIMANETASDESC } from '../tcprimanetasdesc.model';
import { TCPRIMANETASDESCService } from '../service/tcprimanetasdesc.service';

@Injectable({ providedIn: 'root' })
export class TCPRIMANETASDESCRoutingResolveService implements Resolve<ITCPRIMANETASDESC> {
  constructor(protected service: TCPRIMANETASDESCService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITCPRIMANETASDESC> | Observable<never> {
    const id = route.params['idPrimaNetaSdesc'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tCPRIMANETASDESC: HttpResponse<TCPRIMANETASDESC>) => {
          if (tCPRIMANETASDESC.body) {
            return of(tCPRIMANETASDESC.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TCPRIMANETASDESC());
  }
}
