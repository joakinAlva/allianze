jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ITCDESCUENTOTIPORIESGO, TCDESCUENTOTIPORIESGO } from '../tcdescuentotiporiesgo.model';
import { TCDESCUENTOTIPORIESGOService } from '../service/tcdescuentotiporiesgo.service';

import { TCDESCUENTOTIPORIESGORoutingResolveService } from './tcdescuentotiporiesgo-routing-resolve.service';

describe('Service Tests', () => {
  describe('TCDESCUENTOTIPORIESGO routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: TCDESCUENTOTIPORIESGORoutingResolveService;
    let service: TCDESCUENTOTIPORIESGOService;
    let resultTCDESCUENTOTIPORIESGO: ITCDESCUENTOTIPORIESGO | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(TCDESCUENTOTIPORIESGORoutingResolveService);
      service = TestBed.inject(TCDESCUENTOTIPORIESGOService);
      resultTCDESCUENTOTIPORIESGO = undefined;
    });

    describe('resolve', () => {
      it('should return ITCDESCUENTOTIPORIESGO returned by find', () => {
        // GIVEN
        service.find = jest.fn(idDescuentoTipoRiesgo => of(new HttpResponse({ body: { idDescuentoTipoRiesgo } })));
        mockActivatedRouteSnapshot.params = { idDescuentoTipoRiesgo: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCDESCUENTOTIPORIESGO = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCDESCUENTOTIPORIESGO).toEqual({ idDescuentoTipoRiesgo: 123 });
      });

      it('should return new ITCDESCUENTOTIPORIESGO if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCDESCUENTOTIPORIESGO = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultTCDESCUENTOTIPORIESGO).toEqual(new TCDESCUENTOTIPORIESGO());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { idDescuentoTipoRiesgo: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTCDESCUENTOTIPORIESGO = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTCDESCUENTOTIPORIESGO).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
