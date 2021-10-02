import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITCTIPOREGLA, getTCTIPOREGLAIdentifier } from '../tctiporegla.model';

export type EntityResponseType = HttpResponse<ITCTIPOREGLA>;
export type EntityArrayResponseType = HttpResponse<ITCTIPOREGLA[]>;

@Injectable({ providedIn: 'root' })
export class TCTIPOREGLAService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/tctiporeglas');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(tCTIPOREGLA: ITCTIPOREGLA): Observable<EntityResponseType> {
    return this.http.post<ITCTIPOREGLA>(this.resourceUrl, tCTIPOREGLA, { observe: 'response' });
  }

  update(tCTIPOREGLA: ITCTIPOREGLA): Observable<EntityResponseType> {
    return this.http.put<ITCTIPOREGLA>(`${this.resourceUrl}/${getTCTIPOREGLAIdentifier(tCTIPOREGLA) as number}`, tCTIPOREGLA, {
      observe: 'response',
    });
  }

  partialUpdate(tCTIPOREGLA: ITCTIPOREGLA): Observable<EntityResponseType> {
    return this.http.patch<ITCTIPOREGLA>(`${this.resourceUrl}/${getTCTIPOREGLAIdentifier(tCTIPOREGLA) as number}`, tCTIPOREGLA, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITCTIPOREGLA>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITCTIPOREGLA[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addTCTIPOREGLAToCollectionIfMissing(
    tCTIPOREGLACollection: ITCTIPOREGLA[],
    ...tCTIPOREGLASToCheck: (ITCTIPOREGLA | null | undefined)[]
  ): ITCTIPOREGLA[] {
    const tCTIPOREGLAS: ITCTIPOREGLA[] = tCTIPOREGLASToCheck.filter(isPresent);
    if (tCTIPOREGLAS.length > 0) {
      const tCTIPOREGLACollectionIdentifiers = tCTIPOREGLACollection.map(tCTIPOREGLAItem => getTCTIPOREGLAIdentifier(tCTIPOREGLAItem)!);
      const tCTIPOREGLASToAdd = tCTIPOREGLAS.filter(tCTIPOREGLAItem => {
        const tCTIPOREGLAIdentifier = getTCTIPOREGLAIdentifier(tCTIPOREGLAItem);
        if (tCTIPOREGLAIdentifier == null || tCTIPOREGLACollectionIdentifiers.includes(tCTIPOREGLAIdentifier)) {
          return false;
        }
        tCTIPOREGLACollectionIdentifiers.push(tCTIPOREGLAIdentifier);
        return true;
      });
      return [...tCTIPOREGLASToAdd, ...tCTIPOREGLACollection];
    }
    return tCTIPOREGLACollection;
  }
}
