jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ITCESTATUSCOTIZACION, TCESTATUSCOTIZACION } from '../tcestatuscotizacion.model';
import { TCESTATUSCOTIZACIONService } from '../service/tcestatuscotizacion.service';

import { TCESTATUSCOTIZACIONRoutingResolveService } from './tcestatuscotizacion-routing-resolve.service';

describe('Service Tests', () => {
  describe('TCESTATUSCOTIZACION routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: TCESTATUSCOTIZACIONRoutingResolveService;
    let service: TCESTATUSCOTIZACIONService;
    let resultTCESTATUSCOTIZACION: ITCESTATUSCOTIZACION | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(TCESTATUSCOTIZACIONRoutingResolveService);
      service = TestBed.inject(TCESTATUSCOTIZACIONService);
      resultTCESTATUSCOTIZACION = undefined;
    });

    describe('resolve', () => {
      it('should return ITCESTATUSCOTIZACION returned by find', () => {
        // GIVEN
        service.find = jest.fn(idEstatusCotizacion => of(new HttpResponse({ body: { idEstatusCotizacion } })));
        mockActivatedRouteSnapshot.params = { idEstatusCotizacion: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCESTATUSCOTIZACION = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCESTATUSCOTIZACION).toEqual({ idEstatusCotizacion: 123 });
      });

      it('should return new ITCESTATUSCOTIZACION if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCESTATUSCOTIZACION = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultTCESTATUSCOTIZACION).toEqual(new TCESTATUSCOTIZACION());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { idEstatusCotizacion: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCESTATUSCOTIZACION = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCESTATUSCOTIZACION).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
