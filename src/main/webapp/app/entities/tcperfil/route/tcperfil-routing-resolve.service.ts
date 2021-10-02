import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITCPERFIL, TCPERFIL } from '../tcperfil.model';
import { TCPERFILService } from '../service/tcperfil.service';

@Injectable({ providedIn: 'root' })
export class TCPERFILRoutingResolveService implements Resolve<ITCPERFIL> {
  constructor(protected service: TCPERFILService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITCPERFIL> | Observable<never> {
    const id = route.params['idPerfil'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tCPERFIL: HttpResponse<TCPERFIL>) => {
          if (tCPERFIL.body) {
            return of(tCPERFIL.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TCPERFIL());
  }
}
