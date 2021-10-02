import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITCCUOTAVALOR, getTCCUOTAVALORIdentifier } from '../tccuotavalor.model';

export type EntityResponseType = HttpResponse<ITCCUOTAVALOR>;
export type EntityArrayResponseType = HttpResponse<ITCCUOTAVALOR[]>;

@Injectable({ providedIn: 'root' })
export class TCCUOTAVALORService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/tccuotavalors');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(tCCUOTAVALOR: ITCCUOTAVALOR): Observable<EntityResponseType> {
    return this.http.post<ITCCUOTAVALOR>(this.resourceUrl, tCCUOTAVALOR, { observe: 'response' });
  }

  update(tCCUOTAVALOR: ITCCUOTAVALOR): Observable<EntityResponseType> {
    return this.http.put<ITCCUOTAVALOR>(`${this.resourceUrl}/${getTCCUOTAVALORIdentifier(tCCUOTAVALOR) as number}`, tCCUOTAVALOR, {
      observe: 'response',
    });
  }

  partialUpdate(tCCUOTAVALOR: ITCCUOTAVALOR): Observable<EntityResponseType> {
    return this.http.patch<ITCCUOTAVALOR>(`${this.resourceUrl}/${getTCCUOTAVALORIdentifier(tCCUOTAVALOR) as number}`, tCCUOTAVALOR, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITCCUOTAVALOR>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITCCUOTAVALOR[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addTCCUOTAVALORToCollectionIfMissing(
    tCCUOTAVALORCollection: ITCCUOTAVALOR[],
    ...tCCUOTAVALORSToCheck: (ITCCUOTAVALOR | null | undefined)[]
  ): ITCCUOTAVALOR[] {
    const tCCUOTAVALORS: ITCCUOTAVALOR[] = tCCUOTAVALORSToCheck.filter(isPresent);
    if (tCCUOTAVALORS.length > 0) {
      const tCCUOTAVALORCollectionIdentifiers = tCCUOTAVALORCollection.map(
        tCCUOTAVALORItem => getTCCUOTAVALORIdentifier(tCCUOTAVALORItem)!
      );
      const tCCUOTAVALORSToAdd = tCCUOTAVALORS.filter(tCCUOTAVALORItem => {
        const tCCUOTAVALORIdentifier = getTCCUOTAVALORIdentifier(tCCUOTAVALORItem);
        if (tCCUOTAVALORIdentifier == null || tCCUOTAVALORCollectionIdentifiers.includes(tCCUOTAVALORIdentifier)) {
          return false;
        }
        tCCUOTAVALORCollectionIdentifiers.push(tCCUOTAVALORIdentifier);
        return true;
      });
      return [...tCCUOTAVALORSToAdd, ...tCCUOTAVALORCollection];
    }
    return tCCUOTAVALORCollection;
  }
}
