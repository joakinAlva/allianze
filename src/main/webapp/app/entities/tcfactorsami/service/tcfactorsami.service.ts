import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITCFACTORSAMI, getTCFACTORSAMIIdentifier } from '../tcfactorsami.model';

export type EntityResponseType = HttpResponse<ITCFACTORSAMI>;
export type EntityArrayResponseType = HttpResponse<ITCFACTORSAMI[]>;

@Injectable({ providedIn: 'root' })
export class TCFACTORSAMIService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/tcfactorsamis');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(tCFACTORSAMI: ITCFACTORSAMI): Observable<EntityResponseType> {
    return this.http.post<ITCFACTORSAMI>(this.resourceUrl, tCFACTORSAMI, { observe: 'response' });
  }

  update(tCFACTORSAMI: ITCFACTORSAMI): Observable<EntityResponseType> {
    return this.http.put<ITCFACTORSAMI>(`${this.resourceUrl}/${getTCFACTORSAMIIdentifier(tCFACTORSAMI) as number}`, tCFACTORSAMI, {
      observe: 'response',
    });
  }

  partialUpdate(tCFACTORSAMI: ITCFACTORSAMI): Observable<EntityResponseType> {
    return this.http.patch<ITCFACTORSAMI>(`${this.resourceUrl}/${getTCFACTORSAMIIdentifier(tCFACTORSAMI) as number}`, tCFACTORSAMI, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITCFACTORSAMI>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITCFACTORSAMI[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addTCFACTORSAMIToCollectionIfMissing(
    tCFACTORSAMICollection: ITCFACTORSAMI[],
    ...tCFACTORSAMISToCheck: (ITCFACTORSAMI | null | undefined)[]
  ): ITCFACTORSAMI[] {
    const tCFACTORSAMIS: ITCFACTORSAMI[] = tCFACTORSAMISToCheck.filter(isPresent);
    if (tCFACTORSAMIS.length > 0) {
      const tCFACTORSAMICollectionIdentifiers = tCFACTORSAMICollection.map(
        tCFACTORSAMIItem => getTCFACTORSAMIIdentifier(tCFACTORSAMIItem)!
      );
      const tCFACTORSAMISToAdd = tCFACTORSAMIS.filter(tCFACTORSAMIItem => {
        const tCFACTORSAMIIdentifier = getTCFACTORSAMIIdentifier(tCFACTORSAMIItem);
        if (tCFACTORSAMIIdentifier == null || tCFACTORSAMICollectionIdentifiers.includes(tCFACTORSAMIIdentifier)) {
          return false;
        }
        tCFACTORSAMICollectionIdentifiers.push(tCFACTORSAMIIdentifier);
        return true;
      });
      return [...tCFACTORSAMISToAdd, ...tCFACTORSAMICollection];
    }
    return tCFACTORSAMICollection;
  }
}
