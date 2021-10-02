import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITCPRIMATARIFA, TCPRIMATARIFA } from '../tcprimatarifa.model';
import { TCPRIMATARIFAService } from '../service/tcprimatarifa.service';

@Injectable({ providedIn: 'root' })
export class TCPRIMATARIFARoutingResolveService implements Resolve<ITCPRIMATARIFA> {
  constructor(protected service: TCPRIMATARIFAService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITCPRIMATARIFA> | Observable<never> {
    const id = route.params['idPrimaTarifa'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tCPRIMATARIFA: HttpResponse<TCPRIMATARIFA>) => {
          if (tCPRIMATARIFA.body) {
            return of(tCPRIMATARIFA.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TCPRIMATARIFA());
  }
}
