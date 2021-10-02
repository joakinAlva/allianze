jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ITCCUOTATARIFASDESC, TCCUOTATARIFASDESC } from '../tccuotatarifasdesc.model';
import { TCCUOTATARIFASDESCService } from '../service/tccuotatarifasdesc.service';

import { TCCUOTATARIFASDESCRoutingResolveService } from './tccuotatarifasdesc-routing-resolve.service';

describe('Service Tests', () => {
  describe('TCCUOTATARIFASDESC routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: TCCUOTATARIFASDESCRoutingResolveService;
    let service: TCCUOTATARIFASDESCService;
    let resultTCCUOTATARIFASDESC: ITCCUOTATARIFASDESC | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(TCCUOTATARIFASDESCRoutingResolveService);
      service = TestBed.inject(TCCUOTATARIFASDESCService);
      resultTCCUOTATARIFASDESC = undefined;
    });

    describe('resolve', () => {
      it('should return ITCCUOTATARIFASDESC returned by find', () => {
        // GIVEN
        service.find = jest.fn(idCuotaTarifaSdesc => of(new HttpResponse({ body: { idCuotaTarifaSdesc } })));
        mockActivatedRouteSnapshot.params = { idCuotaTarifaSdesc: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCCUOTATARIFASDESC = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCCUOTATARIFASDESC).toEqual({ idCuotaTarifaSdesc: 123 });
      });

      it('should return new ITCCUOTATARIFASDESC if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCCUOTATARIFASDESC = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultTCCUOTATARIFASDESC).toEqual(new TCCUOTATARIFASDESC());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { idCuotaTarifaSdesc: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCCUOTATARIFASDESC = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCCUOTATARIFASDESC).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
