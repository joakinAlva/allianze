jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ITCCOEFICIENTE, TCCOEFICIENTE } from '../tccoeficiente.model';
import { TCCOEFICIENTEService } from '../service/tccoeficiente.service';

import { TCCOEFICIENTERoutingResolveService } from './tccoeficiente-routing-resolve.service';

describe('Service Tests', () => {
  describe('TCCOEFICIENTE routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: TCCOEFICIENTERoutingResolveService;
    let service: TCCOEFICIENTEService;
    let resultTCCOEFICIENTE: ITCCOEFICIENTE | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(TCCOEFICIENTERoutingResolveService);
      service = TestBed.inject(TCCOEFICIENTEService);
      resultTCCOEFICIENTE = undefined;
    });

    describe('resolve', () => {
      it('should return ITCCOEFICIENTE returned by find', () => {
        // GIVEN
        service.find = jest.fn(idCoeficiente => of(new HttpResponse({ body: { idCoeficiente } })));
        mockActivatedRouteSnapshot.params = { idCoeficiente: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCCOEFICIENTE = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCCOEFICIENTE).toEqual({ idCoeficiente: 123 });
      });

      it('should return new ITCCOEFICIENTE if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCCOEFICIENTE = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultTCCOEFICIENTE).toEqual(new TCCOEFICIENTE());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { idCoeficiente: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCCOEFICIENTE = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCCOEFICIENTE).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
