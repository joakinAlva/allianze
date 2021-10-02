import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITCSUMAASEGURADA, getTCSUMAASEGURADAIdentifier } from '../tcsumaasegurada.model';

export type EntityResponseType = HttpResponse<ITCSUMAASEGURADA>;
export type EntityArrayResponseType = HttpResponse<ITCSUMAASEGURADA[]>;

@Injectable({ providedIn: 'root' })
export class TCSUMAASEGURADAService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/tcsumaaseguradas');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(tCSUMAASEGURADA: ITCSUMAASEGURADA): Observable<EntityResponseType> {
    return this.http.post<ITCSUMAASEGURADA>(this.resourceUrl, tCSUMAASEGURADA, { observe: 'response' });
  }

  update(tCSUMAASEGURADA: ITCSUMAASEGURADA): Observable<EntityResponseType> {
    return this.http.put<ITCSUMAASEGURADA>(
      `${this.resourceUrl}/${getTCSUMAASEGURADAIdentifier(tCSUMAASEGURADA) as number}`,
      tCSUMAASEGURADA,
      { observe: 'response' }
    );
  }

  partialUpdate(tCSUMAASEGURADA: ITCSUMAASEGURADA): Observable<EntityResponseType> {
    return this.http.patch<ITCSUMAASEGURADA>(
      `${this.resourceUrl}/${getTCSUMAASEGURADAIdentifier(tCSUMAASEGURADA) as number}`,
      tCSUMAASEGURADA,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITCSUMAASEGURADA>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITCSUMAASEGURADA[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addTCSUMAASEGURADAToCollectionIfMissing(
    tCSUMAASEGURADACollection: ITCSUMAASEGURADA[],
    ...tCSUMAASEGURADASToCheck: (ITCSUMAASEGURADA | null | undefined)[]
  ): ITCSUMAASEGURADA[] {
    const tCSUMAASEGURADAS: ITCSUMAASEGURADA[] = tCSUMAASEGURADASToCheck.filter(isPresent);
    if (tCSUMAASEGURADAS.length > 0) {
      const tCSUMAASEGURADACollectionIdentifiers = tCSUMAASEGURADACollection.map(
        tCSUMAASEGURADAItem => getTCSUMAASEGURADAIdentifier(tCSUMAASEGURADAItem)!
      );
      const tCSUMAASEGURADASToAdd = tCSUMAASEGURADAS.filter(tCSUMAASEGURADAItem => {
        const tCSUMAASEGURADAIdentifier = getTCSUMAASEGURADAIdentifier(tCSUMAASEGURADAItem);
        if (tCSUMAASEGURADAIdentifier == null || tCSUMAASEGURADACollectionIdentifiers.includes(tCSUMAASEGURADAIdentifier)) {
          return false;
        }
        tCSUMAASEGURADACollectionIdentifiers.push(tCSUMAASEGURADAIdentifier);
        return true;
      });
      return [...tCSUMAASEGURADASToAdd, ...tCSUMAASEGURADACollection];
    }
    return tCSUMAASEGURADACollection;
  }
}
