import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITCUNIDADNEGOCIO, getTCUNIDADNEGOCIOIdentifier } from '../tcunidadnegocio.model';

export type EntityResponseType = HttpResponse<ITCUNIDADNEGOCIO>;
export type EntityArrayResponseType = HttpResponse<ITCUNIDADNEGOCIO[]>;

@Injectable({ providedIn: 'root' })
export class TCUNIDADNEGOCIOService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/tcunidadnegocios');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(tCUNIDADNEGOCIO: ITCUNIDADNEGOCIO): Observable<EntityResponseType> {
    return this.http.post<ITCUNIDADNEGOCIO>(this.resourceUrl, tCUNIDADNEGOCIO, { observe: 'response' });
  }

  update(tCUNIDADNEGOCIO: ITCUNIDADNEGOCIO): Observable<EntityResponseType> {
    return this.http.put<ITCUNIDADNEGOCIO>(
      `${this.resourceUrl}/${getTCUNIDADNEGOCIOIdentifier(tCUNIDADNEGOCIO) as number}`,
      tCUNIDADNEGOCIO,
      { observe: 'response' }
    );
  }

  partialUpdate(tCUNIDADNEGOCIO: ITCUNIDADNEGOCIO): Observable<EntityResponseType> {
    return this.http.patch<ITCUNIDADNEGOCIO>(
      `${this.resourceUrl}/${getTCUNIDADNEGOCIOIdentifier(tCUNIDADNEGOCIO) as number}`,
      tCUNIDADNEGOCIO,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITCUNIDADNEGOCIO>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITCUNIDADNEGOCIO[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addTCUNIDADNEGOCIOToCollectionIfMissing(
    tCUNIDADNEGOCIOCollection: ITCUNIDADNEGOCIO[],
    ...tCUNIDADNEGOCIOSToCheck: (ITCUNIDADNEGOCIO | null | undefined)[]
  ): ITCUNIDADNEGOCIO[] {
    const tCUNIDADNEGOCIOS: ITCUNIDADNEGOCIO[] = tCUNIDADNEGOCIOSToCheck.filter(isPresent);
    if (tCUNIDADNEGOCIOS.length > 0) {
      const tCUNIDADNEGOCIOCollectionIdentifiers = tCUNIDADNEGOCIOCollection.map(
        tCUNIDADNEGOCIOItem => getTCUNIDADNEGOCIOIdentifier(tCUNIDADNEGOCIOItem)!
      );
      const tCUNIDADNEGOCIOSToAdd = tCUNIDADNEGOCIOS.filter(tCUNIDADNEGOCIOItem => {
        const tCUNIDADNEGOCIOIdentifier = getTCUNIDADNEGOCIOIdentifier(tCUNIDADNEGOCIOItem);
        if (tCUNIDADNEGOCIOIdentifier == null || tCUNIDADNEGOCIOCollectionIdentifiers.includes(tCUNIDADNEGOCIOIdentifier)) {
          return false;
        }
        tCUNIDADNEGOCIOCollectionIdentifiers.push(tCUNIDADNEGOCIOIdentifier);
        return true;
      });
      return [...tCUNIDADNEGOCIOSToAdd, ...tCUNIDADNEGOCIOCollection];
    }
    return tCUNIDADNEGOCIOCollection;
  }
}
