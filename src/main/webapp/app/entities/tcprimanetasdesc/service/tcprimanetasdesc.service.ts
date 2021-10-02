import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITCPRIMANETASDESC, getTCPRIMANETASDESCIdentifier } from '../tcprimanetasdesc.model';

export type EntityResponseType = HttpResponse<ITCPRIMANETASDESC>;
export type EntityArrayResponseType = HttpResponse<ITCPRIMANETASDESC[]>;

@Injectable({ providedIn: 'root' })
export class TCPRIMANETASDESCService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/tcprimanetasdescs');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(tCPRIMANETASDESC: ITCPRIMANETASDESC): Observable<EntityResponseType> {
    return this.http.post<ITCPRIMANETASDESC>(this.resourceUrl, tCPRIMANETASDESC, { observe: 'response' });
  }

  update(tCPRIMANETASDESC: ITCPRIMANETASDESC): Observable<EntityResponseType> {
    return this.http.put<ITCPRIMANETASDESC>(
      `${this.resourceUrl}/${getTCPRIMANETASDESCIdentifier(tCPRIMANETASDESC) as number}`,
      tCPRIMANETASDESC,
      { observe: 'response' }
    );
  }

  partialUpdate(tCPRIMANETASDESC: ITCPRIMANETASDESC): Observable<EntityResponseType> {
    return this.http.patch<ITCPRIMANETASDESC>(
      `${this.resourceUrl}/${getTCPRIMANETASDESCIdentifier(tCPRIMANETASDESC) as number}`,
      tCPRIMANETASDESC,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITCPRIMANETASDESC>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITCPRIMANETASDESC[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addTCPRIMANETASDESCToCollectionIfMissing(
    tCPRIMANETASDESCCollection: ITCPRIMANETASDESC[],
    ...tCPRIMANETASDESCSToCheck: (ITCPRIMANETASDESC | null | undefined)[]
  ): ITCPRIMANETASDESC[] {
    const tCPRIMANETASDESCS: ITCPRIMANETASDESC[] = tCPRIMANETASDESCSToCheck.filter(isPresent);
    if (tCPRIMANETASDESCS.length > 0) {
      const tCPRIMANETASDESCCollectionIdentifiers = tCPRIMANETASDESCCollection.map(
        tCPRIMANETASDESCItem => getTCPRIMANETASDESCIdentifier(tCPRIMANETASDESCItem)!
      );
      const tCPRIMANETASDESCSToAdd = tCPRIMANETASDESCS.filter(tCPRIMANETASDESCItem => {
        const tCPRIMANETASDESCIdentifier = getTCPRIMANETASDESCIdentifier(tCPRIMANETASDESCItem);
        if (tCPRIMANETASDESCIdentifier == null || tCPRIMANETASDESCCollectionIdentifiers.includes(tCPRIMANETASDESCIdentifier)) {
          return false;
        }
        tCPRIMANETASDESCCollectionIdentifiers.push(tCPRIMANETASDESCIdentifier);
        return true;
      });
      return [...tCPRIMANETASDESCSToAdd, ...tCPRIMANETASDESCCollection];
    }
    return tCPRIMANETASDESCCollection;
  }
}
