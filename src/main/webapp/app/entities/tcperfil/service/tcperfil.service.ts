import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITCPERFIL, getTCPERFILIdentifier } from '../tcperfil.model';

export type EntityResponseType = HttpResponse<ITCPERFIL>;
export type EntityArrayResponseType = HttpResponse<ITCPERFIL[]>;

@Injectable({ providedIn: 'root' })
export class TCPERFILService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/tcperfils');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(tCPERFIL: ITCPERFIL): Observable<EntityResponseType> {
    return this.http.post<ITCPERFIL>(this.resourceUrl, tCPERFIL, { observe: 'response' });
  }

  update(tCPERFIL: ITCPERFIL): Observable<EntityResponseType> {
    return this.http.put<ITCPERFIL>(`${this.resourceUrl}/${getTCPERFILIdentifier(tCPERFIL) as number}`, tCPERFIL, { observe: 'response' });
  }

  partialUpdate(tCPERFIL: ITCPERFIL): Observable<EntityResponseType> {
    return this.http.patch<ITCPERFIL>(`${this.resourceUrl}/${getTCPERFILIdentifier(tCPERFIL) as number}`, tCPERFIL, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITCPERFIL>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITCPERFIL[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addTCPERFILToCollectionIfMissing(tCPERFILCollection: ITCPERFIL[], ...tCPERFILSToCheck: (ITCPERFIL | null | undefined)[]): ITCPERFIL[] {
    const tCPERFILS: ITCPERFIL[] = tCPERFILSToCheck.filter(isPresent);
    if (tCPERFILS.length > 0) {
      const tCPERFILCollectionIdentifiers = tCPERFILCollection.map(tCPERFILItem => getTCPERFILIdentifier(tCPERFILItem)!);
      const tCPERFILSToAdd = tCPERFILS.filter(tCPERFILItem => {
        const tCPERFILIdentifier = getTCPERFILIdentifier(tCPERFILItem);
        if (tCPERFILIdentifier == null || tCPERFILCollectionIdentifiers.includes(tCPERFILIdentifier)) {
          return false;
        }
        tCPERFILCollectionIdentifiers.push(tCPERFILIdentifier);
        return true;
      });
      return [...tCPERFILSToAdd, ...tCPERFILCollection];
    }
    return tCPERFILCollection;
  }
}
