import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITCDESCUENTOSUMAASEGURADA, getTCDESCUENTOSUMAASEGURADAIdentifier } from '../tcdescuentosumaasegurada.model';

export type EntityResponseType = HttpResponse<ITCDESCUENTOSUMAASEGURADA>;
export type EntityArrayResponseType = HttpResponse<ITCDESCUENTOSUMAASEGURADA[]>;

@Injectable({ providedIn: 'root' })
export class TCDESCUENTOSUMAASEGURADAService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/tcdescuentosumaaseguradas');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(tCDESCUENTOSUMAASEGURADA: ITCDESCUENTOSUMAASEGURADA): Observable<EntityResponseType> {
    return this.http.post<ITCDESCUENTOSUMAASEGURADA>(this.resourceUrl, tCDESCUENTOSUMAASEGURADA, { observe: 'response' });
  }

  update(tCDESCUENTOSUMAASEGURADA: ITCDESCUENTOSUMAASEGURADA): Observable<EntityResponseType> {
    return this.http.put<ITCDESCUENTOSUMAASEGURADA>(
      `${this.resourceUrl}/${getTCDESCUENTOSUMAASEGURADAIdentifier(tCDESCUENTOSUMAASEGURADA) as number}`,
      tCDESCUENTOSUMAASEGURADA,
      { observe: 'response' }
    );
  }

  partialUpdate(tCDESCUENTOSUMAASEGURADA: ITCDESCUENTOSUMAASEGURADA): Observable<EntityResponseType> {
    return this.http.patch<ITCDESCUENTOSUMAASEGURADA>(
      `${this.resourceUrl}/${getTCDESCUENTOSUMAASEGURADAIdentifier(tCDESCUENTOSUMAASEGURADA) as number}`,
      tCDESCUENTOSUMAASEGURADA,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITCDESCUENTOSUMAASEGURADA>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITCDESCUENTOSUMAASEGURADA[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addTCDESCUENTOSUMAASEGURADAToCollectionIfMissing(
    tCDESCUENTOSUMAASEGURADACollection: ITCDESCUENTOSUMAASEGURADA[],
    ...tCDESCUENTOSUMAASEGURADASToCheck: (ITCDESCUENTOSUMAASEGURADA | null | undefined)[]
  ): ITCDESCUENTOSUMAASEGURADA[] {
    const tCDESCUENTOSUMAASEGURADAS: ITCDESCUENTOSUMAASEGURADA[] = tCDESCUENTOSUMAASEGURADASToCheck.filter(isPresent);
    if (tCDESCUENTOSUMAASEGURADAS.length > 0) {
      const tCDESCUENTOSUMAASEGURADACollectionIdentifiers = tCDESCUENTOSUMAASEGURADACollection.map(
        tCDESCUENTOSUMAASEGURADAItem => getTCDESCUENTOSUMAASEGURADAIdentifier(tCDESCUENTOSUMAASEGURADAItem)!
      );
      const tCDESCUENTOSUMAASEGURADASToAdd = tCDESCUENTOSUMAASEGURADAS.filter(tCDESCUENTOSUMAASEGURADAItem => {
        const tCDESCUENTOSUMAASEGURADAIdentifier = getTCDESCUENTOSUMAASEGURADAIdentifier(tCDESCUENTOSUMAASEGURADAItem);
        if (
          tCDESCUENTOSUMAASEGURADAIdentifier == null ||
          tCDESCUENTOSUMAASEGURADACollectionIdentifiers.includes(tCDESCUENTOSUMAASEGURADAIdentifier)
        ) {
          return false;
        }
        tCDESCUENTOSUMAASEGURADACollectionIdentifiers.push(tCDESCUENTOSUMAASEGURADAIdentifier);
        return true;
      });
      return [...tCDESCUENTOSUMAASEGURADASToAdd, ...tCDESCUENTOSUMAASEGURADACollection];
    }
    return tCDESCUENTOSUMAASEGURADACollection;
  }
}
