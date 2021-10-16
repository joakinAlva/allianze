jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ITMCOTIZACIONINFO, TMCOTIZACIONINFO } from '../tmcotizacioninfo.model';
import { TMCOTIZACIONINFOService } from '../service/tmcotizacioninfo.service';

import { TMCOTIZACIONINFORoutingResolveService } from './tmcotizacioninfo-routing-resolve.service';

describe('Service Tests', () => {
  describe('TMCOTIZACIONINFO routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: TMCOTIZACIONINFORoutingResolveService;
    let service: TMCOTIZACIONINFOService;
    let resultTMCOTIZACIONINFO: ITMCOTIZACIONINFO | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(TMCOTIZACIONINFORoutingResolveService);
      service = TestBed.inject(TMCOTIZACIONINFOService);
      resultTMCOTIZACIONINFO = undefined;
    });

    describe('resolve', () => {
      it('should return ITMCOTIZACIONINFO returned by find', () => {
        // GIVEN
        service.find = jest.fn(idCotizacionInfo => of(new HttpResponse({ body: { idCotizacionInfo } })));
        mockActivatedRouteSnapshot.params = { idCotizacionInfo: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTMCOTIZACIONINFO = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTMCOTIZACIONINFO).toEqual({ idCotizacionInfo: 123 });
      });

      it('should return new ITMCOTIZACIONINFO if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTMCOTIZACIONINFO = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultTMCOTIZACIONINFO).toEqual(new TMCOTIZACIONINFO());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { idCotizacionInfo: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTMCOTIZACIONINFO = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTMCOTIZACIONINFO).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
