import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITCCOVIDTARIFAS, getTCCOVIDTARIFASIdentifier } from '../tccovidtarifas.model';

export type EntityResponseType = HttpResponse<ITCCOVIDTARIFAS>;
export type EntityArrayResponseType = HttpResponse<ITCCOVIDTARIFAS[]>;

@Injectable({ providedIn: 'root' })
export class TCCOVIDTARIFASService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/tccovidtarifas');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(tCCOVIDTARIFAS: ITCCOVIDTARIFAS): Observable<EntityResponseType> {
    return this.http.post<ITCCOVIDTARIFAS>(this.resourceUrl, tCCOVIDTARIFAS, { observe: 'response' });
  }

  update(tCCOVIDTARIFAS: ITCCOVIDTARIFAS): Observable<EntityResponseType> {
    return this.http.put<ITCCOVIDTARIFAS>(`${this.resourceUrl}/${getTCCOVIDTARIFASIdentifier(tCCOVIDTARIFAS) as number}`, tCCOVIDTARIFAS, {
      observe: 'response',
    });
  }

  partialUpdate(tCCOVIDTARIFAS: ITCCOVIDTARIFAS): Observable<EntityResponseType> {
    return this.http.patch<ITCCOVIDTARIFAS>(
      `${this.resourceUrl}/${getTCCOVIDTARIFASIdentifier(tCCOVIDTARIFAS) as number}`,
      tCCOVIDTARIFAS,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITCCOVIDTARIFAS>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITCCOVIDTARIFAS[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addTCCOVIDTARIFASToCollectionIfMissing(
    tCCOVIDTARIFASCollection: ITCCOVIDTARIFAS[],
    ...tCCOVIDTARIFASToCheck: (ITCCOVIDTARIFAS | null | undefined)[]
  ): ITCCOVIDTARIFAS[] {
    const tCCOVIDTARIFAS: ITCCOVIDTARIFAS[] = tCCOVIDTARIFASToCheck.filter(isPresent);
    if (tCCOVIDTARIFAS.length > 0) {
      const tCCOVIDTARIFASCollectionIdentifiers = tCCOVIDTARIFASCollection.map(
        tCCOVIDTARIFASItem => getTCCOVIDTARIFASIdentifier(tCCOVIDTARIFASItem)!
      );
      const tCCOVIDTARIFASToAdd = tCCOVIDTARIFAS.filter(tCCOVIDTARIFASItem => {
        const tCCOVIDTARIFASIdentifier = getTCCOVIDTARIFASIdentifier(tCCOVIDTARIFASItem);
        if (tCCOVIDTARIFASIdentifier == null || tCCOVIDTARIFASCollectionIdentifiers.includes(tCCOVIDTARIFASIdentifier)) {
          return false;
        }
        tCCOVIDTARIFASCollectionIdentifiers.push(tCCOVIDTARIFASIdentifier);
        return true;
      });
      return [...tCCOVIDTARIFASToAdd, ...tCCOVIDTARIFASCollection];
    }
    return tCCOVIDTARIFASCollection;
  }
}
