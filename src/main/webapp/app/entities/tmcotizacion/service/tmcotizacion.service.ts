import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as dayjs from 'dayjs';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITMCOTIZACION, getTMCOTIZACIONIdentifier } from '../tmcotizacion.model';

export type EntityResponseType = HttpResponse<ITMCOTIZACION>;
export type EntityArrayResponseType = HttpResponse<ITMCOTIZACION[]>;

@Injectable({ providedIn: 'root' })
export class TMCOTIZACIONService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/tmcotizacions');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(tMCOTIZACION: ITMCOTIZACION): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tMCOTIZACION);
    return this.http
      .post<ITMCOTIZACION>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tMCOTIZACION: ITMCOTIZACION): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tMCOTIZACION);
    return this.http
      .put<ITMCOTIZACION>(`${this.resourceUrl}/${getTMCOTIZACIONIdentifier(tMCOTIZACION) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(tMCOTIZACION: ITMCOTIZACION): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tMCOTIZACION);
    return this.http
      .patch<ITMCOTIZACION>(`${this.resourceUrl}/${getTMCOTIZACIONIdentifier(tMCOTIZACION) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITMCOTIZACION>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITMCOTIZACION[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addTMCOTIZACIONToCollectionIfMissing(
    tMCOTIZACIONCollection: ITMCOTIZACION[],
    ...tMCOTIZACIONSToCheck: (ITMCOTIZACION | null | undefined)[]
  ): ITMCOTIZACION[] {
    const tMCOTIZACIONS: ITMCOTIZACION[] = tMCOTIZACIONSToCheck.filter(isPresent);
    if (tMCOTIZACIONS.length > 0) {
      const tMCOTIZACIONCollectionIdentifiers = tMCOTIZACIONCollection.map(
        tMCOTIZACIONItem => getTMCOTIZACIONIdentifier(tMCOTIZACIONItem)!
      );
      const tMCOTIZACIONSToAdd = tMCOTIZACIONS.filter(tMCOTIZACIONItem => {
        const tMCOTIZACIONIdentifier = getTMCOTIZACIONIdentifier(tMCOTIZACIONItem);
        if (tMCOTIZACIONIdentifier == null || tMCOTIZACIONCollectionIdentifiers.includes(tMCOTIZACIONIdentifier)) {
          return false;
        }
        tMCOTIZACIONCollectionIdentifiers.push(tMCOTIZACIONIdentifier);
        return true;
      });
      return [...tMCOTIZACIONSToAdd, ...tMCOTIZACIONCollection];
    }
    return tMCOTIZACIONCollection;
  }

  protected convertDateFromClient(tMCOTIZACION: ITMCOTIZACION): ITMCOTIZACION {
    return Object.assign({}, tMCOTIZACION, {
      fechaRegistro: tMCOTIZACION.fechaRegistro?.isValid() ? tMCOTIZACION.fechaRegistro.format(DATE_FORMAT) : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.fechaRegistro = res.body.fechaRegistro ? dayjs(res.body.fechaRegistro) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((tMCOTIZACION: ITMCOTIZACION) => {
        tMCOTIZACION.fechaRegistro = tMCOTIZACION.fechaRegistro ? dayjs(tMCOTIZACION.fechaRegistro) : undefined;
      });
    }
    return res;
  }
}
