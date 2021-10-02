import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITCCONCEPTO, getTCCONCEPTOIdentifier } from '../tcconcepto.model';

export type EntityResponseType = HttpResponse<ITCCONCEPTO>;
export type EntityArrayResponseType = HttpResponse<ITCCONCEPTO[]>;

@Injectable({ providedIn: 'root' })
export class TCCONCEPTOService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/tcconceptos');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(tCCONCEPTO: ITCCONCEPTO): Observable<EntityResponseType> {
    return this.http.post<ITCCONCEPTO>(this.resourceUrl, tCCONCEPTO, { observe: 'response' });
  }

  update(tCCONCEPTO: ITCCONCEPTO): Observable<EntityResponseType> {
    return this.http.put<ITCCONCEPTO>(`${this.resourceUrl}/${getTCCONCEPTOIdentifier(tCCONCEPTO) as number}`, tCCONCEPTO, {
      observe: 'response',
    });
  }

  partialUpdate(tCCONCEPTO: ITCCONCEPTO): Observable<EntityResponseType> {
    return this.http.patch<ITCCONCEPTO>(`${this.resourceUrl}/${getTCCONCEPTOIdentifier(tCCONCEPTO) as number}`, tCCONCEPTO, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITCCONCEPTO>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITCCONCEPTO[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addTCCONCEPTOToCollectionIfMissing(
    tCCONCEPTOCollection: ITCCONCEPTO[],
    ...tCCONCEPTOSToCheck: (ITCCONCEPTO | null | undefined)[]
  ): ITCCONCEPTO[] {
    const tCCONCEPTOS: ITCCONCEPTO[] = tCCONCEPTOSToCheck.filter(isPresent);
    if (tCCONCEPTOS.length > 0) {
      const tCCONCEPTOCollectionIdentifiers = tCCONCEPTOCollection.map(tCCONCEPTOItem => getTCCONCEPTOIdentifier(tCCONCEPTOItem)!);
      const tCCONCEPTOSToAdd = tCCONCEPTOS.filter(tCCONCEPTOItem => {
        const tCCONCEPTOIdentifier = getTCCONCEPTOIdentifier(tCCONCEPTOItem);
        if (tCCONCEPTOIdentifier == null || tCCONCEPTOCollectionIdentifiers.includes(tCCONCEPTOIdentifier)) {
          return false;
        }
        tCCONCEPTOCollectionIdentifiers.push(tCCONCEPTOIdentifier);
        return true;
      });
      return [...tCCONCEPTOSToAdd, ...tCCONCEPTOCollection];
    }
    return tCCONCEPTOCollection;
  }
}
