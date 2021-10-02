jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ITCCUOTARIESGO, TCCUOTARIESGO } from '../tccuotariesgo.model';
import { TCCUOTARIESGOService } from '../service/tccuotariesgo.service';

import { TCCUOTARIESGORoutingResolveService } from './tccuotariesgo-routing-resolve.service';

describe('Service Tests', () => {
  describe('TCCUOTARIESGO routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: TCCUOTARIESGORoutingResolveService;
    let service: TCCUOTARIESGOService;
    let resultTCCUOTARIESGO: ITCCUOTARIESGO | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(TCCUOTARIESGORoutingResolveService);
      service = TestBed.inject(TCCUOTARIESGOService);
      resultTCCUOTARIESGO = undefined;
    });

    describe('resolve', () => {
      it('should return ITCCUOTARIESGO returned by find', () => {
        // GIVEN
        service.find = jest.fn(idCuotaRiesgo => of(new HttpResponse({ body: { idCuotaRiesgo } })));
        mockActivatedRouteSnapshot.params = { idCuotaRiesgo: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCCUOTARIESGO = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCCUOTARIESGO).toEqual({ idCuotaRiesgo: 123 });
      });

      it('should return new ITCCUOTARIESGO if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCCUOTARIESGO = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultTCCUOTARIESGO).toEqual(new TCCUOTARIESGO());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { idCuotaRiesgo: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCCUOTARIESGO = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCCUOTARIESGO).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
