jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ITCEJECUTIVO, TCEJECUTIVO } from '../tcejecutivo.model';
import { TCEJECUTIVOService } from '../service/tcejecutivo.service';

import { TCEJECUTIVORoutingResolveService } from './tcejecutivo-routing-resolve.service';

describe('Service Tests', () => {
  describe('TCEJECUTIVO routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: TCEJECUTIVORoutingResolveService;
    let service: TCEJECUTIVOService;
    let resultTCEJECUTIVO: ITCEJECUTIVO | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(TCEJECUTIVORoutingResolveService);
      service = TestBed.inject(TCEJECUTIVOService);
      resultTCEJECUTIVO = undefined;
    });

    describe('resolve', () => {
      it('should return ITCEJECUTIVO returned by find', () => {
        // GIVEN
        service.find = jest.fn(idEjecutivo => of(new HttpResponse({ body: { idEjecutivo } })));
        mockActivatedRouteSnapshot.params = { idEjecutivo: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCEJECUTIVO = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCEJECUTIVO).toEqual({ idEjecutivo: 123 });
      });

      it('should return new ITCEJECUTIVO if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCEJECUTIVO = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultTCEJECUTIVO).toEqual(new TCEJECUTIVO());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { idEjecutivo: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCEJECUTIVO = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCEJECUTIVO).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
