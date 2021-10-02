import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITCCOBERTURA, getTCCOBERTURAIdentifier } from '../tccobertura.model';

export type EntityResponseType = HttpResponse<ITCCOBERTURA>;
export type EntityArrayResponseType = HttpResponse<ITCCOBERTURA[]>;

@Injectable({ providedIn: 'root' })
export class TCCOBERTURAService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/tccoberturas');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(tCCOBERTURA: ITCCOBERTURA): Observable<EntityResponseType> {
    return this.http.post<ITCCOBERTURA>(this.resourceUrl, tCCOBERTURA, { observe: 'response' });
  }

  update(tCCOBERTURA: ITCCOBERTURA): Observable<EntityResponseType> {
    return this.http.put<ITCCOBERTURA>(`${this.resourceUrl}/${getTCCOBERTURAIdentifier(tCCOBERTURA) as number}`, tCCOBERTURA, {
      observe: 'response',
    });
  }

  partialUpdate(tCCOBERTURA: ITCCOBERTURA): Observable<EntityResponseType> {
    return this.http.patch<ITCCOBERTURA>(`${this.resourceUrl}/${getTCCOBERTURAIdentifier(tCCOBERTURA) as number}`, tCCOBERTURA, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITCCOBERTURA>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITCCOBERTURA[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addTCCOBERTURAToCollectionIfMissing(
    tCCOBERTURACollection: ITCCOBERTURA[],
    ...tCCOBERTURASToCheck: (ITCCOBERTURA | null | undefined)[]
  ): ITCCOBERTURA[] {
    const tCCOBERTURAS: ITCCOBERTURA[] = tCCOBERTURASToCheck.filter(isPresent);
    if (tCCOBERTURAS.length > 0) {
      const tCCOBERTURACollectionIdentifiers = tCCOBERTURACollection.map(tCCOBERTURAItem => getTCCOBERTURAIdentifier(tCCOBERTURAItem)!);
      const tCCOBERTURASToAdd = tCCOBERTURAS.filter(tCCOBERTURAItem => {
        const tCCOBERTURAIdentifier = getTCCOBERTURAIdentifier(tCCOBERTURAItem);
        if (tCCOBERTURAIdentifier == null || tCCOBERTURACollectionIdentifiers.includes(tCCOBERTURAIdentifier)) {
          return false;
        }
        tCCOBERTURACollectionIdentifiers.push(tCCOBERTURAIdentifier);
        return true;
      });
      return [...tCCOBERTURASToAdd, ...tCCOBERTURACollection];
    }
    return tCCOBERTURACollection;
  }
}
