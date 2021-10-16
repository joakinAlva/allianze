jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ITMCOTIZACIONEXPPROPIA, TMCOTIZACIONEXPPROPIA } from '../tmcotizacionexppropia.model';
import { TMCOTIZACIONEXPPROPIAService } from '../service/tmcotizacionexppropia.service';

import { TMCOTIZACIONEXPPROPIARoutingResolveService } from './tmcotizacionexppropia-routing-resolve.service';

describe('Service Tests', () => {
  describe('TMCOTIZACIONEXPPROPIA routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: TMCOTIZACIONEXPPROPIARoutingResolveService;
    let service: TMCOTIZACIONEXPPROPIAService;
    let resultTMCOTIZACIONEXPPROPIA: ITMCOTIZACIONEXPPROPIA | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(TMCOTIZACIONEXPPROPIARoutingResolveService);
      service = TestBed.inject(TMCOTIZACIONEXPPROPIAService);
      resultTMCOTIZACIONEXPPROPIA = undefined;
    });

    describe('resolve', () => {
      it('should return ITMCOTIZACIONEXPPROPIA returned by find', () => {
        // GIVEN
        service.find = jest.fn(idCotizacionExpPropia => of(new HttpResponse({ body: { idCotizacionExpPropia } })));
        mockActivatedRouteSnapshot.params = { idCotizacionExpPropia: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTMCOTIZACIONEXPPROPIA = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTMCOTIZACIONEXPPROPIA).toEqual({ idCotizacionExpPropia: 123 });
      });

      it('should return new ITMCOTIZACIONEXPPROPIA if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTMCOTIZACIONEXPPROPIA = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultTMCOTIZACIONEXPPROPIA).toEqual(new TMCOTIZACIONEXPPROPIA());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { idCotizacionExpPropia: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTMCOTIZACIONEXPPROPIA = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTMCOTIZACIONEXPPROPIA).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
