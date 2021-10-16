jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ITCCOVIDTARIFAS, TCCOVIDTARIFAS } from '../tccovidtarifas.model';
import { TCCOVIDTARIFASService } from '../service/tccovidtarifas.service';

import { TCCOVIDTARIFASRoutingResolveService } from './tccovidtarifas-routing-resolve.service';

describe('Service Tests', () => {
  describe('TCCOVIDTARIFAS routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: TCCOVIDTARIFASRoutingResolveService;
    let service: TCCOVIDTARIFASService;
    let resultTCCOVIDTARIFAS: ITCCOVIDTARIFAS | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(TCCOVIDTARIFASRoutingResolveService);
      service = TestBed.inject(TCCOVIDTARIFASService);
      resultTCCOVIDTARIFAS = undefined;
    });

    describe('resolve', () => {
      it('should return ITCCOVIDTARIFAS returned by find', () => {
        // GIVEN
        service.find = jest.fn(idCovidTarifas => of(new HttpResponse({ body: { idCovidTarifas } })));
        mockActivatedRouteSnapshot.params = { idCovidTarifas: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCCOVIDTARIFAS = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCCOVIDTARIFAS).toEqual({ idCovidTarifas: 123 });
      });

      it('should return new ITCCOVIDTARIFAS if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCCOVIDTARIFAS = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultTCCOVIDTARIFAS).toEqual(new TCCOVIDTARIFAS());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { idCovidTarifas: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCCOVIDTARIFAS = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCCOVIDTARIFAS).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
