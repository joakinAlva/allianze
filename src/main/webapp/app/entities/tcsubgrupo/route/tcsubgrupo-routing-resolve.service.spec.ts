jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ITCSUBGRUPO, TCSUBGRUPO } from '../tcsubgrupo.model';
import { TCSUBGRUPOService } from '../service/tcsubgrupo.service';

import { TCSUBGRUPORoutingResolveService } from './tcsubgrupo-routing-resolve.service';

describe('Service Tests', () => {
  describe('TCSUBGRUPO routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: TCSUBGRUPORoutingResolveService;
    let service: TCSUBGRUPOService;
    let resultTCSUBGRUPO: ITCSUBGRUPO | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(TCSUBGRUPORoutingResolveService);
      service = TestBed.inject(TCSUBGRUPOService);
      resultTCSUBGRUPO = undefined;
    });

    describe('resolve', () => {
      it('should return ITCSUBGRUPO returned by find', () => {
        // GIVEN
        service.find = jest.fn(idSubGrupo => of(new HttpResponse({ body: { idSubGrupo } })));
        mockActivatedRouteSnapshot.params = { idSubGrupo: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCSUBGRUPO = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCSUBGRUPO).toEqual({ idSubGrupo: 123 });
      });

      it('should return new ITCSUBGRUPO if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCSUBGRUPO = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultTCSUBGRUPO).toEqual(new TCSUBGRUPO());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { idSubGrupo: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCSUBGRUPO = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCSUBGRUPO).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
