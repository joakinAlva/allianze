import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITCCOVID, getTCCOVIDIdentifier } from '../tccovid.model';

export type EntityResponseType = HttpResponse<ITCCOVID>;
export type EntityArrayResponseType = HttpResponse<ITCCOVID[]>;

@Injectable({ providedIn: 'root' })
export class TCCOVIDService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/tccovids');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(tCCOVID: ITCCOVID): Observable<EntityResponseType> {
    return this.http.post<ITCCOVID>(this.resourceUrl, tCCOVID, { observe: 'response' });
  }

  update(tCCOVID: ITCCOVID): Observable<EntityResponseType> {
    return this.http.put<ITCCOVID>(`${this.resourceUrl}/${getTCCOVIDIdentifier(tCCOVID) as number}`, tCCOVID, { observe: 'response' });
  }

  partialUpdate(tCCOVID: ITCCOVID): Observable<EntityResponseType> {
    return this.http.patch<ITCCOVID>(`${this.resourceUrl}/${getTCCOVIDIdentifier(tCCOVID) as number}`, tCCOVID, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITCCOVID>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITCCOVID[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addTCCOVIDToCollectionIfMissing(tCCOVIDCollection: ITCCOVID[], ...tCCOVIDSToCheck: (ITCCOVID | null | undefined)[]): ITCCOVID[] {
    const tCCOVIDS: ITCCOVID[] = tCCOVIDSToCheck.filter(isPresent);
    if (tCCOVIDS.length > 0) {
      const tCCOVIDCollectionIdentifiers = tCCOVIDCollection.map(tCCOVIDItem => getTCCOVIDIdentifier(tCCOVIDItem)!);
      const tCCOVIDSToAdd = tCCOVIDS.filter(tCCOVIDItem => {
        const tCCOVIDIdentifier = getTCCOVIDIdentifier(tCCOVIDItem);
        if (tCCOVIDIdentifier == null || tCCOVIDCollectionIdentifiers.includes(tCCOVIDIdentifier)) {
          return false;
        }
        tCCOVIDCollectionIdentifiers.push(tCCOVIDIdentifier);
        return true;
      });
      return [...tCCOVIDSToAdd, ...tCCOVIDCollection];
    }
    return tCCOVIDCollection;
  }
}
