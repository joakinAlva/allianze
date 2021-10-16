import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITMCOTIZACIONEXPPROPIA, getTMCOTIZACIONEXPPROPIAIdentifier } from '../tmcotizacionexppropia.model';

export type EntityResponseType = HttpResponse<ITMCOTIZACIONEXPPROPIA>;
export type EntityArrayResponseType = HttpResponse<ITMCOTIZACIONEXPPROPIA[]>;

@Injectable({ providedIn: 'root' })
export class TMCOTIZACIONEXPPROPIAService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/tmcotizacionexppropias');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(tMCOTIZACIONEXPPROPIA: ITMCOTIZACIONEXPPROPIA): Observable<EntityResponseType> {
    return this.http.post<ITMCOTIZACIONEXPPROPIA>(this.resourceUrl, tMCOTIZACIONEXPPROPIA, { observe: 'response' });
  }

  update(tMCOTIZACIONEXPPROPIA: ITMCOTIZACIONEXPPROPIA): Observable<EntityResponseType> {
    return this.http.put<ITMCOTIZACIONEXPPROPIA>(
      `${this.resourceUrl}/${getTMCOTIZACIONEXPPROPIAIdentifier(tMCOTIZACIONEXPPROPIA) as number}`,
      tMCOTIZACIONEXPPROPIA,
      { observe: 'response' }
    );
  }

  partialUpdate(tMCOTIZACIONEXPPROPIA: ITMCOTIZACIONEXPPROPIA): Observable<EntityResponseType> {
    return this.http.patch<ITMCOTIZACIONEXPPROPIA>(
      `${this.resourceUrl}/${getTMCOTIZACIONEXPPROPIAIdentifier(tMCOTIZACIONEXPPROPIA) as number}`,
      tMCOTIZACIONEXPPROPIA,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITMCOTIZACIONEXPPROPIA>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITMCOTIZACIONEXPPROPIA[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addTMCOTIZACIONEXPPROPIAToCollectionIfMissing(
    tMCOTIZACIONEXPPROPIACollection: ITMCOTIZACIONEXPPROPIA[],
    ...tMCOTIZACIONEXPPROPIASToCheck: (ITMCOTIZACIONEXPPROPIA | null | undefined)[]
  ): ITMCOTIZACIONEXPPROPIA[] {
    const tMCOTIZACIONEXPPROPIAS: ITMCOTIZACIONEXPPROPIA[] = tMCOTIZACIONEXPPROPIASToCheck.filter(isPresent);
    if (tMCOTIZACIONEXPPROPIAS.length > 0) {
      const tMCOTIZACIONEXPPROPIACollectionIdentifiers = tMCOTIZACIONEXPPROPIACollection.map(
        tMCOTIZACIONEXPPROPIAItem => getTMCOTIZACIONEXPPROPIAIdentifier(tMCOTIZACIONEXPPROPIAItem)!
      );
      const tMCOTIZACIONEXPPROPIASToAdd = tMCOTIZACIONEXPPROPIAS.filter(tMCOTIZACIONEXPPROPIAItem => {
        const tMCOTIZACIONEXPPROPIAIdentifier = getTMCOTIZACIONEXPPROPIAIdentifier(tMCOTIZACIONEXPPROPIAItem);
        if (
          tMCOTIZACIONEXPPROPIAIdentifier == null ||
          tMCOTIZACIONEXPPROPIACollectionIdentifiers.includes(tMCOTIZACIONEXPPROPIAIdentifier)
        ) {
          return false;
        }
        tMCOTIZACIONEXPPROPIACollectionIdentifiers.push(tMCOTIZACIONEXPPROPIAIdentifier);
        return true;
      });
      return [...tMCOTIZACIONEXPPROPIASToAdd, ...tMCOTIZACIONEXPPROPIACollection];
    }
    return tMCOTIZACIONEXPPROPIACollection;
  }
}
