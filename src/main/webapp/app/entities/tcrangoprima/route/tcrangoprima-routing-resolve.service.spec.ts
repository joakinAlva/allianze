jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ITCRANGOPRIMA, TCRANGOPRIMA } from '../tcrangoprima.model';
import { TCRANGOPRIMAService } from '../service/tcrangoprima.service';

import { TCRANGOPRIMARoutingResolveService } from './tcrangoprima-routing-resolve.service';

describe('Service Tests', () => {
  describe('TCRANGOPRIMA routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: TCRANGOPRIMARoutingResolveService;
    let service: TCRANGOPRIMAService;
    let resultTCRANGOPRIMA: ITCRANGOPRIMA | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(TCRANGOPRIMARoutingResolveService);
      service = TestBed.inject(TCRANGOPRIMAService);
      resultTCRANGOPRIMA = undefined;
    });

    describe('resolve', () => {
      it('should return ITCRANGOPRIMA returned by find', () => {
        // GIVEN
        service.find = jest.fn(idRangoPrima => of(new HttpResponse({ body: { idRangoPrima } })));
        mockActivatedRouteSnapshot.params = { idRangoPrima: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCRANGOPRIMA = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCRANGOPRIMA).toEqual({ idRangoPrima: 123 });
      });

      it('should return new ITCRANGOPRIMA if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCRANGOPRIMA = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultTCRANGOPRIMA).toEqual(new TCRANGOPRIMA());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { idRangoPrima: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCRANGOPRIMA = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCRANGOPRIMA).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
