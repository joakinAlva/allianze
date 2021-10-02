import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITCSUBGRUPO, getTCSUBGRUPOIdentifier } from '../tcsubgrupo.model';

export type EntityResponseType = HttpResponse<ITCSUBGRUPO>;
export type EntityArrayResponseType = HttpResponse<ITCSUBGRUPO[]>;

@Injectable({ providedIn: 'root' })
export class TCSUBGRUPOService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/tcsubgrupos');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(tCSUBGRUPO: ITCSUBGRUPO): Observable<EntityResponseType> {
    return this.http.post<ITCSUBGRUPO>(this.resourceUrl, tCSUBGRUPO, { observe: 'response' });
  }

  update(tCSUBGRUPO: ITCSUBGRUPO): Observable<EntityResponseType> {
    return this.http.put<ITCSUBGRUPO>(`${this.resourceUrl}/${getTCSUBGRUPOIdentifier(tCSUBGRUPO) as number}`, tCSUBGRUPO, {
      observe: 'response',
    });
  }

  partialUpdate(tCSUBGRUPO: ITCSUBGRUPO): Observable<EntityResponseType> {
    return this.http.patch<ITCSUBGRUPO>(`${this.resourceUrl}/${getTCSUBGRUPOIdentifier(tCSUBGRUPO) as number}`, tCSUBGRUPO, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITCSUBGRUPO>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITCSUBGRUPO[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addTCSUBGRUPOToCollectionIfMissing(
    tCSUBGRUPOCollection: ITCSUBGRUPO[],
    ...tCSUBGRUPOSToCheck: (ITCSUBGRUPO | null | undefined)[]
  ): ITCSUBGRUPO[] {
    const tCSUBGRUPOS: ITCSUBGRUPO[] = tCSUBGRUPOSToCheck.filter(isPresent);
    if (tCSUBGRUPOS.length > 0) {
      const tCSUBGRUPOCollectionIdentifiers = tCSUBGRUPOCollection.map(tCSUBGRUPOItem => getTCSUBGRUPOIdentifier(tCSUBGRUPOItem)!);
      const tCSUBGRUPOSToAdd = tCSUBGRUPOS.filter(tCSUBGRUPOItem => {
        const tCSUBGRUPOIdentifier = getTCSUBGRUPOIdentifier(tCSUBGRUPOItem);
        if (tCSUBGRUPOIdentifier == null || tCSUBGRUPOCollectionIdentifiers.includes(tCSUBGRUPOIdentifier)) {
          return false;
        }
        tCSUBGRUPOCollectionIdentifiers.push(tCSUBGRUPOIdentifier);
        return true;
      });
      return [...tCSUBGRUPOSToAdd, ...tCSUBGRUPOCollection];
    }
    return tCSUBGRUPOCollection;
  }
}
