import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITCEJECUTIVO, getTCEJECUTIVOIdentifier } from '../tcejecutivo.model';

export type EntityResponseType = HttpResponse<ITCEJECUTIVO>;
export type EntityArrayResponseType = HttpResponse<ITCEJECUTIVO[]>;

@Injectable({ providedIn: 'root' })
export class TCEJECUTIVOService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/tcejecutivos');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(tCEJECUTIVO: ITCEJECUTIVO): Observable<EntityResponseType> {
    return this.http.post<ITCEJECUTIVO>(this.resourceUrl, tCEJECUTIVO, { observe: 'response' });
  }

  update(tCEJECUTIVO: ITCEJECUTIVO): Observable<EntityResponseType> {
    return this.http.put<ITCEJECUTIVO>(`${this.resourceUrl}/${getTCEJECUTIVOIdentifier(tCEJECUTIVO) as number}`, tCEJECUTIVO, {
      observe: 'response',
    });
  }

  partialUpdate(tCEJECUTIVO: ITCEJECUTIVO): Observable<EntityResponseType> {
    return this.http.patch<ITCEJECUTIVO>(`${this.resourceUrl}/${getTCEJECUTIVOIdentifier(tCEJECUTIVO) as number}`, tCEJECUTIVO, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITCEJECUTIVO>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITCEJECUTIVO[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addTCEJECUTIVOToCollectionIfMissing(
    tCEJECUTIVOCollection: ITCEJECUTIVO[],
    ...tCEJECUTIVOSToCheck: (ITCEJECUTIVO | null | undefined)[]
  ): ITCEJECUTIVO[] {
    const tCEJECUTIVOS: ITCEJECUTIVO[] = tCEJECUTIVOSToCheck.filter(isPresent);
    if (tCEJECUTIVOS.length > 0) {
      const tCEJECUTIVOCollectionIdentifiers = tCEJECUTIVOCollection.map(tCEJECUTIVOItem => getTCEJECUTIVOIdentifier(tCEJECUTIVOItem)!);
      const tCEJECUTIVOSToAdd = tCEJECUTIVOS.filter(tCEJECUTIVOItem => {
        const tCEJECUTIVOIdentifier = getTCEJECUTIVOIdentifier(tCEJECUTIVOItem);
        if (tCEJECUTIVOIdentifier == null || tCEJECUTIVOCollectionIdentifiers.includes(tCEJECUTIVOIdentifier)) {
          return false;
        }
        tCEJECUTIVOCollectionIdentifiers.push(tCEJECUTIVOIdentifier);
        return true;
      });
      return [...tCEJECUTIVOSToAdd, ...tCEJECUTIVOCollection];
    }
    return tCEJECUTIVOCollection;
  }
}
