import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as dayjs from 'dayjs';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITMASEGURADO, getTMASEGURADOIdentifier } from '../tmasegurado.model';

export type EntityResponseType = HttpResponse<ITMASEGURADO>;
export type EntityArrayResponseType = HttpResponse<ITMASEGURADO[]>;

@Injectable({ providedIn: 'root' })
export class TMASEGURADOService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/tmasegurados');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(tMASEGURADO: ITMASEGURADO): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tMASEGURADO);
    return this.http
      .post<ITMASEGURADO>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tMASEGURADO: ITMASEGURADO): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tMASEGURADO);
    return this.http
      .put<ITMASEGURADO>(`${this.resourceUrl}/${getTMASEGURADOIdentifier(tMASEGURADO) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(tMASEGURADO: ITMASEGURADO): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tMASEGURADO);
    return this.http
      .patch<ITMASEGURADO>(`${this.resourceUrl}/${getTMASEGURADOIdentifier(tMASEGURADO) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITMASEGURADO>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITMASEGURADO[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addTMASEGURADOToCollectionIfMissing(
    tMASEGURADOCollection: ITMASEGURADO[],
    ...tMASEGURADOSToCheck: (ITMASEGURADO | null | undefined)[]
  ): ITMASEGURADO[] {
    const tMASEGURADOS: ITMASEGURADO[] = tMASEGURADOSToCheck.filter(isPresent);
    if (tMASEGURADOS.length > 0) {
      const tMASEGURADOCollectionIdentifiers = tMASEGURADOCollection.map(tMASEGURADOItem => getTMASEGURADOIdentifier(tMASEGURADOItem)!);
      const tMASEGURADOSToAdd = tMASEGURADOS.filter(tMASEGURADOItem => {
        const tMASEGURADOIdentifier = getTMASEGURADOIdentifier(tMASEGURADOItem);
        if (tMASEGURADOIdentifier == null || tMASEGURADOCollectionIdentifiers.includes(tMASEGURADOIdentifier)) {
          return false;
        }
        tMASEGURADOCollectionIdentifiers.push(tMASEGURADOIdentifier);
        return true;
      });
      return [...tMASEGURADOSToAdd, ...tMASEGURADOCollection];
    }
    return tMASEGURADOCollection;
  }

  protected convertDateFromClient(tMASEGURADO: ITMASEGURADO): ITMASEGURADO {
    return Object.assign({}, tMASEGURADO, {
      fNacimiento: tMASEGURADO.fNacimiento?.isValid() ? tMASEGURADO.fNacimiento.format(DATE_FORMAT) : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.fNacimiento = res.body.fNacimiento ? dayjs(res.body.fNacimiento) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((tMASEGURADO: ITMASEGURADO) => {
        tMASEGURADO.fNacimiento = tMASEGURADO.fNacimiento ? dayjs(tMASEGURADO.fNacimiento) : undefined;
      });
    }
    return res;
  }
}
