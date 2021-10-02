import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITCTIPO, getTCTIPOIdentifier } from '../tctipo.model';

export type EntityResponseType = HttpResponse<ITCTIPO>;
export type EntityArrayResponseType = HttpResponse<ITCTIPO[]>;

@Injectable({ providedIn: 'root' })
export class TCTIPOService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/tctipos');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(tCTIPO: ITCTIPO): Observable<EntityResponseType> {
    return this.http.post<ITCTIPO>(this.resourceUrl, tCTIPO, { observe: 'response' });
  }

  update(tCTIPO: ITCTIPO): Observable<EntityResponseType> {
    return this.http.put<ITCTIPO>(`${this.resourceUrl}/${getTCTIPOIdentifier(tCTIPO) as number}`, tCTIPO, { observe: 'response' });
  }

  partialUpdate(tCTIPO: ITCTIPO): Observable<EntityResponseType> {
    return this.http.patch<ITCTIPO>(`${this.resourceUrl}/${getTCTIPOIdentifier(tCTIPO) as number}`, tCTIPO, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITCTIPO>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITCTIPO[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addTCTIPOToCollectionIfMissing(tCTIPOCollection: ITCTIPO[], ...tCTIPOSToCheck: (ITCTIPO | null | undefined)[]): ITCTIPO[] {
    const tCTIPOS: ITCTIPO[] = tCTIPOSToCheck.filter(isPresent);
    if (tCTIPOS.length > 0) {
      const tCTIPOCollectionIdentifiers = tCTIPOCollection.map(tCTIPOItem => getTCTIPOIdentifier(tCTIPOItem)!);
      const tCTIPOSToAdd = tCTIPOS.filter(tCTIPOItem => {
        const tCTIPOIdentifier = getTCTIPOIdentifier(tCTIPOItem);
        if (tCTIPOIdentifier == null || tCTIPOCollectionIdentifiers.includes(tCTIPOIdentifier)) {
          return false;
        }
        tCTIPOCollectionIdentifiers.push(tCTIPOIdentifier);
        return true;
      });
      return [...tCTIPOSToAdd, ...tCTIPOCollection];
    }
    return tCTIPOCollection;
  }
}
