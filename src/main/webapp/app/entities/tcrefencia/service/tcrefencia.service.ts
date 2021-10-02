import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITCREFENCIA, getTCREFENCIAIdentifier } from '../tcrefencia.model';

export type EntityResponseType = HttpResponse<ITCREFENCIA>;
export type EntityArrayResponseType = HttpResponse<ITCREFENCIA[]>;

@Injectable({ providedIn: 'root' })
export class TCREFENCIAService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/tcrefencias');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(tCREFENCIA: ITCREFENCIA): Observable<EntityResponseType> {
    return this.http.post<ITCREFENCIA>(this.resourceUrl, tCREFENCIA, { observe: 'response' });
  }

  update(tCREFENCIA: ITCREFENCIA): Observable<EntityResponseType> {
    return this.http.put<ITCREFENCIA>(`${this.resourceUrl}/${getTCREFENCIAIdentifier(tCREFENCIA) as number}`, tCREFENCIA, {
      observe: 'response',
    });
  }

  partialUpdate(tCREFENCIA: ITCREFENCIA): Observable<EntityResponseType> {
    return this.http.patch<ITCREFENCIA>(`${this.resourceUrl}/${getTCREFENCIAIdentifier(tCREFENCIA) as number}`, tCREFENCIA, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITCREFENCIA>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITCREFENCIA[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addTCREFENCIAToCollectionIfMissing(
    tCREFENCIACollection: ITCREFENCIA[],
    ...tCREFENCIASToCheck: (ITCREFENCIA | null | undefined)[]
  ): ITCREFENCIA[] {
    const tCREFENCIAS: ITCREFENCIA[] = tCREFENCIASToCheck.filter(isPresent);
    if (tCREFENCIAS.length > 0) {
      const tCREFENCIACollectionIdentifiers = tCREFENCIACollection.map(tCREFENCIAItem => getTCREFENCIAIdentifier(tCREFENCIAItem)!);
      const tCREFENCIASToAdd = tCREFENCIAS.filter(tCREFENCIAItem => {
        const tCREFENCIAIdentifier = getTCREFENCIAIdentifier(tCREFENCIAItem);
        if (tCREFENCIAIdentifier == null || tCREFENCIACollectionIdentifiers.includes(tCREFENCIAIdentifier)) {
          return false;
        }
        tCREFENCIACollectionIdentifiers.push(tCREFENCIAIdentifier);
        return true;
      });
      return [...tCREFENCIASToAdd, ...tCREFENCIACollection];
    }
    return tCREFENCIACollection;
  }
}
