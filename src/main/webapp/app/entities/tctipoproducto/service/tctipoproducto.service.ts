import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as dayjs from 'dayjs';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITCTIPOPRODUCTO, getTCTIPOPRODUCTOIdentifier } from '../tctipoproducto.model';

export type EntityResponseType = HttpResponse<ITCTIPOPRODUCTO>;
export type EntityArrayResponseType = HttpResponse<ITCTIPOPRODUCTO[]>;

@Injectable({ providedIn: 'root' })
export class TCTIPOPRODUCTOService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/tctipoproductos');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(tCTIPOPRODUCTO: ITCTIPOPRODUCTO): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tCTIPOPRODUCTO);
    return this.http
      .post<ITCTIPOPRODUCTO>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tCTIPOPRODUCTO: ITCTIPOPRODUCTO): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tCTIPOPRODUCTO);
    return this.http
      .put<ITCTIPOPRODUCTO>(`${this.resourceUrl}/${getTCTIPOPRODUCTOIdentifier(tCTIPOPRODUCTO) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(tCTIPOPRODUCTO: ITCTIPOPRODUCTO): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tCTIPOPRODUCTO);
    return this.http
      .patch<ITCTIPOPRODUCTO>(`${this.resourceUrl}/${getTCTIPOPRODUCTOIdentifier(tCTIPOPRODUCTO) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITCTIPOPRODUCTO>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITCTIPOPRODUCTO[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addTCTIPOPRODUCTOToCollectionIfMissing(
    tCTIPOPRODUCTOCollection: ITCTIPOPRODUCTO[],
    ...tCTIPOPRODUCTOSToCheck: (ITCTIPOPRODUCTO | null | undefined)[]
  ): ITCTIPOPRODUCTO[] {
    const tCTIPOPRODUCTOS: ITCTIPOPRODUCTO[] = tCTIPOPRODUCTOSToCheck.filter(isPresent);
    if (tCTIPOPRODUCTOS.length > 0) {
      const tCTIPOPRODUCTOCollectionIdentifiers = tCTIPOPRODUCTOCollection.map(
        tCTIPOPRODUCTOItem => getTCTIPOPRODUCTOIdentifier(tCTIPOPRODUCTOItem)!
      );
      const tCTIPOPRODUCTOSToAdd = tCTIPOPRODUCTOS.filter(tCTIPOPRODUCTOItem => {
        const tCTIPOPRODUCTOIdentifier = getTCTIPOPRODUCTOIdentifier(tCTIPOPRODUCTOItem);
        if (tCTIPOPRODUCTOIdentifier == null || tCTIPOPRODUCTOCollectionIdentifiers.includes(tCTIPOPRODUCTOIdentifier)) {
          return false;
        }
        tCTIPOPRODUCTOCollectionIdentifiers.push(tCTIPOPRODUCTOIdentifier);
        return true;
      });
      return [...tCTIPOPRODUCTOSToAdd, ...tCTIPOPRODUCTOCollection];
    }
    return tCTIPOPRODUCTOCollection;
  }

  protected convertDateFromClient(tCTIPOPRODUCTO: ITCTIPOPRODUCTO): ITCTIPOPRODUCTO {
    return Object.assign({}, tCTIPOPRODUCTO, {
      fecha: tCTIPOPRODUCTO.fecha?.isValid() ? tCTIPOPRODUCTO.fecha.format(DATE_FORMAT) : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.fecha = res.body.fecha ? dayjs(res.body.fecha) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((tCTIPOPRODUCTO: ITCTIPOPRODUCTO) => {
        tCTIPOPRODUCTO.fecha = tCTIPOPRODUCTO.fecha ? dayjs(tCTIPOPRODUCTO.fecha) : undefined;
      });
    }
    return res;
  }
}
