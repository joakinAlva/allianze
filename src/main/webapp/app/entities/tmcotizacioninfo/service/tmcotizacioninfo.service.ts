import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as dayjs from 'dayjs';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITMCOTIZACIONINFO, getTMCOTIZACIONINFOIdentifier } from '../tmcotizacioninfo.model';

export type EntityResponseType = HttpResponse<ITMCOTIZACIONINFO>;
export type EntityArrayResponseType = HttpResponse<ITMCOTIZACIONINFO[]>;

@Injectable({ providedIn: 'root' })
export class TMCOTIZACIONINFOService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/tmcotizacioninfos');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(tMCOTIZACIONINFO: ITMCOTIZACIONINFO): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tMCOTIZACIONINFO);
    return this.http
      .post<ITMCOTIZACIONINFO>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tMCOTIZACIONINFO: ITMCOTIZACIONINFO): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tMCOTIZACIONINFO);
    return this.http
      .put<ITMCOTIZACIONINFO>(`${this.resourceUrl}/${getTMCOTIZACIONINFOIdentifier(tMCOTIZACIONINFO) as number}`, copy, {
        observe: 'response',
      })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(tMCOTIZACIONINFO: ITMCOTIZACIONINFO): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tMCOTIZACIONINFO);
    return this.http
      .patch<ITMCOTIZACIONINFO>(`${this.resourceUrl}/${getTMCOTIZACIONINFOIdentifier(tMCOTIZACIONINFO) as number}`, copy, {
        observe: 'response',
      })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITMCOTIZACIONINFO>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITMCOTIZACIONINFO[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addTMCOTIZACIONINFOToCollectionIfMissing(
    tMCOTIZACIONINFOCollection: ITMCOTIZACIONINFO[],
    ...tMCOTIZACIONINFOSToCheck: (ITMCOTIZACIONINFO | null | undefined)[]
  ): ITMCOTIZACIONINFO[] {
    const tMCOTIZACIONINFOS: ITMCOTIZACIONINFO[] = tMCOTIZACIONINFOSToCheck.filter(isPresent);
    if (tMCOTIZACIONINFOS.length > 0) {
      const tMCOTIZACIONINFOCollectionIdentifiers = tMCOTIZACIONINFOCollection.map(
        tMCOTIZACIONINFOItem => getTMCOTIZACIONINFOIdentifier(tMCOTIZACIONINFOItem)!
      );
      const tMCOTIZACIONINFOSToAdd = tMCOTIZACIONINFOS.filter(tMCOTIZACIONINFOItem => {
        const tMCOTIZACIONINFOIdentifier = getTMCOTIZACIONINFOIdentifier(tMCOTIZACIONINFOItem);
        if (tMCOTIZACIONINFOIdentifier == null || tMCOTIZACIONINFOCollectionIdentifiers.includes(tMCOTIZACIONINFOIdentifier)) {
          return false;
        }
        tMCOTIZACIONINFOCollectionIdentifiers.push(tMCOTIZACIONINFOIdentifier);
        return true;
      });
      return [...tMCOTIZACIONINFOSToAdd, ...tMCOTIZACIONINFOCollection];
    }
    return tMCOTIZACIONINFOCollection;
  }

  protected convertDateFromClient(tMCOTIZACIONINFO: ITMCOTIZACIONINFO): ITMCOTIZACIONINFO {
    return Object.assign({}, tMCOTIZACIONINFO, {
      fechaSolicitud: tMCOTIZACIONINFO.fechaSolicitud?.isValid() ? tMCOTIZACIONINFO.fechaSolicitud.format(DATE_FORMAT) : undefined,
      fechaEntrega: tMCOTIZACIONINFO.fechaEntrega?.isValid() ? tMCOTIZACIONINFO.fechaEntrega.format(DATE_FORMAT) : undefined,
      inicioVigencia: tMCOTIZACIONINFO.inicioVigencia?.isValid() ? tMCOTIZACIONINFO.inicioVigencia.format(DATE_FORMAT) : undefined,
      finVigencia: tMCOTIZACIONINFO.finVigencia?.isValid() ? tMCOTIZACIONINFO.finVigencia.format(DATE_FORMAT) : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.fechaSolicitud = res.body.fechaSolicitud ? dayjs(res.body.fechaSolicitud) : undefined;
      res.body.fechaEntrega = res.body.fechaEntrega ? dayjs(res.body.fechaEntrega) : undefined;
      res.body.inicioVigencia = res.body.inicioVigencia ? dayjs(res.body.inicioVigencia) : undefined;
      res.body.finVigencia = res.body.finVigencia ? dayjs(res.body.finVigencia) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((tMCOTIZACIONINFO: ITMCOTIZACIONINFO) => {
        tMCOTIZACIONINFO.fechaSolicitud = tMCOTIZACIONINFO.fechaSolicitud ? dayjs(tMCOTIZACIONINFO.fechaSolicitud) : undefined;
        tMCOTIZACIONINFO.fechaEntrega = tMCOTIZACIONINFO.fechaEntrega ? dayjs(tMCOTIZACIONINFO.fechaEntrega) : undefined;
        tMCOTIZACIONINFO.inicioVigencia = tMCOTIZACIONINFO.inicioVigencia ? dayjs(tMCOTIZACIONINFO.inicioVigencia) : undefined;
        tMCOTIZACIONINFO.finVigencia = tMCOTIZACIONINFO.finVigencia ? dayjs(tMCOTIZACIONINFO.finVigencia) : undefined;
      });
    }
    return res;
  }
}
