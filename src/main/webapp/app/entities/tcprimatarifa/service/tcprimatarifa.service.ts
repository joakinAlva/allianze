import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITCPRIMATARIFA, getTCPRIMATARIFAIdentifier } from '../tcprimatarifa.model';

export type EntityResponseType = HttpResponse<ITCPRIMATARIFA>;
export type EntityArrayResponseType = HttpResponse<ITCPRIMATARIFA[]>;

@Injectable({ providedIn: 'root' })
export class TCPRIMATARIFAService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/tcprimatarifas');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(tCPRIMATARIFA: ITCPRIMATARIFA): Observable<EntityResponseType> {
    return this.http.post<ITCPRIMATARIFA>(this.resourceUrl, tCPRIMATARIFA, { observe: 'response' });
  }

  update(tCPRIMATARIFA: ITCPRIMATARIFA): Observable<EntityResponseType> {
    return this.http.put<ITCPRIMATARIFA>(`${this.resourceUrl}/${getTCPRIMATARIFAIdentifier(tCPRIMATARIFA) as number}`, tCPRIMATARIFA, {
      observe: 'response',
    });
  }

  partialUpdate(tCPRIMATARIFA: ITCPRIMATARIFA): Observable<EntityResponseType> {
    return this.http.patch<ITCPRIMATARIFA>(`${this.resourceUrl}/${getTCPRIMATARIFAIdentifier(tCPRIMATARIFA) as number}`, tCPRIMATARIFA, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITCPRIMATARIFA>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITCPRIMATARIFA[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addTCPRIMATARIFAToCollectionIfMissing(
    tCPRIMATARIFACollection: ITCPRIMATARIFA[],
    ...tCPRIMATARIFASToCheck: (ITCPRIMATARIFA | null | undefined)[]
  ): ITCPRIMATARIFA[] {
    const tCPRIMATARIFAS: ITCPRIMATARIFA[] = tCPRIMATARIFASToCheck.filter(isPresent);
    if (tCPRIMATARIFAS.length > 0) {
      const tCPRIMATARIFACollectionIdentifiers = tCPRIMATARIFACollection.map(
        tCPRIMATARIFAItem => getTCPRIMATARIFAIdentifier(tCPRIMATARIFAItem)!
      );
      const tCPRIMATARIFASToAdd = tCPRIMATARIFAS.filter(tCPRIMATARIFAItem => {
        const tCPRIMATARIFAIdentifier = getTCPRIMATARIFAIdentifier(tCPRIMATARIFAItem);
        if (tCPRIMATARIFAIdentifier == null || tCPRIMATARIFACollectionIdentifiers.includes(tCPRIMATARIFAIdentifier)) {
          return false;
        }
        tCPRIMATARIFACollectionIdentifiers.push(tCPRIMATARIFAIdentifier);
        return true;
      });
      return [...tCPRIMATARIFASToAdd, ...tCPRIMATARIFACollection];
    }
    return tCPRIMATARIFACollection;
  }
}
