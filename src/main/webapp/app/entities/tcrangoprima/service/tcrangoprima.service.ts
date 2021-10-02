import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITCRANGOPRIMA, getTCRANGOPRIMAIdentifier } from '../tcrangoprima.model';

export type EntityResponseType = HttpResponse<ITCRANGOPRIMA>;
export type EntityArrayResponseType = HttpResponse<ITCRANGOPRIMA[]>;

@Injectable({ providedIn: 'root' })
export class TCRANGOPRIMAService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/tcrangoprimas');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(tCRANGOPRIMA: ITCRANGOPRIMA): Observable<EntityResponseType> {
    return this.http.post<ITCRANGOPRIMA>(this.resourceUrl, tCRANGOPRIMA, { observe: 'response' });
  }

  update(tCRANGOPRIMA: ITCRANGOPRIMA): Observable<EntityResponseType> {
    return this.http.put<ITCRANGOPRIMA>(`${this.resourceUrl}/${getTCRANGOPRIMAIdentifier(tCRANGOPRIMA) as number}`, tCRANGOPRIMA, {
      observe: 'response',
    });
  }

  partialUpdate(tCRANGOPRIMA: ITCRANGOPRIMA): Observable<EntityResponseType> {
    return this.http.patch<ITCRANGOPRIMA>(`${this.resourceUrl}/${getTCRANGOPRIMAIdentifier(tCRANGOPRIMA) as number}`, tCRANGOPRIMA, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITCRANGOPRIMA>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITCRANGOPRIMA[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addTCRANGOPRIMAToCollectionIfMissing(
    tCRANGOPRIMACollection: ITCRANGOPRIMA[],
    ...tCRANGOPRIMASToCheck: (ITCRANGOPRIMA | null | undefined)[]
  ): ITCRANGOPRIMA[] {
    const tCRANGOPRIMAS: ITCRANGOPRIMA[] = tCRANGOPRIMASToCheck.filter(isPresent);
    if (tCRANGOPRIMAS.length > 0) {
      const tCRANGOPRIMACollectionIdentifiers = tCRANGOPRIMACollection.map(
        tCRANGOPRIMAItem => getTCRANGOPRIMAIdentifier(tCRANGOPRIMAItem)!
      );
      const tCRANGOPRIMASToAdd = tCRANGOPRIMAS.filter(tCRANGOPRIMAItem => {
        const tCRANGOPRIMAIdentifier = getTCRANGOPRIMAIdentifier(tCRANGOPRIMAItem);
        if (tCRANGOPRIMAIdentifier == null || tCRANGOPRIMACollectionIdentifiers.includes(tCRANGOPRIMAIdentifier)) {
          return false;
        }
        tCRANGOPRIMACollectionIdentifiers.push(tCRANGOPRIMAIdentifier);
        return true;
      });
      return [...tCRANGOPRIMASToAdd, ...tCRANGOPRIMACollection];
    }
    return tCRANGOPRIMACollection;
  }
}
