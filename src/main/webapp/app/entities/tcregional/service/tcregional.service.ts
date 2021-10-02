import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITCREGIONAL, getTCREGIONALIdentifier } from '../tcregional.model';

export type EntityResponseType = HttpResponse<ITCREGIONAL>;
export type EntityArrayResponseType = HttpResponse<ITCREGIONAL[]>;

@Injectable({ providedIn: 'root' })
export class TCREGIONALService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/tcregionals');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(tCREGIONAL: ITCREGIONAL): Observable<EntityResponseType> {
    return this.http.post<ITCREGIONAL>(this.resourceUrl, tCREGIONAL, { observe: 'response' });
  }

  update(tCREGIONAL: ITCREGIONAL): Observable<EntityResponseType> {
    return this.http.put<ITCREGIONAL>(`${this.resourceUrl}/${getTCREGIONALIdentifier(tCREGIONAL) as number}`, tCREGIONAL, {
      observe: 'response',
    });
  }

  partialUpdate(tCREGIONAL: ITCREGIONAL): Observable<EntityResponseType> {
    return this.http.patch<ITCREGIONAL>(`${this.resourceUrl}/${getTCREGIONALIdentifier(tCREGIONAL) as number}`, tCREGIONAL, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITCREGIONAL>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITCREGIONAL[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addTCREGIONALToCollectionIfMissing(
    tCREGIONALCollection: ITCREGIONAL[],
    ...tCREGIONALSToCheck: (ITCREGIONAL | null | undefined)[]
  ): ITCREGIONAL[] {
    const tCREGIONALS: ITCREGIONAL[] = tCREGIONALSToCheck.filter(isPresent);
    if (tCREGIONALS.length > 0) {
      const tCREGIONALCollectionIdentifiers = tCREGIONALCollection.map(tCREGIONALItem => getTCREGIONALIdentifier(tCREGIONALItem)!);
      const tCREGIONALSToAdd = tCREGIONALS.filter(tCREGIONALItem => {
        const tCREGIONALIdentifier = getTCREGIONALIdentifier(tCREGIONALItem);
        if (tCREGIONALIdentifier == null || tCREGIONALCollectionIdentifiers.includes(tCREGIONALIdentifier)) {
          return false;
        }
        tCREGIONALCollectionIdentifiers.push(tCREGIONALIdentifier);
        return true;
      });
      return [...tCREGIONALSToAdd, ...tCREGIONALCollection];
    }
    return tCREGIONALCollection;
  }
}
