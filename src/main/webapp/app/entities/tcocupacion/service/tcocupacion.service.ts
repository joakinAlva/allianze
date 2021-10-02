import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITCOCUPACION, getTCOCUPACIONIdentifier } from '../tcocupacion.model';

export type EntityResponseType = HttpResponse<ITCOCUPACION>;
export type EntityArrayResponseType = HttpResponse<ITCOCUPACION[]>;

@Injectable({ providedIn: 'root' })
export class TCOCUPACIONService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/tcocupacions');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(tCOCUPACION: ITCOCUPACION): Observable<EntityResponseType> {
    return this.http.post<ITCOCUPACION>(this.resourceUrl, tCOCUPACION, { observe: 'response' });
  }

  update(tCOCUPACION: ITCOCUPACION): Observable<EntityResponseType> {
    return this.http.put<ITCOCUPACION>(`${this.resourceUrl}/${getTCOCUPACIONIdentifier(tCOCUPACION) as number}`, tCOCUPACION, {
      observe: 'response',
    });
  }

  partialUpdate(tCOCUPACION: ITCOCUPACION): Observable<EntityResponseType> {
    return this.http.patch<ITCOCUPACION>(`${this.resourceUrl}/${getTCOCUPACIONIdentifier(tCOCUPACION) as number}`, tCOCUPACION, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITCOCUPACION>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITCOCUPACION[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addTCOCUPACIONToCollectionIfMissing(
    tCOCUPACIONCollection: ITCOCUPACION[],
    ...tCOCUPACIONSToCheck: (ITCOCUPACION | null | undefined)[]
  ): ITCOCUPACION[] {
    const tCOCUPACIONS: ITCOCUPACION[] = tCOCUPACIONSToCheck.filter(isPresent);
    if (tCOCUPACIONS.length > 0) {
      const tCOCUPACIONCollectionIdentifiers = tCOCUPACIONCollection.map(tCOCUPACIONItem => getTCOCUPACIONIdentifier(tCOCUPACIONItem)!);
      const tCOCUPACIONSToAdd = tCOCUPACIONS.filter(tCOCUPACIONItem => {
        const tCOCUPACIONIdentifier = getTCOCUPACIONIdentifier(tCOCUPACIONItem);
        if (tCOCUPACIONIdentifier == null || tCOCUPACIONCollectionIdentifiers.includes(tCOCUPACIONIdentifier)) {
          return false;
        }
        tCOCUPACIONCollectionIdentifiers.push(tCOCUPACIONIdentifier);
        return true;
      });
      return [...tCOCUPACIONSToAdd, ...tCOCUPACIONCollection];
    }
    return tCOCUPACIONCollection;
  }
}
