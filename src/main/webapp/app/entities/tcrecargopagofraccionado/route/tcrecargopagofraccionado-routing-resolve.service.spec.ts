jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ITCRECARGOPAGOFRACCIONADO, TCRECARGOPAGOFRACCIONADO } from '../tcrecargopagofraccionado.model';
import { TCRECARGOPAGOFRACCIONADOService } from '../service/tcrecargopagofraccionado.service';

import { TCRECARGOPAGOFRACCIONADORoutingResolveService } from './tcrecargopagofraccionado-routing-resolve.service';

describe('Service Tests', () => {
  describe('TCRECARGOPAGOFRACCIONADO routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: TCRECARGOPAGOFRACCIONADORoutingResolveService;
    let service: TCRECARGOPAGOFRACCIONADOService;
    let resultTCRECARGOPAGOFRACCIONADO: ITCRECARGOPAGOFRACCIONADO | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(TCRECARGOPAGOFRACCIONADORoutingResolveService);
      service = TestBed.inject(TCRECARGOPAGOFRACCIONADOService);
      resultTCRECARGOPAGOFRACCIONADO = undefined;
    });

    describe('resolve', () => {
      it('should return ITCRECARGOPAGOFRACCIONADO returned by find', () => {
        // GIVEN
        service.find = jest.fn(idRecargoPagoFraccionado => of(new HttpResponse({ body: { idRecargoPagoFraccionado } })));
        mockActivatedRouteSnapshot.params = { idRecargoPagoFraccionado: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCRECARGOPAGOFRACCIONADO = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCRECARGOPAGOFRACCIONADO).toEqual({ idRecargoPagoFraccionado: 123 });
      });

      it('should return new ITCRECARGOPAGOFRACCIONADO if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCRECARGOPAGOFRACCIONADO = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultTCRECARGOPAGOFRACCIONADO).toEqual(new TCRECARGOPAGOFRACCIONADO());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { idRecargoPagoFraccionado: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCRECARGOPAGOFRACCIONADO = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCRECARGOPAGOFRACCIONADO).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
