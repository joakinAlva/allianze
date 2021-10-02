import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITCCUOTATARIFASDESC, getTCCUOTATARIFASDESCIdentifier } from '../tccuotatarifasdesc.model';

export type EntityResponseType = HttpResponse<ITCCUOTATARIFASDESC>;
export type EntityArrayResponseType = HttpResponse<ITCCUOTATARIFASDESC[]>;

@Injectable({ providedIn: 'root' })
export class TCCUOTATARIFASDESCService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/tccuotatarifasdescs');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(tCCUOTATARIFASDESC: ITCCUOTATARIFASDESC): Observable<EntityResponseType> {
    return this.http.post<ITCCUOTATARIFASDESC>(this.resourceUrl, tCCUOTATARIFASDESC, { observe: 'response' });
  }

  update(tCCUOTATARIFASDESC: ITCCUOTATARIFASDESC): Observable<EntityResponseType> {
    return this.http.put<ITCCUOTATARIFASDESC>(
      `${this.resourceUrl}/${getTCCUOTATARIFASDESCIdentifier(tCCUOTATARIFASDESC) as number}`,
      tCCUOTATARIFASDESC,
      { observe: 'response' }
    );
  }

  partialUpdate(tCCUOTATARIFASDESC: ITCCUOTATARIFASDESC): Observable<EntityResponseType> {
    return this.http.patch<ITCCUOTATARIFASDESC>(
      `${this.resourceUrl}/${getTCCUOTATARIFASDESCIdentifier(tCCUOTATARIFASDESC) as number}`,
      tCCUOTATARIFASDESC,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITCCUOTATARIFASDESC>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITCCUOTATARIFASDESC[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addTCCUOTATARIFASDESCToCollectionIfMissing(
    tCCUOTATARIFASDESCCollection: ITCCUOTATARIFASDESC[],
    ...tCCUOTATARIFASDESCSToCheck: (ITCCUOTATARIFASDESC | null | undefined)[]
  ): ITCCUOTATARIFASDESC[] {
    const tCCUOTATARIFASDESCS: ITCCUOTATARIFASDESC[] = tCCUOTATARIFASDESCSToCheck.filter(isPresent);
    if (tCCUOTATARIFASDESCS.length > 0) {
      const tCCUOTATARIFASDESCCollectionIdentifiers = tCCUOTATARIFASDESCCollection.map(
        tCCUOTATARIFASDESCItem => getTCCUOTATARIFASDESCIdentifier(tCCUOTATARIFASDESCItem)!
      );
      const tCCUOTATARIFASDESCSToAdd = tCCUOTATARIFASDESCS.filter(tCCUOTATARIFASDESCItem => {
        const tCCUOTATARIFASDESCIdentifier = getTCCUOTATARIFASDESCIdentifier(tCCUOTATARIFASDESCItem);
        if (tCCUOTATARIFASDESCIdentifier == null || tCCUOTATARIFASDESCCollectionIdentifiers.includes(tCCUOTATARIFASDESCIdentifier)) {
          return false;
        }
        tCCUOTATARIFASDESCCollectionIdentifiers.push(tCCUOTATARIFASDESCIdentifier);
        return true;
      });
      return [...tCCUOTATARIFASDESCSToAdd, ...tCCUOTATARIFASDESCCollection];
    }
    return tCCUOTATARIFASDESCCollection;
  }
}
