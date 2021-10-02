import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITCCUOTAPROPUESTA, getTCCUOTAPROPUESTAIdentifier } from '../tccuotapropuesta.model';

export type EntityResponseType = HttpResponse<ITCCUOTAPROPUESTA>;
export type EntityArrayResponseType = HttpResponse<ITCCUOTAPROPUESTA[]>;

@Injectable({ providedIn: 'root' })
export class TCCUOTAPROPUESTAService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/tccuotapropuestas');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(tCCUOTAPROPUESTA: ITCCUOTAPROPUESTA): Observable<EntityResponseType> {
    return this.http.post<ITCCUOTAPROPUESTA>(this.resourceUrl, tCCUOTAPROPUESTA, { observe: 'response' });
  }

  update(tCCUOTAPROPUESTA: ITCCUOTAPROPUESTA): Observable<EntityResponseType> {
    return this.http.put<ITCCUOTAPROPUESTA>(
      `${this.resourceUrl}/${getTCCUOTAPROPUESTAIdentifier(tCCUOTAPROPUESTA) as number}`,
      tCCUOTAPROPUESTA,
      { observe: 'response' }
    );
  }

  partialUpdate(tCCUOTAPROPUESTA: ITCCUOTAPROPUESTA): Observable<EntityResponseType> {
    return this.http.patch<ITCCUOTAPROPUESTA>(
      `${this.resourceUrl}/${getTCCUOTAPROPUESTAIdentifier(tCCUOTAPROPUESTA) as number}`,
      tCCUOTAPROPUESTA,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITCCUOTAPROPUESTA>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITCCUOTAPROPUESTA[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addTCCUOTAPROPUESTAToCollectionIfMissing(
    tCCUOTAPROPUESTACollection: ITCCUOTAPROPUESTA[],
    ...tCCUOTAPROPUESTASToCheck: (ITCCUOTAPROPUESTA | null | undefined)[]
  ): ITCCUOTAPROPUESTA[] {
    const tCCUOTAPROPUESTAS: ITCCUOTAPROPUESTA[] = tCCUOTAPROPUESTASToCheck.filter(isPresent);
    if (tCCUOTAPROPUESTAS.length > 0) {
      const tCCUOTAPROPUESTACollectionIdentifiers = tCCUOTAPROPUESTACollection.map(
        tCCUOTAPROPUESTAItem => getTCCUOTAPROPUESTAIdentifier(tCCUOTAPROPUESTAItem)!
      );
      const tCCUOTAPROPUESTASToAdd = tCCUOTAPROPUESTAS.filter(tCCUOTAPROPUESTAItem => {
        const tCCUOTAPROPUESTAIdentifier = getTCCUOTAPROPUESTAIdentifier(tCCUOTAPROPUESTAItem);
        if (tCCUOTAPROPUESTAIdentifier == null || tCCUOTAPROPUESTACollectionIdentifiers.includes(tCCUOTAPROPUESTAIdentifier)) {
          return false;
        }
        tCCUOTAPROPUESTACollectionIdentifiers.push(tCCUOTAPROPUESTAIdentifier);
        return true;
      });
      return [...tCCUOTAPROPUESTASToAdd, ...tCCUOTAPROPUESTACollection];
    }
    return tCCUOTAPROPUESTACollection;
  }
}
