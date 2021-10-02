import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITCCOEFICIENTE, getTCCOEFICIENTEIdentifier } from '../tccoeficiente.model';

export type EntityResponseType = HttpResponse<ITCCOEFICIENTE>;
export type EntityArrayResponseType = HttpResponse<ITCCOEFICIENTE[]>;

@Injectable({ providedIn: 'root' })
export class TCCOEFICIENTEService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/tccoeficientes');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(tCCOEFICIENTE: ITCCOEFICIENTE): Observable<EntityResponseType> {
    return this.http.post<ITCCOEFICIENTE>(this.resourceUrl, tCCOEFICIENTE, { observe: 'response' });
  }

  update(tCCOEFICIENTE: ITCCOEFICIENTE): Observable<EntityResponseType> {
    return this.http.put<ITCCOEFICIENTE>(`${this.resourceUrl}/${getTCCOEFICIENTEIdentifier(tCCOEFICIENTE) as number}`, tCCOEFICIENTE, {
      observe: 'response',
    });
  }

  partialUpdate(tCCOEFICIENTE: ITCCOEFICIENTE): Observable<EntityResponseType> {
    return this.http.patch<ITCCOEFICIENTE>(`${this.resourceUrl}/${getTCCOEFICIENTEIdentifier(tCCOEFICIENTE) as number}`, tCCOEFICIENTE, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITCCOEFICIENTE>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITCCOEFICIENTE[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addTCCOEFICIENTEToCollectionIfMissing(
    tCCOEFICIENTECollection: ITCCOEFICIENTE[],
    ...tCCOEFICIENTESToCheck: (ITCCOEFICIENTE | null | undefined)[]
  ): ITCCOEFICIENTE[] {
    const tCCOEFICIENTES: ITCCOEFICIENTE[] = tCCOEFICIENTESToCheck.filter(isPresent);
    if (tCCOEFICIENTES.length > 0) {
      const tCCOEFICIENTECollectionIdentifiers = tCCOEFICIENTECollection.map(
        tCCOEFICIENTEItem => getTCCOEFICIENTEIdentifier(tCCOEFICIENTEItem)!
      );
      const tCCOEFICIENTESToAdd = tCCOEFICIENTES.filter(tCCOEFICIENTEItem => {
        const tCCOEFICIENTEIdentifier = getTCCOEFICIENTEIdentifier(tCCOEFICIENTEItem);
        if (tCCOEFICIENTEIdentifier == null || tCCOEFICIENTECollectionIdentifiers.includes(tCCOEFICIENTEIdentifier)) {
          return false;
        }
        tCCOEFICIENTECollectionIdentifiers.push(tCCOEFICIENTEIdentifier);
        return true;
      });
      return [...tCCOEFICIENTESToAdd, ...tCCOEFICIENTECollection];
    }
    return tCCOEFICIENTECollection;
  }
}
