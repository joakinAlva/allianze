import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITCDESCUENTOTIPORIESGO, getTCDESCUENTOTIPORIESGOIdentifier } from '../tcdescuentotiporiesgo.model';

export type EntityResponseType = HttpResponse<ITCDESCUENTOTIPORIESGO>;
export type EntityArrayResponseType = HttpResponse<ITCDESCUENTOTIPORIESGO[]>;

@Injectable({ providedIn: 'root' })
export class TCDESCUENTOTIPORIESGOService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/tcdescuentotiporiesgos');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(tCDESCUENTOTIPORIESGO: ITCDESCUENTOTIPORIESGO): Observable<EntityResponseType> {
    return this.http.post<ITCDESCUENTOTIPORIESGO>(this.resourceUrl, tCDESCUENTOTIPORIESGO, { observe: 'response' });
  }

  update(tCDESCUENTOTIPORIESGO: ITCDESCUENTOTIPORIESGO): Observable<EntityResponseType> {
    return this.http.put<ITCDESCUENTOTIPORIESGO>(
      `${this.resourceUrl}/${getTCDESCUENTOTIPORIESGOIdentifier(tCDESCUENTOTIPORIESGO) as number}`,
      tCDESCUENTOTIPORIESGO,
      { observe: 'response' }
    );
  }

  partialUpdate(tCDESCUENTOTIPORIESGO: ITCDESCUENTOTIPORIESGO): Observable<EntityResponseType> {
    return this.http.patch<ITCDESCUENTOTIPORIESGO>(
      `${this.resourceUrl}/${getTCDESCUENTOTIPORIESGOIdentifier(tCDESCUENTOTIPORIESGO) as number}`,
      tCDESCUENTOTIPORIESGO,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITCDESCUENTOTIPORIESGO>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITCDESCUENTOTIPORIESGO[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addTCDESCUENTOTIPORIESGOToCollectionIfMissing(
    tCDESCUENTOTIPORIESGOCollection: ITCDESCUENTOTIPORIESGO[],
    ...tCDESCUENTOTIPORIESGOSToCheck: (ITCDESCUENTOTIPORIESGO | null | undefined)[]
  ): ITCDESCUENTOTIPORIESGO[] {
    const tCDESCUENTOTIPORIESGOS: ITCDESCUENTOTIPORIESGO[] = tCDESCUENTOTIPORIESGOSToCheck.filter(isPresent);
    if (tCDESCUENTOTIPORIESGOS.length > 0) {
      const tCDESCUENTOTIPORIESGOCollectionIdentifiers = tCDESCUENTOTIPORIESGOCollection.map(
        tCDESCUENTOTIPORIESGOItem => getTCDESCUENTOTIPORIESGOIdentifier(tCDESCUENTOTIPORIESGOItem)!
      );
      const tCDESCUENTOTIPORIESGOSToAdd = tCDESCUENTOTIPORIESGOS.filter(tCDESCUENTOTIPORIESGOItem => {
        const tCDESCUENTOTIPORIESGOIdentifier = getTCDESCUENTOTIPORIESGOIdentifier(tCDESCUENTOTIPORIESGOItem);
        if (
          tCDESCUENTOTIPORIESGOIdentifier == null ||
          tCDESCUENTOTIPORIESGOCollectionIdentifiers.includes(tCDESCUENTOTIPORIESGOIdentifier)
        ) {
          return false;
        }
        tCDESCUENTOTIPORIESGOCollectionIdentifiers.push(tCDESCUENTOTIPORIESGOIdentifier);
        return true;
      });
      return [...tCDESCUENTOTIPORIESGOSToAdd, ...tCDESCUENTOTIPORIESGOCollection];
    }
    return tCDESCUENTOTIPORIESGOCollection;
  }
}
