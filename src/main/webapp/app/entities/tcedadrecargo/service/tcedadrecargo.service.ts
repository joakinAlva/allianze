import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITCEDADRECARGO, getTCEDADRECARGOIdentifier } from '../tcedadrecargo.model';

export type EntityResponseType = HttpResponse<ITCEDADRECARGO>;
export type EntityArrayResponseType = HttpResponse<ITCEDADRECARGO[]>;

@Injectable({ providedIn: 'root' })
export class TCEDADRECARGOService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/tcedadrecargos');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(tCEDADRECARGO: ITCEDADRECARGO): Observable<EntityResponseType> {
    return this.http.post<ITCEDADRECARGO>(this.resourceUrl, tCEDADRECARGO, { observe: 'response' });
  }

  update(tCEDADRECARGO: ITCEDADRECARGO): Observable<EntityResponseType> {
    return this.http.put<ITCEDADRECARGO>(`${this.resourceUrl}/${getTCEDADRECARGOIdentifier(tCEDADRECARGO) as number}`, tCEDADRECARGO, {
      observe: 'response',
    });
  }

  partialUpdate(tCEDADRECARGO: ITCEDADRECARGO): Observable<EntityResponseType> {
    return this.http.patch<ITCEDADRECARGO>(`${this.resourceUrl}/${getTCEDADRECARGOIdentifier(tCEDADRECARGO) as number}`, tCEDADRECARGO, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITCEDADRECARGO>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITCEDADRECARGO[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addTCEDADRECARGOToCollectionIfMissing(
    tCEDADRECARGOCollection: ITCEDADRECARGO[],
    ...tCEDADRECARGOSToCheck: (ITCEDADRECARGO | null | undefined)[]
  ): ITCEDADRECARGO[] {
    const tCEDADRECARGOS: ITCEDADRECARGO[] = tCEDADRECARGOSToCheck.filter(isPresent);
    if (tCEDADRECARGOS.length > 0) {
      const tCEDADRECARGOCollectionIdentifiers = tCEDADRECARGOCollection.map(
        tCEDADRECARGOItem => getTCEDADRECARGOIdentifier(tCEDADRECARGOItem)!
      );
      const tCEDADRECARGOSToAdd = tCEDADRECARGOS.filter(tCEDADRECARGOItem => {
        const tCEDADRECARGOIdentifier = getTCEDADRECARGOIdentifier(tCEDADRECARGOItem);
        if (tCEDADRECARGOIdentifier == null || tCEDADRECARGOCollectionIdentifiers.includes(tCEDADRECARGOIdentifier)) {
          return false;
        }
        tCEDADRECARGOCollectionIdentifiers.push(tCEDADRECARGOIdentifier);
        return true;
      });
      return [...tCEDADRECARGOSToAdd, ...tCEDADRECARGOCollection];
    }
    return tCEDADRECARGOCollection;
  }
}
