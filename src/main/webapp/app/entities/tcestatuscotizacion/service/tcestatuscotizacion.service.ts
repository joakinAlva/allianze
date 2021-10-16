import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITCESTATUSCOTIZACION, getTCESTATUSCOTIZACIONIdentifier } from '../tcestatuscotizacion.model';

export type EntityResponseType = HttpResponse<ITCESTATUSCOTIZACION>;
export type EntityArrayResponseType = HttpResponse<ITCESTATUSCOTIZACION[]>;

@Injectable({ providedIn: 'root' })
export class TCESTATUSCOTIZACIONService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/tcestatuscotizacions');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(tCESTATUSCOTIZACION: ITCESTATUSCOTIZACION): Observable<EntityResponseType> {
    return this.http.post<ITCESTATUSCOTIZACION>(this.resourceUrl, tCESTATUSCOTIZACION, { observe: 'response' });
  }

  update(tCESTATUSCOTIZACION: ITCESTATUSCOTIZACION): Observable<EntityResponseType> {
    return this.http.put<ITCESTATUSCOTIZACION>(
      `${this.resourceUrl}/${getTCESTATUSCOTIZACIONIdentifier(tCESTATUSCOTIZACION) as number}`,
      tCESTATUSCOTIZACION,
      { observe: 'response' }
    );
  }

  partialUpdate(tCESTATUSCOTIZACION: ITCESTATUSCOTIZACION): Observable<EntityResponseType> {
    return this.http.patch<ITCESTATUSCOTIZACION>(
      `${this.resourceUrl}/${getTCESTATUSCOTIZACIONIdentifier(tCESTATUSCOTIZACION) as number}`,
      tCESTATUSCOTIZACION,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITCESTATUSCOTIZACION>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITCESTATUSCOTIZACION[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addTCESTATUSCOTIZACIONToCollectionIfMissing(
    tCESTATUSCOTIZACIONCollection: ITCESTATUSCOTIZACION[],
    ...tCESTATUSCOTIZACIONSToCheck: (ITCESTATUSCOTIZACION | null | undefined)[]
  ): ITCESTATUSCOTIZACION[] {
    const tCESTATUSCOTIZACIONS: ITCESTATUSCOTIZACION[] = tCESTATUSCOTIZACIONSToCheck.filter(isPresent);
    if (tCESTATUSCOTIZACIONS.length > 0) {
      const tCESTATUSCOTIZACIONCollectionIdentifiers = tCESTATUSCOTIZACIONCollection.map(
        tCESTATUSCOTIZACIONItem => getTCESTATUSCOTIZACIONIdentifier(tCESTATUSCOTIZACIONItem)!
      );
      const tCESTATUSCOTIZACIONSToAdd = tCESTATUSCOTIZACIONS.filter(tCESTATUSCOTIZACIONItem => {
        const tCESTATUSCOTIZACIONIdentifier = getTCESTATUSCOTIZACIONIdentifier(tCESTATUSCOTIZACIONItem);
        if (tCESTATUSCOTIZACIONIdentifier == null || tCESTATUSCOTIZACIONCollectionIdentifiers.includes(tCESTATUSCOTIZACIONIdentifier)) {
          return false;
        }
        tCESTATUSCOTIZACIONCollectionIdentifiers.push(tCESTATUSCOTIZACIONIdentifier);
        return true;
      });
      return [...tCESTATUSCOTIZACIONSToAdd, ...tCESTATUSCOTIZACIONCollection];
    }
    return tCESTATUSCOTIZACIONCollection;
  }
}
