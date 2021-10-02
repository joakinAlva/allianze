import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITCCUOTARIESGO, getTCCUOTARIESGOIdentifier } from '../tccuotariesgo.model';

export type EntityResponseType = HttpResponse<ITCCUOTARIESGO>;
export type EntityArrayResponseType = HttpResponse<ITCCUOTARIESGO[]>;

@Injectable({ providedIn: 'root' })
export class TCCUOTARIESGOService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/tccuotariesgos');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(tCCUOTARIESGO: ITCCUOTARIESGO): Observable<EntityResponseType> {
    return this.http.post<ITCCUOTARIESGO>(this.resourceUrl, tCCUOTARIESGO, { observe: 'response' });
  }

  update(tCCUOTARIESGO: ITCCUOTARIESGO): Observable<EntityResponseType> {
    return this.http.put<ITCCUOTARIESGO>(`${this.resourceUrl}/${getTCCUOTARIESGOIdentifier(tCCUOTARIESGO) as number}`, tCCUOTARIESGO, {
      observe: 'response',
    });
  }

  partialUpdate(tCCUOTARIESGO: ITCCUOTARIESGO): Observable<EntityResponseType> {
    return this.http.patch<ITCCUOTARIESGO>(`${this.resourceUrl}/${getTCCUOTARIESGOIdentifier(tCCUOTARIESGO) as number}`, tCCUOTARIESGO, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITCCUOTARIESGO>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITCCUOTARIESGO[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addTCCUOTARIESGOToCollectionIfMissing(
    tCCUOTARIESGOCollection: ITCCUOTARIESGO[],
    ...tCCUOTARIESGOSToCheck: (ITCCUOTARIESGO | null | undefined)[]
  ): ITCCUOTARIESGO[] {
    const tCCUOTARIESGOS: ITCCUOTARIESGO[] = tCCUOTARIESGOSToCheck.filter(isPresent);
    if (tCCUOTARIESGOS.length > 0) {
      const tCCUOTARIESGOCollectionIdentifiers = tCCUOTARIESGOCollection.map(
        tCCUOTARIESGOItem => getTCCUOTARIESGOIdentifier(tCCUOTARIESGOItem)!
      );
      const tCCUOTARIESGOSToAdd = tCCUOTARIESGOS.filter(tCCUOTARIESGOItem => {
        const tCCUOTARIESGOIdentifier = getTCCUOTARIESGOIdentifier(tCCUOTARIESGOItem);
        if (tCCUOTARIESGOIdentifier == null || tCCUOTARIESGOCollectionIdentifiers.includes(tCCUOTARIESGOIdentifier)) {
          return false;
        }
        tCCUOTARIESGOCollectionIdentifiers.push(tCCUOTARIESGOIdentifier);
        return true;
      });
      return [...tCCUOTARIESGOSToAdd, ...tCCUOTARIESGOCollection];
    }
    return tCCUOTARIESGOCollection;
  }
}
