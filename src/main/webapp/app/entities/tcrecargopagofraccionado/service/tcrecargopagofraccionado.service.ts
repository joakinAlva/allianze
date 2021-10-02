import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITCRECARGOPAGOFRACCIONADO, getTCRECARGOPAGOFRACCIONADOIdentifier } from '../tcrecargopagofraccionado.model';

export type EntityResponseType = HttpResponse<ITCRECARGOPAGOFRACCIONADO>;
export type EntityArrayResponseType = HttpResponse<ITCRECARGOPAGOFRACCIONADO[]>;

@Injectable({ providedIn: 'root' })
export class TCRECARGOPAGOFRACCIONADOService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/tcrecargopagofraccionados');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(tCRECARGOPAGOFRACCIONADO: ITCRECARGOPAGOFRACCIONADO): Observable<EntityResponseType> {
    return this.http.post<ITCRECARGOPAGOFRACCIONADO>(this.resourceUrl, tCRECARGOPAGOFRACCIONADO, { observe: 'response' });
  }

  update(tCRECARGOPAGOFRACCIONADO: ITCRECARGOPAGOFRACCIONADO): Observable<EntityResponseType> {
    return this.http.put<ITCRECARGOPAGOFRACCIONADO>(
      `${this.resourceUrl}/${getTCRECARGOPAGOFRACCIONADOIdentifier(tCRECARGOPAGOFRACCIONADO) as number}`,
      tCRECARGOPAGOFRACCIONADO,
      { observe: 'response' }
    );
  }

  partialUpdate(tCRECARGOPAGOFRACCIONADO: ITCRECARGOPAGOFRACCIONADO): Observable<EntityResponseType> {
    return this.http.patch<ITCRECARGOPAGOFRACCIONADO>(
      `${this.resourceUrl}/${getTCRECARGOPAGOFRACCIONADOIdentifier(tCRECARGOPAGOFRACCIONADO) as number}`,
      tCRECARGOPAGOFRACCIONADO,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITCRECARGOPAGOFRACCIONADO>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITCRECARGOPAGOFRACCIONADO[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addTCRECARGOPAGOFRACCIONADOToCollectionIfMissing(
    tCRECARGOPAGOFRACCIONADOCollection: ITCRECARGOPAGOFRACCIONADO[],
    ...tCRECARGOPAGOFRACCIONADOSToCheck: (ITCRECARGOPAGOFRACCIONADO | null | undefined)[]
  ): ITCRECARGOPAGOFRACCIONADO[] {
    const tCRECARGOPAGOFRACCIONADOS: ITCRECARGOPAGOFRACCIONADO[] = tCRECARGOPAGOFRACCIONADOSToCheck.filter(isPresent);
    if (tCRECARGOPAGOFRACCIONADOS.length > 0) {
      const tCRECARGOPAGOFRACCIONADOCollectionIdentifiers = tCRECARGOPAGOFRACCIONADOCollection.map(
        tCRECARGOPAGOFRACCIONADOItem => getTCRECARGOPAGOFRACCIONADOIdentifier(tCRECARGOPAGOFRACCIONADOItem)!
      );
      const tCRECARGOPAGOFRACCIONADOSToAdd = tCRECARGOPAGOFRACCIONADOS.filter(tCRECARGOPAGOFRACCIONADOItem => {
        const tCRECARGOPAGOFRACCIONADOIdentifier = getTCRECARGOPAGOFRACCIONADOIdentifier(tCRECARGOPAGOFRACCIONADOItem);
        if (
          tCRECARGOPAGOFRACCIONADOIdentifier == null ||
          tCRECARGOPAGOFRACCIONADOCollectionIdentifiers.includes(tCRECARGOPAGOFRACCIONADOIdentifier)
        ) {
          return false;
        }
        tCRECARGOPAGOFRACCIONADOCollectionIdentifiers.push(tCRECARGOPAGOFRACCIONADOIdentifier);
        return true;
      });
      return [...tCRECARGOPAGOFRACCIONADOSToAdd, ...tCRECARGOPAGOFRACCIONADOCollection];
    }
    return tCRECARGOPAGOFRACCIONADOCollection;
  }
}
