import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITCHIPOTESIS, getTCHIPOTESISIdentifier } from '../tchipotesis.model';

export type EntityResponseType = HttpResponse<ITCHIPOTESIS>;
export type EntityArrayResponseType = HttpResponse<ITCHIPOTESIS[]>;

@Injectable({ providedIn: 'root' })
export class TCHIPOTESISService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/tchipoteses');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(tCHIPOTESIS: ITCHIPOTESIS): Observable<EntityResponseType> {
    return this.http.post<ITCHIPOTESIS>(this.resourceUrl, tCHIPOTESIS, { observe: 'response' });
  }

  update(tCHIPOTESIS: ITCHIPOTESIS): Observable<EntityResponseType> {
    return this.http.put<ITCHIPOTESIS>(`${this.resourceUrl}/${getTCHIPOTESISIdentifier(tCHIPOTESIS) as number}`, tCHIPOTESIS, {
      observe: 'response',
    });
  }

  partialUpdate(tCHIPOTESIS: ITCHIPOTESIS): Observable<EntityResponseType> {
    return this.http.patch<ITCHIPOTESIS>(`${this.resourceUrl}/${getTCHIPOTESISIdentifier(tCHIPOTESIS) as number}`, tCHIPOTESIS, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITCHIPOTESIS>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITCHIPOTESIS[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addTCHIPOTESISToCollectionIfMissing(
    tCHIPOTESISCollection: ITCHIPOTESIS[],
    ...tCHIPOTESESToCheck: (ITCHIPOTESIS | null | undefined)[]
  ): ITCHIPOTESIS[] {
    const tCHIPOTESES: ITCHIPOTESIS[] = tCHIPOTESESToCheck.filter(isPresent);
    if (tCHIPOTESES.length > 0) {
      const tCHIPOTESISCollectionIdentifiers = tCHIPOTESISCollection.map(tCHIPOTESISItem => getTCHIPOTESISIdentifier(tCHIPOTESISItem)!);
      const tCHIPOTESESToAdd = tCHIPOTESES.filter(tCHIPOTESISItem => {
        const tCHIPOTESISIdentifier = getTCHIPOTESISIdentifier(tCHIPOTESISItem);
        if (tCHIPOTESISIdentifier == null || tCHIPOTESISCollectionIdentifiers.includes(tCHIPOTESISIdentifier)) {
          return false;
        }
        tCHIPOTESISCollectionIdentifiers.push(tCHIPOTESISIdentifier);
        return true;
      });
      return [...tCHIPOTESESToAdd, ...tCHIPOTESISCollection];
    }
    return tCHIPOTESISCollection;
  }
}
