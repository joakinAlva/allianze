jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ITMCOTIZACION, TMCOTIZACION } from '../tmcotizacion.model';
import { TMCOTIZACIONService } from '../service/tmcotizacion.service';

import { TMCOTIZACIONRoutingResolveService } from './tmcotizacion-routing-resolve.service';

describe('Service Tests', () => {
  describe('TMCOTIZACION routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: TMCOTIZACIONRoutingResolveService;
    let service: TMCOTIZACIONService;
    let resultTMCOTIZACION: ITMCOTIZACION | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(TMCOTIZACIONRoutingResolveService);
      service = TestBed.inject(TMCOTIZACIONService);
      resultTMCOTIZACION = undefined;
    });

    describe('resolve', () => {
      it('should return ITMCOTIZACION returned by find', () => {
        // GIVEN
        service.find = jest.fn(idCotizacion => of(new HttpResponse({ body: { idCotizacion } })));
        mockActivatedRouteSnapshot.params = { idCotizacion: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTMCOTIZACION = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTMCOTIZACION).toEqual({ idCotizacion: 123 });
      });

      it('should return new ITMCOTIZACION if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTMCOTIZACION = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultTMCOTIZACION).toEqual(new TMCOTIZACION());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { idCotizacion: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTMCOTIZACION = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTMCOTIZACION).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
