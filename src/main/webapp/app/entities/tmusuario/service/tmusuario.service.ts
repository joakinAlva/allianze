import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as dayjs from 'dayjs';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITMUSUARIO, getTMUSUARIOIdentifier } from '../tmusuario.model';

export type EntityResponseType = HttpResponse<ITMUSUARIO>;
export type EntityArrayResponseType = HttpResponse<ITMUSUARIO[]>;

@Injectable({ providedIn: 'root' })
export class TMUSUARIOService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/tmusuarios');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(tMUSUARIO: ITMUSUARIO): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tMUSUARIO);
    return this.http
      .post<ITMUSUARIO>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tMUSUARIO: ITMUSUARIO): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tMUSUARIO);
    return this.http
      .put<ITMUSUARIO>(`${this.resourceUrl}/${getTMUSUARIOIdentifier(tMUSUARIO) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(tMUSUARIO: ITMUSUARIO): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tMUSUARIO);
    return this.http
      .patch<ITMUSUARIO>(`${this.resourceUrl}/${getTMUSUARIOIdentifier(tMUSUARIO) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITMUSUARIO>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITMUSUARIO[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addTMUSUARIOToCollectionIfMissing(
    tMUSUARIOCollection: ITMUSUARIO[],
    ...tMUSUARIOSToCheck: (ITMUSUARIO | null | undefined)[]
  ): ITMUSUARIO[] {
    const tMUSUARIOS: ITMUSUARIO[] = tMUSUARIOSToCheck.filter(isPresent);
    if (tMUSUARIOS.length > 0) {
      const tMUSUARIOCollectionIdentifiers = tMUSUARIOCollection.map(tMUSUARIOItem => getTMUSUARIOIdentifier(tMUSUARIOItem)!);
      const tMUSUARIOSToAdd = tMUSUARIOS.filter(tMUSUARIOItem => {
        const tMUSUARIOIdentifier = getTMUSUARIOIdentifier(tMUSUARIOItem);
        if (tMUSUARIOIdentifier == null || tMUSUARIOCollectionIdentifiers.includes(tMUSUARIOIdentifier)) {
          return false;
        }
        tMUSUARIOCollectionIdentifiers.push(tMUSUARIOIdentifier);
        return true;
      });
      return [...tMUSUARIOSToAdd, ...tMUSUARIOCollection];
    }
    return tMUSUARIOCollection;
  }

  protected convertDateFromClient(tMUSUARIO: ITMUSUARIO): ITMUSUARIO {
    return Object.assign({}, tMUSUARIO, {
      fechaRegistro: tMUSUARIO.fechaRegistro?.isValid() ? tMUSUARIO.fechaRegistro.format(DATE_FORMAT) : undefined,
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
      res.body.forEach((tMUSUARIO: ITMUSUARIO) => {
        tMUSUARIO.fechaRegistro = tMUSUARIO.fechaRegistro ? dayjs(tMUSUARIO.fechaRegistro) : undefined;
      });
    }
    return res;
  }
}
