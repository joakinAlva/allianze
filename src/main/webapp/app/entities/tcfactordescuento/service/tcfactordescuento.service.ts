import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITCFACTORDESCUENTO, getTCFACTORDESCUENTOIdentifier } from '../tcfactordescuento.model';

export type EntityResponseType = HttpResponse<ITCFACTORDESCUENTO>;
export type EntityArrayResponseType = HttpResponse<ITCFACTORDESCUENTO[]>;

@Injectable({ providedIn: 'root' })
export class TCFACTORDESCUENTOService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/tcfactordescuentos');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(tCFACTORDESCUENTO: ITCFACTORDESCUENTO): Observable<EntityResponseType> {
    return this.http.post<ITCFACTORDESCUENTO>(this.resourceUrl, tCFACTORDESCUENTO, { observe: 'response' });
  }

  update(tCFACTORDESCUENTO: ITCFACTORDESCUENTO): Observable<EntityResponseType> {
    return this.http.put<ITCFACTORDESCUENTO>(
      `${this.resourceUrl}/${getTCFACTORDESCUENTOIdentifier(tCFACTORDESCUENTO) as number}`,
      tCFACTORDESCUENTO,
      { observe: 'response' }
    );
  }

  partialUpdate(tCFACTORDESCUENTO: ITCFACTORDESCUENTO): Observable<EntityResponseType> {
    return this.http.patch<ITCFACTORDESCUENTO>(
      `${this.resourceUrl}/${getTCFACTORDESCUENTOIdentifier(tCFACTORDESCUENTO) as number}`,
      tCFACTORDESCUENTO,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITCFACTORDESCUENTO>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITCFACTORDESCUENTO[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addTCFACTORDESCUENTOToCollectionIfMissing(
    tCFACTORDESCUENTOCollection: ITCFACTORDESCUENTO[],
    ...tCFACTORDESCUENTOSToCheck: (ITCFACTORDESCUENTO | null | undefined)[]
  ): ITCFACTORDESCUENTO[] {
    const tCFACTORDESCUENTOS: ITCFACTORDESCUENTO[] = tCFACTORDESCUENTOSToCheck.filter(isPresent);
    if (tCFACTORDESCUENTOS.length > 0) {
      const tCFACTORDESCUENTOCollectionIdentifiers = tCFACTORDESCUENTOCollection.map(
        tCFACTORDESCUENTOItem => getTCFACTORDESCUENTOIdentifier(tCFACTORDESCUENTOItem)!
      );
      const tCFACTORDESCUENTOSToAdd = tCFACTORDESCUENTOS.filter(tCFACTORDESCUENTOItem => {
        const tCFACTORDESCUENTOIdentifier = getTCFACTORDESCUENTOIdentifier(tCFACTORDESCUENTOItem);
        if (tCFACTORDESCUENTOIdentifier == null || tCFACTORDESCUENTOCollectionIdentifiers.includes(tCFACTORDESCUENTOIdentifier)) {
          return false;
        }
        tCFACTORDESCUENTOCollectionIdentifiers.push(tCFACTORDESCUENTOIdentifier);
        return true;
      });
      return [...tCFACTORDESCUENTOSToAdd, ...tCFACTORDESCUENTOCollection];
    }
    return tCFACTORDESCUENTOCollection;
  }
}
