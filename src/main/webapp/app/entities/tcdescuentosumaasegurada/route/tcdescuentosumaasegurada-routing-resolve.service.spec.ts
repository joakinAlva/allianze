jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ITCDESCUENTOSUMAASEGURADA, TCDESCUENTOSUMAASEGURADA } from '../tcdescuentosumaasegurada.model';
import { TCDESCUENTOSUMAASEGURADAService } from '../service/tcdescuentosumaasegurada.service';

import { TCDESCUENTOSUMAASEGURADARoutingResolveService } from './tcdescuentosumaasegurada-routing-resolve.service';

describe('Service Tests', () => {
  describe('TCDESCUENTOSUMAASEGURADA routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: TCDESCUENTOSUMAASEGURADARoutingResolveService;
    let service: TCDESCUENTOSUMAASEGURADAService;
    let resultTCDESCUENTOSUMAASEGURADA: ITCDESCUENTOSUMAASEGURADA | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(TCDESCUENTOSUMAASEGURADARoutingResolveService);
      service = TestBed.inject(TCDESCUENTOSUMAASEGURADAService);
      resultTCDESCUENTOSUMAASEGURADA = undefined;
    });

    describe('resolve', () => {
      it('should return ITCDESCUENTOSUMAASEGURADA returned by find', () => {
        // GIVEN
        service.find = jest.fn(idDescuentoSumaAsegurada => of(new HttpResponse({ body: { idDescuentoSumaAsegurada } })));
        mockActivatedRouteSnapshot.params = { idDescuentoSumaAsegurada: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCDESCUENTOSUMAASEGURADA = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCDESCUENTOSUMAASEGURADA).toEqual({ idDescuentoSumaAsegurada: 123 });
      });

      it('should return new ITCDESCUENTOSUMAASEGURADA if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCDESCUENTOSUMAASEGURADA = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultTCDESCUENTOSUMAASEGURADA).toEqual(new TCDESCUENTOSUMAASEGURADA());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { idDescuentoSumaAsegurada: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCDESCUENTOSUMAASEGURADA = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCDESCUENTOSUMAASEGURADA).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
